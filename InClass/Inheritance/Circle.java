import java.awt.Graphics;

/**
 * The circle class.
 * 
 * @author Dalton Caron
 * @version 1/3/2018
 */
public class Circle extends Shape
{

    /**
     * The circle construtor.
     * 
     * @param x X pos
     * @param y Y pos
     * @param width Width of shape
     * @param height Height of shape
     */
    public Circle(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }

    /**
     * Draws a circle.
     * 
     * @param g Graphics to draw with
     */
    public void draw(Graphics g)
    {
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }

}