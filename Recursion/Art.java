import java.awt.Color;

/**
 * Welcome to the art. You will find nothing but trees here.
 * This program takes an argument, N, the number of iterations.
 * USE 10 FOR THE BEST RESULTS!!! A lot of this is randomness.
 * 
 * @author Dalton Caron
 * @version 2/14/2018
 */
public class Art
{

	/**
	 * The special main method. Looks a lot like my main method
	 * from Sierpinski, well, I copied and pasted it.
	 * 
	 * @param args We use args[0] for the number N of recursions
	 */
	public static void main(String[] args)
	{
		final int N;
		try
		{
			N = Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			System.err.println("Usage: java Sierpinski [number]");
			return;
		}
		new Art(N);
	}

	/**
	 * The simple constructor sets a random draw color and draws
	 * up a tree recursively.
	 * 
	 * @param N The number of times to recurse through the tree
	 */
	public Art(final int N)
	{
		StdDraw.setPenColor(new Color((int)(Math.random() * 255), 
			(int)(Math.random() * 255), (int)(Math.random() * 255)));

		tree(N, new double[] { 0.5, 0.0 }, 0.2, 0.7, 
			Math.toRadians(Math.random() * (105 - 75) + 75), 
			Math.toRadians(Math.random() * (20 - 16) + 16));
	}

	/**
	 * MAGIC
	 * 
	 * Not really. We just draw a line, but every time we do, we
	 * change the angles and length just a little bit. That was
	 * my original plan until I added some randomness to make
	 * the trees look more natural.
	 */
	private void tree(int iteration, final double[] position, final double length, 
		final double compression, final double theta, final double thetaChange)
	{
		if(iteration-- == 0) 
			return;

		double[] newPosition =
		{
			position[0] + length * Math.cos(theta),
			position[1] + length * Math.sin(theta)
		};

		StdDraw.line(position[0], position[1], newPosition[0], newPosition[1]);

		double newLength = compression * length;

		tree(iteration, newPosition, (Math.random() + 0.5) * newLength, compression, theta + thetaChange, Math.toRadians(Math.random() * (40 - 16) + 16));
		tree(iteration, newPosition, (Math.random() + 0.5) * newLength, compression, theta - thetaChange, Math.toRadians(Math.random() * (40 - 16) + 16));

		StdDraw.show(3);
	}

}
