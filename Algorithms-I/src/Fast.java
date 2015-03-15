/*************************************************************************
 * Name: Prashant Singh
 * Email: prashantfromindia@gmail.com
 *
 * Compilation:  javac Fast.java
 * Execution:	 java Fast rs1423.txt	
 * Dependencies: Point.java,StdDraw.java
 *
 * Description: This program finds collinear points among a given set of N points
 * and print them on screen.Its worst case running time is O(N^2logN).
 *
 *************************************************************************/
import java.util.Arrays;
public class Fast {
	private Point[] points;
	private Point[] pointsHelper;
	private int pointCount;
	private Point[] sortPoint;
	private int sp;
	public static void main(String[] args) {
		Fast f = new Fast();
		// rescale coordinates and turn on animation mode
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.setPenRadius(.01);
		StdDraw.show(0);

		f.init(args[0]); // initialize points[] array with points
		f.start();

		StdDraw.show(0); // display all points on screen
	}

	/*
	 * Reads all the points from given text file and initialize the points[].
	 */
	private void init(String fileName) {
		In in = new In(fileName); // read x,y from file
		pointCount = in.readInt(); // total points
		points = new Point[pointCount]; // stores all points
		pointsHelper = new Point[pointCount];
		sortPoint = new Point[pointCount];
		for (int i = 0; i < points.length; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
			pointsHelper[i] = new Point(x, y);

			// draw every point on screen
			points[i].draw();
		}
		Arrays.sort(points);
	}

	/*
	 * Start finding collinear points to each point in points[]. This method
	 * uses Arrays.sort() whose O(NlogN) unlike Insertion.sort() whose O(N^2).
	 */
	private void start() {
		for (int i = 0; i < points.length; i++) {
				// sort pointshelper based on slope with points[i]
				Arrays.sort(pointsHelper, points[i].SLOPE_ORDER);

				// find points collinear to points[i]
				collinearToRefPoint(points[i], pointsHelper);
		}
	}

	/*
	 * Finds and print points collinear to refPoint.
	 */
	private void collinearToRefPoint(Point refPoint, Point[] p) {
		int lo = -1, hi = -1, i = 0;
		// find longest seq of collinear points
		for (i = 0; i + 2 < p.length; i++) {
			if (isCollinear(refPoint, p[i], p[i + 1], p[i + 2])) {
				if (lo == -1)
					lo = i;
			} else if (lo != -1 && hi == -1) {
				hi = i + 1;
				processLine(refPoint, p, lo, hi);

				// reset lo,hi
				lo = -1;
				hi = -1;
			}
		}
		if (lo != -1 && hi == -1) {
			hi = i + 1;
			processLine(refPoint, p, lo, hi);

			// reset lo,hi
			lo = -1;
			hi = -1;
		}

	}

	/*
	 * This method process each point sequence whic are collinear to refPoint.
	 */
	private void processLine(Point refPoint, Point[] p, int lo, int hi) {
		// found a seq
		if (lo < hi) {
			// extract it
			extractIt(refPoint, p, lo, hi);

			// sort it
			Arrays.sort(sortPoint, 0, sp);
			
			if(refPoint.compareTo(sortPoint[0]) <= 0){
				// print on stdout
				printLineSegment(sortPoint);

				// print on screen
				sortPoint[0].drawTo(sortPoint[sp - 1]);

				// mark visited
				//markVisited(sortPoint);
			}
			
		}

	}

	/*
	 * Extract the collinear points from pointHelper[] and put them in
	 * sortPoint[], which represent a single line segment collinear to refPoint.
	 */
	private void extractIt(Point refPoint, Point[] p, int lo, int hi) {
		int item = hi - lo + 1;
		sp = 0;
		sortPoint[sp++] = refPoint;
		for (int i = 0; i < item; i++) {
			sortPoint[sp++] = p[lo + i];
		}
	}

	/*
	 * Prints a line segment on StdOut
	 */
	private void printLineSegment(Point[] p) {
		int i = 0;
		for (i = 0; i < sp; i++) {
			StdOut.print(p[i]);
			if (i + 1 != sp)
				StdOut.print(" -> ");
		}
		StdOut.println();
	}

	/*
	 * @return true if three points are collinear.
	 */
	private boolean isCollinear(Point point, Point point2, Point point3,
			Point p3) {
		double s1 = point.slopeTo(point2);
		double s2 = point.slopeTo(point3);
		double s3 = point.slopeTo(p3);
		if (s1 == s2 && s1 == s3)
			return true;
		return false;
	}
}