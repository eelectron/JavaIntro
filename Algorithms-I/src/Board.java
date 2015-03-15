/*************************************************************************
 * Name: Prashant Singh Email: prashantfromindia@gmail.com
 * 
 * Compilation: javac Board.java Execution:
 * java Board Dependencies: null
 * 
 * Description: This is a immutable Board data type which represents a NxN
 * puzzle.For ex: 8puzzle,15puzzle,etc
 * 
 * @author Prashant Singh
 * @date 01-Mar-2015
 *************************************************************************/
public class Board {
    private final int N; // dimension of board
    private final char[][] blocks; // represents a NxN board
    private int bRow = 0; // location of blank tile
    private int bCol = 0;

    public Board(int[][] copy) {
        this.N = copy[0].length;
        this.blocks = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // Copy blocks
                blocks[i][j] = (char) copy[i][j];

                // Gets the location of blank block
                if (blocks[i][j] == 0) {
                    bRow = i;
                    bCol = j;
                }
            }
        }
    }

    /*
     * @return dimension of board
     */
    public int dimension() {
        return N;
    }

    /*
     * @return no of blocks out of place.
     */
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // Calculate hamming
                if (blocks[i][j] != getBlockVal(i, j) && blocks[i][j] != 0)
                    hamming++;
            }
        }
        return hamming;
    }

    /*
     * 0 denotes the blank block.
     * 
     * @return sum of manhattan distances between blocks and their goal.
     */
    public int manhattan() {
        int value, gRow, gCol, dx, dy, manhattan = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                value = blocks[i][j];

                // don't find manhattan dist. for blank block
                if (value != 0) {
                    gRow = (value - 1) / N;
                    gCol = (value - 1) % N;
                    dx = Math.abs(i - gRow);
                    dy = Math.abs(j - gCol);
                    manhattan += (dx + dy);
                }
            }
        }
        return manhattan;
    }

    /*
     * @return true if given board is our goal.
     */
    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != getBlockVal(i, j)
                        && getBlockVal(i, j) != N * N)
                    return false;
            }
        }
        return true;
    }

    /*
     * @return true if two boards are equal.
     */
    public boolean equals(Object y) {
        // return true if both pointing to same object
        if (this == y)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;

        Board b = (Board) y;
        // return false if there dimensions are diff.
        if (this.N != b.N)
            return false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != b.blocks[i][j])
                    return false;
            }
        }
        return true;
    }

    /*
     * @return a Board which is twin of this board.
     */
    public Board twin() {
        int n1, n2;
        int[][] boardsTwin = copyBlocks();
        for (int i = 0; i < boardsTwin.length; i++) {
            for (int j = 1; j < boardsTwin.length; j++) {
                n1 = boardsTwin[i][j];
                n2 = boardsTwin[i][j - 1];
                if (n1 != 0 && n2 != 0) {
                    boardsTwin[i][j] = n2;
                    boardsTwin[i][j - 1] = n1;
                    return new Board(boardsTwin);
                }
            }
        }
        return null;
    }

    /*
     * @return all the neighbors of this board.
     */
    public Iterable<Board> neighbors() {
        Queue<Board> s = new Queue<Board>();
        int[][] b = copyBlocks();
        Board board;
        /*
         * if adjacent location of blank block is valid then create a new board
         * by swappicountMoves++;ng that blank block with adj block.
         */
        // check above loc
        if (isValid(bRow - 1, bCol)) {
            swap(b, bRow - 1, bCol);
            board = new Board(b);
            s.enqueue(board);
            swap(b, bRow - 1, bCol);
        }
        // below
        if (isValid(bRow + 1, bCol)) {
            swap(b, bRow + 1, bCol);
            board = new Board(b);
            s.enqueue(board);
            swap(b, bRow + 1, bCol);
        }

        // left
        if (isValid(bRow, bCol - 1)) {
            swap(b, bRow, bCol - 1);
            board = new Board(b);
            s.enqueue(board);
            swap(b, bRow, bCol - 1);
        }

        // right
        if (isValid(bRow, bCol + 1)) {
            swap(b, bRow, bCol + 1);
            board = new Board(b);
            s.enqueue(board);
            swap(b, bRow, bCol + 1);
        }
        return s;
    }

    /*
     * @return string representation of this board.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", (int) blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    /*
     * @return char 2d array which is copy of given board.
     */
    private int[][] copyBlocks() {
        int[][] b = new int[N][N];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                b[i][j] = blocks[i][j];
            }
        }
        return b;
    }

    /*
     * swaps given tile with blank tile.
     */
    private void swap(int[][] b, int i, int j) {
        int temp = b[bRow][bCol];
        b[bRow][bCol] = b[i][j];
        b[i][j] = temp;
    }

    /*
     * @return block number which must be present at the specified location(i,
     * j).
     */
    private int getBlockVal(int i, int j) {
        return i * N + j + 1;
    }

    /*
     * @return true if given index is valid.
     */
    private boolean isValid(int i, int j) {
        if (i < 0 || i >= N)
            return false;
        if (j < 0 || j >= N)
            return false;
        return true;
    }
}
