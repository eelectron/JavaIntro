/*************************************************************************
 * Compilation: javac PointSET.java 
 * Execution: java PointSET 
 * Dependencies:Point2D.java RectHV.java StdDraw.java
 * 
 * This is a brute force implementation of 2d-tree.This data structure is used to
 * store all points in a given x-y plane and then we can perform range search for
 * a query rectangle and nearest point.
 *
 * 
 * 
 *************************************************************************/
public class PointSET {
    private SET<Point2D> points = new SET<Point2D>();

    /*
     * @return true if there no points are present in the set.
     */
    public boolean isEmpty() {
        return points.size() == 0;
    }
    
    /*
     * @return total number of points inserted */
    public int size() {
        return points.size();
    }
    
    /*
     * Inserts a point (x, y) */
    public void insert(Point2D p) {
        points.add(p);
    }
    
    /*
     * @return true if query point present in SET.*/
    public boolean contains(Point2D p) {
        return points.contains(p);
    }
    
    /*
     * Draws all point in SET on Window.*/
    public void draw() {
        for (Point2D p : points)
            p.draw();
    }
    
    /*
     * Return all points lies inside query rectangle. 
     * */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> q = new Queue<Point2D>();
        for (Point2D p : points) {
            if (rect.contains(p))
                q.enqueue(p);
        }
        // return all the point inside the rectangle
        return q;
    }
    
    /*
     * @return a point nearest to query point.*/
    public Point2D nearest(Point2D queryPoint) {
        if (isEmpty())
            return null; // no points in tree

        Point2D nearestPoint = points.min();
        double minDist = queryPoint.distanceSquaredTo(points.min());
        double dis = 0.0;

        for (Point2D x : points) {
            dis = x.distanceSquaredTo(queryPoint);
            if (dis < minDist) {
                minDist = dis;
                nearestPoint = x;
            }
        }
        return nearestPoint;
    }
}