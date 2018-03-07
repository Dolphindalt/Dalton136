import java.util.LinkedList;

/**
 * Lines job is to store a collection of Line objects. I wanted
 * to make this a static class, but the toString method can not
 * be made static. Feels bad man.
 * 
 * The client and server both make strange uses of this class.
 * 
 * @author Dalton Caron
 * @version 3/2/2018
 */
public class Lines 
{

	private volatile LinkedList<Line> lines;
	private volatile int numberOfClients = 0;
	
	/**
	 * Constructs the List of Line.
	 */
	public Lines()
	{
		lines = new LinkedList<Line>();
	}
	
	public LinkedList<Line> getLines()
	{
		return lines;
	}
	
	/**
	 * Adds a line to the collection of lines.
	 * 
	 * @param line The line to add
	 */
	public synchronized void addLine(Line line)
	{
		lines.add(line);
	}
	
	/**
	 * Returns the amount of lines in the collection.
	 * 
	 * @return The amount of lines as an int
	 */
	public int size()
	{
		synchronized (lines)
		{
			return lines.size();
		}
	}
	
	/**
	 * Clears the collections of lines.
	 */
	public void clear()
	{
		synchronized (lines)
		{
			lines.clear();
		}
	}
	
	/**
	 * A single line of data representing all of the lines.
	 * 
	 * @return String representation of the data
	 */
	@Override
	public synchronized String toString()
	{
		synchronized (lines)
		{
			StringBuilder sb = new StringBuilder()
				.append(lines.size()).append(' ')
				.append(numberOfClients).append(' ');
			for(int i = size()-1; i >= 0; i--)
			{
				sb.append(lines.get(i).toString()).append(' ');
			}
			return sb.toString();
		}
	}
	
	/**
	 * Increments the number of clients by one.
	 */
	public synchronized void incrementClients()
	{
		numberOfClients++;
	}
	
	/**
	 * Decrements the number of clients by one.
	 */
	public synchronized void decrementClients()
	{
		numberOfClients--;
	}
	
	/**
	 * How many clients are connected?
	 * 
	 * @return An int representing the number of clients connected
	 */
	public int getClients()
	{
		return numberOfClients;
	}
	
	/**
	 * Safely draw the contents of the lines array to the screen.
	 */
	public void draw()
	{
		synchronized (lines)
		{
			for(int i = lines.size()-1; i >= 0; i--)
			{
				lines.get(i).draw();
			}
		}
	}
	
}
