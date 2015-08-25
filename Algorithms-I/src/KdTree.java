/*************************************************************************
 * @Compilation: javac KdTree.java input.txt
 * @Execution: java KdTree
 * @Dependencies:Point2D.java RectHV.java StdDraw.java
 * 
 *                            This data structure is a implementation of 2d-tree
 *                            version of kd-tree. The idea is to build a BST
 *                            with points in the nodes,using x-y co-ordinate to
 *                            compare based on whether a node present in even
 *                            level or odd level.
 * 
 *                            Its range search and nearest point search method
 *                            has better timings than the brute force
 *                            implementation.
 * 
 *************************************************************************/
public class KdTree {
    private static final boolean EVEN = true;
    private static final boolean ODD = false;

    private Node root; // Root of my binary tree
    private int size; // total nodes in tree

    private Queue<Point2D> rangeOut;

    private Point2D nearestPoint;
    private double minDist;

    // co-ordinate of initial window
    private double xmin, ymin, xmax, ymax;

    // Denotes node of the tree
    private static class Node {
        private Point2D point;
        private Node left;
        private Node right;

        // constructor creates the object with point, level and plane
        Node(Point2D point) {
            this.point = point;
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
        root = put(root, p, EVEN);
    }

    /*
     * Called by insert() , is used recursively to insert a point. e is telling
     * whether Node is EVEN or ODD.
     */
    private Node put(Node h, Point2D p, boolean e) {
        // Insert the node
        if (h == null) {
            size++; // increment size of tree by one
            return new Node(p);
        }

        // Avoid duplicate points in tree
        if (p.equals(h.point))
            return h; // if point p is equal to current node's point then return
                      // node

        int cmp = 0;
        if (e == EVEN) {
            cmp = compareX(p, h.point); // compare x
            if (cmp < 0) {
                h.left = put(h.left, p, ODD);
            } else {
                h.right = put(h.right, p, ODD);
            }
        } else {
            cmp = compareY(p, h.point); // compare y
            if (cmp < 0) {
                h.left = put(h.left, p, EVEN);
            } else {
                h.right = put(h.right, p, EVEN);
            }
        }
        return h;
    }

    /*
     * Used to compare x coordinate of two points
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
        boolean level = EVEN; // level of root node
        while (curNode != null) {
            if (p.equals(curNode.point)) // FOUND point in tree :)
                return true;

            // compare query point with point in tree
            if (level == EVEN)
                cmp = compareX(p, curNode.point); // compare x at even level
            else
                cmp = compareY(p, curNode.point); // compare y at odd level

            // then take appropriate branch
            if (cmp < 0) {
                level = !level; // update level of next node
                curNode = curNode.left; // go to left or bottom plane
            } else {
                level = !level;
                curNode = curNode.right; // go to right or top plane
            }
        }
        return false;
    }

    /*
     * Draws all points Recursively in plane and all vertical lines in RED and
     * all horizontal lines in BLUE.
     */
    public void draw() {
        xmin = 0;
        ymin = 0;
        xmax = 1;
        ymax = 1;
        RectHV rect = new RectHV(xmin, ymin, xmax, ymax);
        rect.draw();
        draw(root, EVEN, xmin, ymin, xmax, ymax);
    }

    private void draw(Node node, boolean level, double x0, double y0,
            double x1, double y1) {
        if (node == null)
            return;

        // draw point first
        Point2D p = node.point;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.02);
        p.draw();

        double x = p.x();
        double y = p.y();

        // then draw line through it
        if (level == EVEN) { // draw vertical line
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(x, y0, x, y1);

            draw(node.left, !level, x0, y0, x, y1); // draw point in left plane
            draw(node.right, !level, x, y0, x1, y1); // draw point in right
                                                     // plane
        } else { // draw horizontal line
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(x0, y, x1, y);

            draw(node.left, !level, x0, y0, x1, y); // draw point in left plane
            draw(node.right, !level, x0, y, x1, y1); // draw point in right
                                                     // plane
        }

    }

    /*
     * It recursively check the nodes which can lie in query rectangle.
     * 
     * @return a queue containing all the points lie in query rectangle.
     */
    public Iterable<Point2D> range(RectHV queryRect) {
        rangeOut = new Queue<Point2D>();
        RectHV r = new RectHV(0, 0, 1, 1); // plane which root node splits
        range(queryRect, root, EVEN, r);
        return rangeOut;
    }

    /*
     * If Node plane intersects with query rect then if Node Point lies inside
     * query rect then add it to rangeOut queue.
     */
    private void range(RectHV queryRect, Node node, boolean level, RectHV r) {
        // Base case
        if (node == null)
            return;

        // node point
        Point2D p = node.point;

        if (queryRect.intersects(r)) {
            if (queryRect.contains(p)) {
                rangeOut.enqueue(p); // add point to queue lies in
                                     // rectangle
            }

            // select apt subtree
            if (level == EVEN) {
                range(queryRect, node.left, !level,
                        new RectHV(r.xmin(), r.ymin(), p.x(), r.ymax()));
                range(queryRect, node.right, !level, new RectHV(p.x(),
                        r.ymin(), r.xmax(), r.ymax()));
            } else {
                range(queryRect, node.left, !level,
                        new RectHV(r.xmin(), r.ymin(), r.xmax(), p.y()));
                range(queryRect, node.right, !level, new RectHV(r.xmin(),
                        p.y(), r.xmax(), r.ymax()));
            }

        }
    }

    /*
     * Recursively searches for a point nearest to query point and returns it.
     */
    public Point2D nearest(Point2D queryPoint) {
        if (isEmpty())
            return null; // no points in tree

        nearestPoint = root.point;
        minDist = queryPoint.distanceSquaredTo(root.point);

        RectHV r = new RectHV(0, 0, 1, 1);
        nearest(queryPoint, root, EVEN, r);
        return nearestPoint;
    }

    /*
     * Recursively searches for each node and if its closer then the current
     * closest point then updates the nearest point and min distance between
     * them.
     */
    private void nearest(Point2D qp, Node node, boolean level, RectHV r) {
        // Base case
        if (node == null)
            return;

        // node's point
        Point2D p = node.point;

        if (r.contains(qp) || (distance(qp, r) < minDist)) {
            double dis = qp.distanceSquaredTo(p); // find dist bw qp
                                                  // and np
            if (dis < minDist) {
                nearestPoint = p; // new nearest point
                // new min distance between query point and nearest point
                minDist = dis;
            }

            // OPTIMIZED nearest point search by selecting better subtree
            if (level == EVEN) {
                if (qp.x() < p.x()) { // qp present in left or bottom plane

                    RectHV r1 = new RectHV(r.xmin(), r.ymin(), p.x(), r.ymax());
                    nearest(qp, node.left, !level, r1);

                    RectHV rr = new RectHV(p.x(), r.ymin(), r.xmax(), r.ymax());
                    nearest(qp, node.right, !level, rr);
                } else { // qp present in right or top plane
                    RectHV rr = new RectHV(p.x(), r.ymin(), r.xmax(), r.ymax());
                    nearest(qp, node.right, !level, rr);

                    RectHV rl = new RectHV(r.xmin(), r.ymin(), p.x(), r.ymax());
                    nearest(qp, node.left, !level, rl);
                }
            } else {
                if (qp.y() < p.y()) { // qp present in left or bottom
                    RectHV rl = new RectHV(r.xmin(), r.ymin(), r.xmax(), p.y());
                    nearest(qp, node.left, !level, rl);

                    RectHV rr = new RectHV(r.xmin(), p.y(), r.xmax(), r.ymax());
                    nearest(qp, node.right, !level, rr);
                } else { // qp present in right or top plane
                    RectHV rr = new RectHV(r.xmin(), p.y(), r.xmax(), r.ymax());
                    nearest(qp, node.right, !level, rr);

                    RectHV rl = new RectHV(r.xmin(), r.ymin(), r.xmax(), p.y());
                    nearest(qp, node.left, !level, rl);
                }
            }

        }
    }

    /*
     * Minimum distance between a point and a given rectangle Here node contains
     * a point and plane.
     */
    private double distance(Point2D qp, RectHV rect) {
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