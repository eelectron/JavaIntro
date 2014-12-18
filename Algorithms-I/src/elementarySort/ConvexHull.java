package elementarySort;

import java.awt.Point;
import java.util.Stack;

public class ConvexHull {
	// to store points which are on convex hull
	public static Stack<MyPoint> hull = new Stack<MyPoint>();

	public static Stack<MyPoint> getHull(MyPoint[] p) {
		// sort array of point in ascending order of Y-Coordinate
		Sort.shellSort(p);

		// Create a array to store point with their polar angle wrt to p0
		MyPoint[] points = new MyPoint[p.length];
		points[0] = p[0];

		for (int i = 0; i < p.length; i++) {
			points[i] = p[i];
			points[i].setSlope(points[0]);
			points[i].COMPARE_ANGLE = true;
		}

		// Sort according to POLAR angle
		Sort.shellSort(points);
		
		// lowest point must be on hull
		hull.push(points[0]);
		hull.push(points[1]);

		// Find points which are on hull
		for (int i = 2; i < points.length; i++) {
			MyPoint top = hull.pop();
			while (MyPoint.ccw(hull.peek(), top, points[i]) <= 0)
				top = hull.pop();
			hull.push(top);
			hull.push(points[i]);
		}
		return hull;
	}
}