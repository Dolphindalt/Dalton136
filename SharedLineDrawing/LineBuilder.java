/**
 * The LineBuilder class has been created so I can
 * use functional programming and have an immutable
 * line class, which is useful I guess.
 * 
 * @author Dalton Caron
 * @version 2/28/2018
 */
public class LineBuilder
{
		
	private float x0, y0, x1, y1;
	private int r, g, b;
		
	public Line build()
	{
		return new Line(x0, y0, x1, y1, r, g, b);
	}
		
	public LineBuilder setX0(float x0)
	{
		this.x0 = x0;
		return this;
	}
		
	public LineBuilder setY0(float y0)
	{
		this.y0 = y0;
		return this;
	}
		
	public LineBuilder setX1(float x1)
	{
		this.x1 = x1;
		return this;
	}
		
	public LineBuilder setY1(float y1)
	{
		this.y1 = y1;
		return this;
	}
		
	public LineBuilder setR(int r)
	{
		assert(r >= 0 && r <= 255);
		this.r = r;
		return this;
	}
		
	public LineBuilder setG(int g)
	{
		assert(g >= 0 && g <= 255);
		this.g = g;
		return this;
	}
		
	public LineBuilder setB(int b)
	{
		assert(b >= 0 && b <= 255);
		this.b = b;
		return this;
	}
		
}