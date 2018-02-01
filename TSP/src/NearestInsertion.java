/*************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 *  Compilation:  javac NearestInsertion.java
 *  Execution:    java NearestInsertion file.txt
 *  Dependencies: Tour.java Point.java StdDraw.java
 *
 *  Run nearest neighbor insertion heuristic for traveling
 *  salesperson problem and plot results.
 *
 *  % java NearestInsertion tsp1000.txt
 *
 *  Optional integer command-line argument specifies animation delay in ms  
 *  % java NearestInsertion tsp1000.txt 10
 *  
 *************************************************************************/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class runs the nearest insertion heuristic for the traveling
 * salesperson problem.
 * 
 * @author Keith Vertanen
 * @author Michele Van Dyne - file reading modifications
 */
public class NearestInsertion 
{
	/**
	 * Sets up parameters, reads in the data, draws the current result and calls the Tour.insertSmallest method
	 * 
	 * @param args String filename - the name of the file that stores the data
	 *             optional: int - the delay between drawing cycles
	 */
	public static void main(String[] args) 
	{
		String fileName = args[0];
		// check for optional command-line argument that turns on animation
		int delay = -1;
		if (args.length > 1)
			delay = Integer.parseInt(args[1]);

		try 
		{
			Scanner file = new Scanner(new File(fileName));
			// get dimensions
			int w = file.nextInt();
			int h = file.nextInt();
			StdDraw.setCanvasSize(w, h);
			StdDraw.setXscale(0, w);
			StdDraw.setYscale(0, h);

			// turn on animation mode
			StdDraw.show(0);

			// run nearest insertion heuristic
			Tour tour = new Tour();
			while (file.hasNext()) 
			{
				double x = file.nextDouble();
				double y = file.nextDouble();
				Point p = new Point(x, y);
				tour.insertNearest(p);
				if (delay > -1)
				{
					StdDraw.clear();
					tour.draw();
					StdDraw.text(100, 0, "" + tour.distance());
					StdDraw.show(delay);
				}
			}

			// draw to standard draw
			tour.draw();
			StdDraw.show(0);

			// print tour to standard output
			tour.show();
			file.close();
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("File not found.");

		}
	}

}
