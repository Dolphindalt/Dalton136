import java.awt.Graphics;

/**
 * The rectangle class.
 * 
 * @author Dalton Caron
 * @version 1/3/2018
 */
public class Rectangle extends Shape
{

    /**
     * The rectangle construtor.
     * 
     * @param x X pos
     * @param y Y pos
     * @param width Width of shape
     * @param height Height of shape
     */
    public Rectangle(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }

    /**
     * Draws a rectangle.
     * 
     * @param g Graphics to draw with
     */
    public void draw(Graphics g)
    {
        g.fillRect(getX(), getY(), getWidth(), getWidth());
    }

}