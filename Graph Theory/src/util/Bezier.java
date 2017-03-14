package util;

import java.awt.Point;

public class Bezier {
	private static double ERROR = 3;
	public static final Point quadratic (Point p0, Point p1, Point p2, double t) {
		double x = Math.pow(1-t, 2) * p0.x + 2 * ((1-t) * t) * p1.x + Math.pow(t, 2) * p2.x;
		double y = Math.pow(1-t, 2) * p0.y + 2 * ((1-t) * t) * p1.y + Math.pow(t, 2) * p2.y;

		return new Point((int) x, (int) y);
	}
	public static final boolean isPointOnCurve(Point p, Point p0, Point p1, Point p2) {
		return isPointOnCurve(p, p0, p1, p2, true);
	}
	public static final boolean isPointOnCurve(Point p, Point p0, Point p1, Point p2, boolean testCurve) {
		double ax = p0.x - 2*p1.x + p2.x;
		double bx = 2*p1.x - 2*p0.x ;
		double cx = p0.x - p.x;
		
		double ay = p0.y - 2*p1.y + p2.y;
		double by = 2*p1.y - 2*p0.y ;
		double cy = p0.y - p.y;
		
		double t = -(cx*ay - cy*ax)/(bx*ay - by*ax);
		//System.out.println(t);
		if (testCurve && (t == Double.NaN || t == Double.NEGATIVE_INFINITY || t == Double.POSITIVE_INFINITY)) {
			return isPointOnCurve(p, p0, new Point((int) p1.getX()-1, (int) p1.getY()+1), p2, false);
		}
		if (t < 0 || t > 1) return false;
		Point q = Bezier.quadratic(p0, p1, p2, t);
		return Math.abs(q.x - p.x) <= ERROR && Math.abs(q.y - p.y) <= ERROR;
	}
	
}
