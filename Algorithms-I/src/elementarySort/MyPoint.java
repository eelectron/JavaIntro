package elementarySort;

import java.awt.Point;

public class MyPoint extends Point implements Comparable {
	public static boolean Y_AXIS, COMPARE_ANGLE;
	// slope of point
	double slope;

	private static final long serialVersionUID = 1L;
	public MyPoint(int x, int y){
		super(x,y);
	}
	@Override
	public int compareTo(Object o) {
		MyPoint p = (MyPoint) o;
		if (COMPARE_ANGLE) {
			if (this.slope < p.slope)
				return -1;
			else if (this.slope > p.slope)
				return 1;
			else
				return 0;
		} else {
			if (this.y < p.y)
				return -1;
			else if (this.y > p.y)
				return 1;
			else
				return 0;
		}
	}

	public void setSlope(Point p) {
		try {
			if(this.equals(p))
				slope = 0;
			else{
				slope = (this.y - p.y) / (this.x - p.x);
				slope = Math.toDegrees( Math.atan(slope) );
				if(slope < 0)
					slope = -slope + 90;
			}
			
		} catch (Exception ex) {
			slope = 90;
		}
	}

	// Checks counter-clockwiseness od three points
	public static int ccw(MyPoint a, MyPoint b, MyPoint c) {
		double area = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
		if (area < 0)
			return -1;
		else if (area > 0)
			return 1;
		else
			return 0;
	}
	
	public String toString(){
		return "("+x+","+y+")";
	}
}
