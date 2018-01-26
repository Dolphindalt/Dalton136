/**
 * Defines the class for a position (in this case, in a 2D maze).
 * DO NOT CHANGE THIS CODE
 * 
 * @author Michele Van Dyne
 */
import java.awt.Color;

public class Position {

	private int x;
	private int y;
	
	/**
	 * Constructor for the Position class
	 * 
	 * @param x the x position
	 * @param y the y position
	 */
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Accessor for the x position
	 * 
	 * @return the x position
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Accessor for the y position
	 * 
	 * @return the y position
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Draws the position as a circle
	 * 
	 * @param color the color that should be used to draw the position
	 */
	public void draw(Color color)
	{
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);
	}
	
	/**
	 * Checks to see if two positions are at the same location
	 * 
	 * @param p the position that "this" should be compared to
	 * @return true if they are at the same location, false otherwise
	 */
	public boolean equals(Position p)
	{
		if (p.getX() == x && p.getY() == y)
			return true;
		return false;
	}
	
	/**
	 * Method for converting a position to a String for printing
	 * 
	 * @return the String representation of the Position
	 */
	public String toString()
	{
		return "" + x + ", " + y + "\n";
	}
}