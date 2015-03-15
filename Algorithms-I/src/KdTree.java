/*************************************************************************
 * Compilation: javac KdTree.java input.txt 
 * Execution: java KdTree
 * Dependencies:Point2D.java RectHV.java StdDraw.java
 * 
 * This data structure is a implementation of 2d-tree version of kd-tree. The
 * idea is to build a BST with points in the nodes,using x-y co-ordinate to
 * compare based on whether a node present in even level or odd level.
 * 
 * Its range search and nearest point search method has better timings than the
 * brute force implementation.
 * 
 *************************************************************************/
public class KdTree {
    private static final boolean EVEN = true;
    private static final boolean ODD = false;

    private Node root; // Root of my binary tree
    private int size; // total nodes in tree

    private Point2D nearestPoint;
    private double minDist;

    private double xmin, ymin, xmax, ymax;

    // Denotes node of the tree
    private static class Node {
        private Point2D point;
        private boolean isEven; // node is at even or odd level
        private RectHV rect; // plane in which point lies
        private Node left;
        private Node right;

        // constructor creates the object with point, level and plane
        Node(Point2D point, boolean evenOddLevel, RectHV rectangle) {
            this.point = point;
            isEven = evenOddLevel;
            rect = rectangle;
        }
    }

    /*
     * @return true if tree is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * @return total points stored in tree.
     */
    public int size() {
        return size;
    }

    /*
     * Inserts a node which contains the point and rectangle(sub plane) in which
     * point lies.Its a recursive method.
     */
    public void insert(Point2D p) {
        // put the point in tree with the info about its Evenness and
        // in which rectangle(plane) it lies
        xmin = 0;
        ymin = 0;
        xmax = 1.0;
        ymax = 1.0;
        root = put(root, p, EVEN);
    }

    /*
     * Called by insert() , is used recursively to insert a point.
     */
    private Node put(Node h, Point2D p, boolean e) {
        // Insert the node
        if (h == null) {
            size++; // increment size of tree by one
            RectHV rec = new RectHV(xmin, ymin, xmax, ymax);
            return new Node(p, e, rec);
        }

        // DON'T insert duplicate points
        if (p.equals(h.point))
            return h;

        int cmp = 0;
        // point in tree
        double x = h.point.x();
        double y = h.point.y();

        if (h.isEven == EVEN) {
            cmp = compareX(p, h.point); // compare x
            if (cmp < 0) {
                xmax = x;
                h.left = put(h.left, p, ODD);
            } else {
                xmin = x;
                h.right = put(h.right, p, ODD);
            }
        } else {
            cmp = compareY(p, h.point); // compare y
            if (cmp < 0) {
                ymax = y;
                h.left = put(h.left, p, EVEN);
            } else {
                ymin = y;
                h.right = put(h.right, p, EVEN);
            }
        }
        return h;
    }

    /*
     * Used to compare x coordinate of two points.
     * 
     * @return {-1,0,1} any of these based on whether p is less, equal or more
     * than q.
     */
    private int compareX(Point2D p, Point2D q) {
        if (p.x() < q.x())
            return -1;
        if (p.x() > q.x())
            return +1;
        return 0;
    }

    /*
     * Used to compare y coordinate of two points.
     * 
     * @return {-1,0,1} any of these based on whether p is less, equal or more
     * than q.
     */
    private int compareY(Point2D p, Point2D q) {
        if (p.y() < q.y())
            return -1;
        if (p.y() > q.y())
            return +1;
        return 0;
    }

    /*
     * @return true if tree contains the queryPoint.
     */
    public boolean contains(Point2D p) {
        Node curNode = root; // start with root node
        int cmp = 0;
        while (curNode != null) {
            if (p.equals(curNode.point)) // FOUND point in tree :)
                return true;

            if (curNode.isEven == EVEN)
                cmp = compareX(p, curNode.point); // compare x if node is at
                                                  // even level
            else
                cmp = compareY(p, curNode.point); // compare y if node is at odd
                                                  // level

            if (cmp < 0)
                curNode = curNode.left; // go to left or bottom plane
            else
                curNode = curNode.right; // go to right or top plane
        }
        return false;
    }

    /*
     * Draws all points Recursively in plane and all vertical lines in RED and
     * all horizontal lines in BLUE.
     */
    public void draw() {
        RectHV rect = new RectHV(0, 0, 1, 1);
        rect.draw();
        draw(root);
    }

    private void draw(Node node) {
        if (node == null)
            return;

        // draw point first
        Point2D p = node.point;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        p.draw();

        double x = p.x();
        double y = p.y();

        // then draw line through it
        if (node.isEven == EVEN) { // draw vertical line
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            // override xmin,xmax
            StdDraw.line(x, node.rect.ymin(), x, node.rect.ymax());
        } else { // draw horizontal line
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(node.rect.xmin(), y, node.rect.xmax(), y);
        }

        draw(node.left); // draw point in left plane
        draw(node.right); // draw point in right plane
    }

    /*
     * It recursively check the nodes which can lie in query rectangle.
     * 
     * @return a queue containing all the points lie in query rectangle.
     */
    public Iterable<Point2D> range(RectHV queryRect) {
        Queue<Point2D> queue = new Queue<Point2D>();
        range(queryRect, root, queue);
        return queue;
    }

    private void range(RectHV queryRect, Node node, Queue<Point2D> queue) {
        // Base case
        if (node == null)
            return;

        RectHV rect = node.rect; // plane in which node lies

        if (queryRect.intersects(rect)) {
            if (queryRect.contains(node.point)) {
                queue.enqueue(node.point); // add point to queue contained in
                                           // rectangle
            }
            range(queryRect, node.left, queue);
            range(queryRect, node.right, queue);
        }
    }

    /*
     * It recursively searches for a point nearest to query point and returns
     * it.
     */
    public Point2D nearest(Point2D queryPoint) {
        if (isEmpty())
            return null; // no points in tree

        nearestPoint = root.point;
        minDist = queryPoint.distanceSquaredTo(root.point);

        nearest(queryPoint, root);
        return nearestPoint;
    }

    private void nearest(Point2D qp, Node node) {
        // Base case
        if (node == null)
            return;

        if (node.rect.contains(qp) || (distance(qp, node) < minDist)) {
            double dis = qp.distanceSquaredTo(node.point); // find dist bw qp
                                                           // and np
            if (dis < minDist) {
                nearestPoint = node.point; // new nearest point
                // new min distance between query point and nearest point
                minDist = dis;
            }

            // OPTIMIZED nearest point search
            if (node.isEven) {
                if (qp.x() < node.point.x()) { // qp present in left or bottom
                                              // plane
                    nearest(qp, node.left);
                    nearest(qp, node.right);
                } else { // qp present in right or top plane
                    nearest(qp, node.right);
                    nearest(qp, node.left);
                }
            } else {
                if (qp.y() < node.point.y()) { // qp present in left or bottom
                                              // plane
                    nearest(qp, node.left);
                    nearest(qp, node.right);
                } else { // qp present in right or top plane
                    nearest(qp, node.right);
                    nearest(qp, node.left);
                }
            }

        }

    }

    /*
     * Minimum distance between a point and a given rectangle Here node contains
     * a point and plane.
     */
    private double distance(Point2D qp, Node node) {
        RectHV rect = node.rect;
        double d = 0;

        if (qp.x() < rect.xmin()) { // qp in left of rectangle
            if (qp.y() < rect.ymin())
                d = qp.distanceSquaredTo(new Point2D(rect.xmin(), rect.ymin()));
            else if (qp.y() > rect.ymax())
                d = qp.distanceSquaredTo(new Point2D(rect.xmin(), rect.ymax()));
            else
                d = qp.distanceSquaredTo(new Point2D(rect.xmin(), qp.y()));
        } else if (qp.x() > rect.xmax()) { // qp in right of rectangle
            if (qp.y() < rect.ymin())
                d = qp.distanceSquaredTo(new Point2D(rect.xmax(), rect.ymin()));
            else if (qp.y() > rect.ymax())
                d = qp.distanceSquaredTo(new Point2D(rect.xmax(), rect.ymax()));
            else
                d = qp.distanceSquaredTo(new Point2D(rect.xmax(), qp.y()));
        } else if (qp.y() < rect.ymin()) { // qp in bottom of rectangle
            d = qp.distanceSquaredTo(new Point2D(qp.x(), rect.ymin()));
        } else
            d = qp.distanceSquaredTo(new Point2D(qp.x(), rect.ymax()));

        // finally return the min distance
        return d;
    }

    // Used to test draw()
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);

        StdDraw.show(0);
        // initialize the data structures with N points from standard input
        // PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            // brute.insert(p);
        }

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        kdtree.draw();

        StdDraw.show(1000);
    }
}