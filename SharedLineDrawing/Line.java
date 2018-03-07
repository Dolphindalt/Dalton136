/**
 * The Line class represents a line. Pretty straight forward.
 * Not really though. Notice that this entire class is immutable.
 * I also never directly call the constructor except from the
 * LineBuilder class, as the BecomingFunctional book told me to do.
 * 
 * @author Dalton Caron
 * @version 2/28/2018
 */
public class Line 
{
	
	private final float x0, y0, x1, y1;
	private final int r, g, b;
	
	/**
	 * Creates a line between (x0, y0) and (x1, y1) with the given rgb color.
	 * 
	 * @param x0 Point 0's x
	 * @param y0 Point 0's y
	 * @param x1 Point 1's x
	 * @param y1 Point 1's y
	 * @param r Red color between 0 and 255
	 * @param g Green color between 0 and 255
	 * @param b Blue color between 0 and 255
	 */
	public Line(float x0, float y0, float x1, float y1, int r, int g, int b)
	{
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * Draws the line using StdDraw.
	 */
	public void draw()
	{
		StdDraw.setPenColor(r, g, b);
		StdDraw.line(x0, y0, x1, y1);		
	}
	
	/**
	 * String representation of the line, format "x0 y0 x1 y1 r g b".
	 * 
	 * @return The String representation
	 */
	public String toString()
	{
		assert(g >= 0 && g <= 255);
		return new StringBuilder()
		.append(x0).append(' ').append(y0).append(' ')
		.append(x1).append(' ').append(y1).append(' ')
		.append(r).append(' ').append(g).append(' ').append(b)
		.toString();
	}
	
}
