import java.awt.Graphics;

/**
 * The shape class.
 * 
 * @author Dalton Caron
 * @version 1/3/2018
 */
public abstract class Shape
{
    private int x, y, width, height;

    /**
     * The shape construtor.
     * 
     * @param x X pos
     * @param y Y pos
     * @param width Width of shape
     * @param height Height of shape
     */
    public Shape(int x, int y, int width, int height) 
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Function to be declared in sub classes.
     * 
     * @param g Graphics to draw with
     */
    public abstract void draw(Graphics g);

    /**
     * The X getter.
     * 
     * @return x The X value
     */
    public int getX()
    {
        return x;
    }

    /**
     * The Y getter.
     * 
     * @return The Y value
     */
    public int getY()
    {
        return y;
    }

    /**
     * The width getter.
     * 
     * @return The width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * The height getter.
     * 
     * @return The height
     */
    public int getHeight()
    {
        return height;
    }

}