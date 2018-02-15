/**
 * <i>Behold</i>, the power of recursion. Not much to say about
 * this lab in particular. Was fun. I could have multiplied sqrt
 * 3 / 2 with the side and added that to the y value of the new
 * triangles for better result, but I shall refrain so my program
 * is more unique. 
 * 
 * @author Dalton Caron
 * @version 2/14/2018
 */
public class Sierpinski
{

	/**
	 * Entry point for the program, repsonsible for taking a single int input
	 * and drawing the outline of the big triangle. Also calls recursive draw.
	 * 
	 * @params args We use args[0] for the number N of recursions
	 */
	public static void main(String[] args)
	{
		final int N;
		try
		{
			N = Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			System.err.println("Usage: java Sierpinski [number]");
			return;
		}
		
		double[] x = { 0.0, 1.0, (1.0/2.0) };
		double[] y = { 0.0, 0.0, Math.sqrt(3.0)/2.0 };
		StdDraw.polygon(x, y); // drawing that outline triangle
		
		sierpinski(N, 0.5, 0.0, Math.abs(3.0/4.0 - 1.0/4.0));
	}
	
	/**
	 * It is kind of a pain that we have to calculate triangle vertices as
	 * arrays each time, but it is not bad and reminds me of OPENGL, where
	 * everything can be stored in a vbo.
	 * 
	 * @param x Bottom x point to draw a triangle from
	 * @param y Bottom y point to draw a triangle from
	 * @param s The side length of the triangle to draw
	 */
	public static void filledTriangle(double x, double y, double s)
	{
		
		double[] xbuffer = new double[3];
		double[] ybuffer = new double[3];
		xbuffer[0] = x;
		ybuffer[0] = y;
		xbuffer[1] = x - s/2.0; 
		ybuffer[1] = y + getTriangleCenterLine(x, y, s);
		xbuffer[2] = x + (s/2.0);
		ybuffer[2] = y + getTriangleCenterLine(x, y, s); // I could rewrite this to be an array literal
		StdDraw.filledPolygon(xbuffer, ybuffer);
	}
	
	/**
	 * This is where the magic happens. We draw one triangle, then a
	 * few other triangles until we want to stop.
	 * 
	 * @param n The number of iterations to draw triangles
	 * @param x The x point of the triangle to draw and find new triangles from
	 * @param y The y point of the triangle to draw and find new triangles from
	 */
	public static void sierpinski(int n, double x, double y, double s)
	{
		filledTriangle(x, y, s);
		if(--n <= 0) return;
		
		sierpinski(n, x - (s/2.0), y, s/2.0);
		sierpinski(n, x + (s/2.0), y, s/2.0);
		sierpinski(n, x, y + getTriangleCenterLine(x, y, s), s/2.0);
	}
	
	private static double getTriangleCenterLine(double x, double y, double s)
	{
		double x1 = s/2.0;
		double line = Math.pow(s, 2) - Math.pow(x1, 2);
		return Math.sqrt(line);
	}
	
}
