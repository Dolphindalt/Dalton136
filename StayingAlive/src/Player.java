/**
 * Class representation of a Player which handles logic and rendering.
 * 
 * @author Dalton Caron
 * @version 2/11/2018
 */
public class Player extends SpaceObject
{
    private int speed;

    /**
     * Constructs a player, should only be called once.
     * 
     * @param imageFilename The string path to the player image file
     * @param x The player's x cooridnate
     * @param y The player's y coordinate
     * @param radius The radius of the player
     * @param speed The speed of the player 
     */
    public Player(String imageFilename, double x, double y, double radius, int speed)
    {
        super(imageFilename, x, y, radius);
        this.speed = speed;
    }

    /**
     * Draws the player.
     */
    @Override
    public void draw()
    {
        StdDraw.picture(getX(), getY(), getImage());
    }

    /**
     * Updates the position of the player relative to mouse position.
     * 
     * @param mouseX The mouse x position
     * @param mouseY The mouse y position
     */
    @Override
    public void updatePos(double mouseX, double mouseY)
    {
        setX(getX() + ((mouseX - getX()) / speed));
        setY(getY() + ((mouseY - getY()) / speed));
    }

    /**
     * Collision detection for the game. Player-Enemy collisions only.
     * 
     * @param enemy The Enemy to test a collision with
     * @return true if a collision occurs, false otherwise
     */
    public boolean intersects(Enemy enemy)
    {
        double distanceBetween = Math.sqrt(Math.pow(getX() - enemy.getX(), 2) + Math.pow(getY() - enemy.getY(), 2));
        return (distanceBetween < enemy.getRadius() + getRadius());
    }

    /**
     * Returns a string representation of the player.
     * 
     * @return The string representation of the player
     */
    @Override
    public String toString()
    {
        String result = "";
        result += "(" + getX() + ", " + getY() + ") r=" + getRadius() + " speed=" + speed + " " + getImage(); 
        return result;
    }

}
