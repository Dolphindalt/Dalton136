/**
 * 
 * @author
 *
 */

public class Enemy extends SpaceObject implements Spinnable
{
    private double velX, velY, rotato;

    public Enemy(String image, 
                 double x, 
                 double y, 
                 double radius,
                 double velX, 
                 double velY)
    {
        super(image, x, y, radius);
        this.velX = velX;
        this.velY = velY;
        this.rotato = Math.random() * (360);
    }

    public void rotate()
    {
      if(++rotato > 360)
      {
        rotato = 0;
      }
    }

    public String toString()
    {
        String result = "";

        result += "(" + getX() + ", " + getY() + ") vel (" + velX + ", " + velY + ") r=" + getRadius() + " angle=" + rotato + " " + getImage(); 

        return result;   
    }

    public void draw()
    {
       StdDraw.picture(getX(), getY(), getImage(), rotato); 
    }

    public void updatePos(double x, double y)
    {
        rotate();
        double cx = x + velX;
        double cy = y + velY;
        if(cx < 0.0 || cx > 1.0) velX = -velX;
        if(cy < 0.0 || cy > 1.0) velY = -velY;
        setX(cx);
        setY(cy);
    }   
}
