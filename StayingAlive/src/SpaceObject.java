/**
 * An abstract class to define objects that are present in our
 * space world.
 * 
 * MODIFIED BY DALTON CARON, PLEASE USE THIS WHEN GRADING
 *
 * @author Michele Van Dyne
 * @version 2/7/2018
 */
public abstract class SpaceObject {

	private double  x               = 0.0;
	private double  y               = 0.0;
	private double  radius          = 0.0;
	private String  image           = "";

	/**
	 * Constructor for the SpaceObject
	 * 
	 * @param image the file name of the image associated with this object
	 * @param x the object's x position
	 * @param y the object's y position
	 * @param radius the object's radius
	 */
	public SpaceObject(String image, 
			double x, 
			double y, 
			double radius)
	{
		this.x          = x;
		this.y          = y;
		this.radius     = radius;
		this.image      = image;
	}

	/**
	 * Accessor for the x location
	 * 
	 * @return the x location of the object
	 */
	public double getX() {
		return x;
	}

	/**
	 * Mutator for the x position
	 * 
	 * @param x the new value for the x position
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Accessor for the y location
	 * 
	 * @return the y location of the object
	 */
	public double getY() {
		return y;
	}

	/**
	 * Mutator for the y position
	 * 
	 * @param y the new value for the y position
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Accessor for the radius
	 * 
	 * @return the radius of the object
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Mutator for the radius
	 * 
	 * @param radius the new value for the radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Accessor for the image file name
	 * 
	 * @return the image file name of the object
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Mutator for the image file name
	 * 
	 * @param radius the new value for the image file name
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Abstract method to update the position of the object in space
	 * 
	 * @param x the current x location of the object
	 * @param y the current y location of the object
	 */
	public abstract void updatePos(double x, double y);

  /*******************************************************************
   * Abstract method for drawing everything we want.
   */
  public abstract void draw();
}
