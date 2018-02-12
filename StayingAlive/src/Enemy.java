
/**
 * The Enemy class is awesome. It stores enimies information,
 * draws them, moves them, nice!
 * 
 * @author Daltn Caron
 * @version 2/11/2018
 */

public class Enemy extends SpaceObject implements Spinnable
{
    private double velX, velY, rotato;

    /**
     * Enemy constructor requires all the information to store an enemy.
     * This Enemy is special, so it has to set its rotato variable.
     * 
     * @param image The string image file name
     * @param x The x position of the enemy
     * @param y The y position of the enemy
     * @param radius The radius of the enemy
     * @param velX The x velocity of the enemy
     * @param velY The y velocity of the enemy 
     */
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

    /**
     * Hey, this method is from the Spinnable interface!
     */
    @Override
    public void rotate()
    {
      if(++rotato > 360)
      {
        rotato = 0;
      }
    }

    /**
     * Returns a string representation of an enemy.
     * 
     * @return The string representation of an enemy
     */
    @Override
    public String toString()
    {
        String result = "";

        result += "(" + getX() + ", " + getY() + ") vel (" + velX + ", " + velY + ") r=" + getRadius() + " angle=" + rotato + " " + getImage(); 

        return result;   
    }

    /**
     * Draws the enemy.
     */
    @Override
    public void draw()
    {
       StdDraw.picture(getX(), getY(), getImage(), rotato); 
    }

    /**
     * Updates the position of the enemy.
     * 
     * Wait, hold up a second.
     * 
     * Why are we passing the enemy its own x and y
     * position when we can use getX() or getY() from
     * the super class instead? Oh wait, we are doing this
     * ONLY because player needs a mouseX and mouseY when
     * it updates its own position. Okay, what the heck. I mean,
     * if you want to store everything in the same Collection or
     * array, this won't even do anything unless you use instanceof
     * on player every iteration over the array which is dumb and a 
     * waste of performance. Why not just seperate these two update
     * methods into two seperate concrete classes rather than forcing
     * inheritance- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAArrrggggggg
     * My game loop is suffering from this :(
     * 
     * @param x The enemies x position
     * @param y The enimies y position
     */
    @Override
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
