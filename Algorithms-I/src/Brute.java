/*************************************************************************
 * Name: Prashant Singh Email: prashantfromindia@gmail.com
 * 
 * Compilation: javac Brute.java Execution: java Brute input400.txt
 * Dependencies: Point.java,StdDraw.java
 * 
 * Description: This program finds collinear points among a given set of N
 * points and print them on screen.Its running time is O(N^4).
 * 
 *************************************************************************/
public class Brute {
	public static void main(String[] args) {
		Point[] points;
		Point[] selPoints = new Point[4]; // for 4 selected points

		// rescale coordinates and turn on animation mode
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);

		In in = new In(args[0]); // read x,y from file
		int pointCount = in.readInt(); // total points
		points = new Point[pointCount]; // stores all points
		for (int i = 0; i < points.length; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
			points[i].draw();
		}

		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int j2 = j + 1; j2 < points.length; j2++) {
					for (int k = j2 + 1; k < points.length; k++) {
						selPoints[0] = points[i]; // put 4 points in
													// different array
						selPoints[1] = points[j];
						selPoints[2] = points[j2];
						selPoints[3] = points[k];
						if (isSlopeEqual(selPoints)) {
							Insertion.sort(selPoints);
							selPoints[0].drawTo(selPoints[3]); // draw line
																// segment
							StdOut.println(selPoints[0] + " -> " + selPoints[1]
									+ " -> " + selPoints[2] + " -> "
									+ selPoints[3]);
						}
					}
				}
			}
		}
		StdDraw.show(0); // display all points on screen
	}

	/*
	 * @return true if slope of 4 points are equal.
	 */
	private static boolean isSlopeEqual(Point[] p) {
		double s1 = p[0].slopeTo(p[1]);
		double s2 = p[0].slopeTo(p[2]);
		double s3 = p[0].slopeTo(p[3]);
		if (s1 == s2 && s1 == s3) {
			return true;
		}
		return false;
	}
}
