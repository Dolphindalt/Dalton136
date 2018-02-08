/**
 * 
 * @author
 *
 */

public class Player extends SpaceObject
{
    private int speed;

    public Player(String imageFilename, double x, double y, double radius, int speed)
    {
        super(imageFilename, x, y, radius);
        this.speed = speed;
    }

    public void draw()
    {
        StdDraw.picture(getX(), getY(), getImage());
    }

    public void updatePos(double mouseX, double mouseY)
    {
        setX(getX() + ((mouseX - getX()) / speed));
        setY(getY() + ((mouseY - getY()) / speed));
    }

    public boolean intersects(Enemy enemy)
    {
        double distanceBetween = Math.sqrt(Math.pow(getX() - enemy.getX(), 2) + Math.pow(getY() - enemy.getY(), 2));
        return (distanceBetween < enemy.getRadius() + getRadius());
    }

    public String toString()
    {
        String result = "";
        result += "(" + getX() + ", " + getY() + ") r=" + getRadius() + " speed=" + speed + " " + getImage(); 
        return result;
    }

}
