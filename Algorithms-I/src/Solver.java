/*************************************************************************
 * Name: Prashant Singh Email: prashantfromindia@gmail.com
 * 
 * Compilation: javac Solver.java 
 * Execution: java Solver puzzle30.txt
 * Dependencies: Board.java MinPQ.java ArrayList.java
 * 
 * Description: This is a immutable Solver data structure used to perform
 * operations on Board object such as isSolvable(),minimum no of moves and 
 * to get a solution if the board is solvable. 
 * 
 * @author Prashant Singh 
 * @date   01-Mar-2015
 *************************************************************************/
import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    private final Board initialBoard;
    private final char[][] blocks;
    private final int N;
    private Node bestNode;
    private int minMoves = 0;
    private MinPQ<Node> mpq = new MinPQ<Node>(getNodeComparator());
    private ArrayList<Node> al = new ArrayList<Node>();

    public Solver(Board board) {
        this.initialBoard = board;
        this.N = board.dimension();

        blocks = new char[N][N];

        // get the block from board
        String s = board.toString();
        String[] sa = s.split("\\W+");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = (char) Integer.parseInt(sa[i * N + j + 1]);
            }
        }

    }

    /*
     * This class represents a SEARCH NODE.
     */
    private class Node {
        private Board board;
        private int move;
        private int manhattan;
        private Node parent;

        public Node(Board board) {
            this.board = board;
            manhattan = board.manhattan();
        }
    }

    /*
     * @return true if given board is solvable.
     */
    public boolean isSolvable() {
        int[] board1D = new int[N * N]; // linear representation of board
        int inversions = 0;
        int rowOfBlankTile = 0;

        // convert board to 1d array
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board1D[i * N + j] = blocks[i][j];
                if (board1D[i * N + j] == 0)
                    rowOfBlankTile = i;
            }
        }

        // find no of inversions
        for (int i = 0; i < board1D.length; i++) {
            for (int j = i + 1; j < board1D.length; j++) {
                // skip for blank tile
                if (board1D[j] == 0)
                    continue;
                if (board1D[i] > board1D[j])
                    inversions++;
            }
        }

        // for odd dimesion board for ex 3x3
        if (!isEven(N) && !isEven(inversions))
            return false;

        // for even dimension board for ex 2x2
        if (isEven(N) && isEven(inversions + rowOfBlankTile))
            return false;
        return true;
    }

    /*
     * @return minimum no of moves taken to solve a puzzle. It return -1 if
     * puzzle is unsolvable.
     */
    public int moves() {
        // if board is not solvable then instantly return -1
        if (!isSolvable()) {
            minMoves = -1;
            return minMoves;
        }

        Node temp;
        bestNode = new Node(initialBoard);
        bestNode.parent = null;

        // add the start board to MinPQ
        mpq.insert(bestNode);

        while (!mpq.isEmpty()) {
            // StdOut.println(bestNode.manhattan + " " + bestNode.move);

            // select the board with minimum manhattan distance
            bestNode = mpq.delMin();
            al.add(bestNode);

            // break from loop if goal board is found
            if (bestNode.board.isGoal())
                break;

            // add its neighbors to minpq
            for (Board b : bestNode.board.neighbors()) {
                if (bestNode.parent == null) {
                    temp = new Node(b);
                    temp.move = bestNode.move + 1;
                    temp.parent = bestNode;
                    // Add to PQ if neighbor is not equal to its grandparent
                    mpq.insert(temp);
                } else if (!b.equals(bestNode.parent.board)) {
                    temp = new Node(b);
                    temp.move = bestNode.move + 1;
                    temp.parent = bestNode;
                    // Add to PQ if neighbor is not equal to its grandparent
                    mpq.insert(temp);
                }
            }
        }
        minMoves = bestNode.move;
        return minMoves;
    }

    /*
     * @return solution of given board..
     */
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;

        minMoves = moves();

        Stack<Board> s = new Stack<Board>();
        while (bestNode != null) {
            s.push(bestNode.board);
            bestNode = bestNode.parent;
        }
        return s;
    }

    /*
     * @return true if number is even.
     */
    private boolean isEven(int n) {
        return n % 2 == 0;
    }

    /*
     * BoardComparator class is used to compare to boards. In MinPQ it is used
     * to create a minheap.
     */
    private class NodeComparator implements Comparator<Node> {
        public int compare(Node b1, Node b2) {
            int p1, p2;
            // Manhattan priority of boards
            p1 = b1.manhattan + b1.move;
            p2 = b2.manhattan + b2.move;

            if (p1 < p2)
                return -1;
            if (p1 > p2)
                return 1;

            if (b1.manhattan < b2.manhattan)
                return -1;
            if (b1.manhattan > b2.manhattan)
                return 1;
            return 0;
        }
    }

    /*
     * @return a comparator which is used in MinPQ data structure for comparing
     * two SearchNodes.
     */
    private Comparator<Node> getNodeComparator() {
        return new NodeComparator();
    }

    /*
     * Used for unit testing of code.
     */
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
