import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * "All our knowledge begins with the senses, 
 * proceeds then to the understanding, 
 * and ends with reason. 
 * There is nothing higher than reason."
 * - Immanuel Kant
 * 
 * Well that was a wild ride. I now understand why Trevor Brooks says he hates multi threading. A lot
 * of problems that I had could have been prevented if I understand Java's standard BufferedReader and
 * PrintWriter classes better. I had to go to the documentation and check specifically for thread safety.
 * I tried to use functional programming in this assignment, but Java is Java. Oh well. It was fun while
 * it lasted. I want more of this stuff.
 * 
 * @author Dalton Caron
 * @version 3/2/2018
 */
public class LineClient 
{

	/**
	 * Dynamic entry point for client class and passes arguments.
	 * 
	 * @param args [server name] [server port] [<red, 0-255> <green, 0-255> <blue, 0-255>]
	 */
	public static void main(String[] args)
	{
		new LineClient(args);
	}
	
	private String HOSTNAME = "127.0.0.1";
	private int PORT = 5000, R = 0, G = 0, B = 0;
	
	private final Lines LINES;
	
	private int currentlyConnected, numberOfLines;
	
	private Socket connection;
	private BufferedReader reader;
	private PrintWriter writer;
	
	private volatile boolean running; // these threads need to read the same boolean, not CPU cache garbage 
	private Thread getThread, dataThread, clearThread, quitThread, mouseThread;
	
	/**
	 * The constructor processes the arguments in a (((match))) statement and calls
	 * all other helper functions in the class to run the client.
	 * 
	 * @param args [server name] [server port] [<red, 0-255> <green, 0-255> <blue, 0-255>]
	 */
	public LineClient(final String[] args)
	{
		switch(args.length) // (((match))) statement
		{
		
		case 5:
			HOSTNAME = args[0];
			PORT = new Integer(args[1]);
			R = parseColorInteger(args[2]);
			G = parseColorInteger(args[3]);
			B = parseColorInteger(args[4]);
			break;
			
		case 2:
			HOSTNAME = args[0];
			PORT = new Integer(args[1]);
			R = (int) (Math.random() * 255);
			B = (int) (Math.random() * 255);
			G = (int) (Math.random() * 255);
			break;
			
		case 1:
			HOSTNAME = args[0];
			R = (int) (Math.random() * 255);
			B = (int) (Math.random() * 255);
			G = (int) (Math.random() * 255);
			break;
			
		case 0:
			R = (int) (Math.random() * 255);
			B = (int) (Math.random() * 255);
			G = (int) (Math.random() * 255);
			break;
			
		default:
			System.out.println("LineClient [server name] [server port] [<red, 0-255> <green, 0-255> <blue, 0-255>]");
			System.exit(0);
			
		}
		
		LINES = new Lines();
		
		getConnected();
		createSocketTools();
		
		running = true;
		
		spawnGetThread();
		spawnRequestDataThread();
		spawnWritingThreads();
		loop();
	}
	
	/**
	 * Init our socket connection and handle errors.
	 */
	private void getConnected()
	{
		try 
		{
			connection = new Socket(HOSTNAME, PORT);
		} 
		catch (UnknownHostException e) 
		{
			System.out.println("Failed to find the remote host");
			System.exit(1);
		} 
		catch (IOException e) 
		{
			System.out.println("Failed to connect to the server");
			System.exit(1);
		}
	}
	
	/**
	 * Init our Buffered Reader and PrintWriter while handling errors.
	 */
	private void createSocketTools()
	{
		try 
		{
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			writer = new PrintWriter(connection.getOutputStream(), true);
		} 
		catch (IOException e) 
		{
			System.out.println("Failed to init socket tools");
			System.exit(1);
		}
	}
	
	/**
	 * The get thread processes all reading of data from the server.
	 */
	private void spawnGetThread()
	{
		getThread = new Thread(() -> 
		{
			while(running && !connection.isClosed())
			{
				try 
				{
					final String response = reader.readLine();
					final String[] split = response.trim().split(" ");
						
					switch(split[0])
					{
					
					case "OK":
						break;
						
					default:
						numberOfLines = new Integer(split[0]);
						currentlyConnected = new Integer(split[1]);
							
						LINES.clear();
							
						int i = 2;
						for(; i < split.length; i += 7)
						{
							final float x0 = new Float(split[i]);
							final float y0 = new Float(split[i+1]);
							final float x1 = new Float(split[i+2]);
							final float y1 = new Float(split[i+3]);
							final int r = new Integer(split[i+4]);
							final int g = new Integer(split[i+5]);
							final int b = new Integer(split[i+6]);
								
							LINES.addLine(
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
						}
						break;
						
					}
				}
				catch(NullPointerException e)
				{
					terminate();
				}
				catch (IOException e) 
				{
					return; // streams are closed, so end thread
				}
			}
		});
		getThread.start();
	}
	
	/**
	 * The request thread sends get requests to the server to stay
	 * updated every 100ms, as the assignment dictates.
	 */
	private void spawnRequestDataThread()
	{
		dataThread = new Thread(() ->
		{
			while(running && !connection.isClosed())
			{
				writer.println("GET");
				writer.flush();
				sleep(100);
			}
		});
		dataThread.start();
	}
	
	/**
	 * This method contains a lump of three threads, declared as sexy lambdas,
	 * to handle all input related activities very quickly and concurrently.
	 */
	private void spawnWritingThreads()
	{
		quitThread = new Thread(() -> 
		{
			while(running && !connection.isClosed())
			{
				if(StdDraw.isKeyPressed(KeyEvent.VK_Q))
				{
					running = false;
					writer.println("QUIT");
					writer.flush();
					sleep(500);
				}
			}
		});
		
		clearThread = new Thread(() ->
		{
			while(running && !connection.isClosed())
			{
				if(StdDraw.isKeyPressed(KeyEvent.VK_C))
				{
					LINES.clear();
					writer.println("CLEAR");
					writer.flush();
					sleep(500);
				}
			}
		});
		
		mouseThread = new Thread(() -> 
		{
			while(running && !connection.isClosed())
			{
				if(StdDraw.mousePressed())
				{
					final float x0 = (float) StdDraw.mouseX(), y0 = (float) StdDraw.mouseY();
					while(StdDraw.mousePressed()) {} // wait for the user to unclick
					final float x1 = (float) StdDraw.mouseX(), y1 = (float) StdDraw.mouseY();
	
					LINES.addLine(
						new LineBuilder()
						.setX0(x0)
						.setY0(y0)
						.setX1(x1)
						.setY1(y1)
						.setR(R)
						.setG(G)
						.setB(B)
						.build()
					);
					
					writer.println(new StringBuilder().append("ADD ") // send an add command
						.append(x0).append(' ')
						.append(y0).append(' ')
						.append(x1).append(' ')
						.append(y1).append(' ')
						.append(R).append(' ')
						.append(G).append(' ')
						.append(B)
						.toString());
					writer.flush();
				}
			}
		});
		
		quitThread.start();
		clearThread.start();
		mouseThread.start();
	}
	
	/**
	 * Repeated code is evil, but anything without zero overhead is also evil.
	 * 
	 * @param long millis sleep time
	 */
	private void sleep(final long millis)
	{
		try 
		{
			Thread.sleep(millis);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * This loop is designed to run on the main thread to draw the program.
	 */
	private void loop()
	{
		while(running && !connection.isClosed())
		{
			draw();
		}
		
		terminate();
	}
	
	/**
	 * This is how you safely terminate the client.
	 */
	private void terminate()
	{
		writer.close();
		
		try 
		{
			reader.close();
			connection.close();
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		try 
		{
			getThread.join();
			dataThread.join();
			quitThread.join();
			clearThread.join();
			mouseThread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		System.exit(0); // kill std draw
	}
	
	/**
	 * Standard Draw function for the client.
	 */
	private void draw()
	{
		StdDraw.clear();

		LINES.draw();
		
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(0.2, 0.9, "Lines: " + numberOfLines);
		StdDraw.text(0.2, 0.8, "Connected: " + currentlyConnected);
		
		StdDraw.show(10);
	}
	
	/**
	 * Make sure the int we parse is a valid rgb value.
	 * 
	 * @param str The string to parse the number from
	 * @return The int parsed from the string
	 */
	private static int parseColorInteger(final String str)
	{
		final int parsed = new Integer(str);
		return parsed < 0 ? 0 : parsed > 255 ? 255 : parsed;
	}
	
}
