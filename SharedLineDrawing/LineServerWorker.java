import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * This class is kickass. There is so much discrete magic in here,
 * I love it. Pay special attention to the use of the parallel streams
 * and other functional concepts to the best of my ability.
 * (Most of these were deleted due to threading problems)
 * 
 * @author Dalton Caron
 * @version 3/2/2018
 *
 */
public class LineServerWorker implements Runnable
{

	private volatile Lines lines; // avoid CPU cache problems, maybe -java
	
	private final Socket connection;
	
	/**
	 * Constructs a thread for the user. Dies on disconnect.
	 * 
	 * @param connection The client-server connection
	 * @param lines The lines object to work with
	 */
	public LineServerWorker(Socket connection, Lines lines)
	{
		this.connection = connection;
		this.lines = lines;
		lines.incrementClients();
		System.out.println("CONNECTIONS: " + lines.getClients());
	}

	/**
	 * Main thread worker loop for a single client. Reads and writes to
	 * the client and handles the connection.
	 */
	@Override
	public void run()
	{
		BufferedReader reader = null; // why java, null is bad
		PrintWriter writer = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			writer = new PrintWriter(connection.getOutputStream(), true); // true to auto flush
			
			for(;;)
			{
				final String command = reader.readLine();
				final String[] split = command.trim().split(" ");
					
				switch(split[0])
				{
					
				case "GET":
					final String output = lines.toString();
					writer.println(output);
					writer.flush();
					break;
						
				case "CLEAR":
					lines.clear();
					writer.println("OK");
					writer.flush();
					break;
						
				case "ADD":
					final float x0 = new Float(split[1]);
					final float y0 = new Float(split[2]);
					final float x1 = new Float(split[3]);
					final float y1 = new Float(split[4]);
					final int r = new Integer(split[5]);
					final int g = new Integer(split[6]);
					final int b = new Integer(split[7]);
						
					lines.addLine(
						new LineBuilder()
						.setX0(x0)
						.setY0(y0)
						.setX1(x1)
						.setY1(y1)
						.setR(r)
						.setG(g)
						.setB(b)
						.build()
					);
						
					writer.println("OK");
					writer.flush();
					break;
						
				case "QUIT":
					writer.println("OK");
					writer.flush();
					throw new DisconnectedException();
					
				default:
					break;
						
				}
			}
		}
		catch(DisconnectedException e)
		{
			System.out.println("GOODBYE: " + connection.toString());
		}
		catch(SocketException e)
		{
			System.out.println("HANGUP: " + connection.toString());
		}
		catch(IOException | NullPointerException e)
		{
			System.out.println("HANGUP: " + connection.toString()); // end of file, null
		}
		finally // close everything no matter what happens
		{
			try
			{
				writer.close();
				reader.close();
				connection.close();
			} 
			catch (IOException e) {} // if this happens, you are fucked, sorry
			
			lines.decrementClients();
			System.out.println("CONNECTIONS: " + lines.getClients());
		}
	}
	
	/**
	 * This exception is thrown to trigger a disconnect message and exit from
	 * the reading loop.
	 * 
	 * @author Dalton Caron
	 * @version 3/2/2018
	 */
	private class DisconnectedException extends Exception
	{
		private static final long serialVersionUID = 2133L;
		
		/**
		 * Default constructor declared for clarity.
		 */
		public DisconnectedException()
		{
			super();
		}
	}
	
}
