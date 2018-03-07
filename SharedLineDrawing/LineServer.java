import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * I really wanted to separate a lot of this into different methods 
 * but I must follow the assignments instructions of just using main. 
 * It kind of messed up my functional concepts. Oh well.
 * 
 * @author Dalton Caron
 * @version 3/2/2018
 */
public class LineServer 
{
	
	private static int port = 5000;
	
	private static final Lines lines = new Lines();
	
	/**
	 * The server for the line drawing application. Parse arguments
	 * and open a server connection, listens for clients to connect
	 * and spawn threads for them when they join in.
	 * 
	 * @param args The port to listen on as arg 0
	 */
	public static void main(String[] args)
	{
		if(args.length >= 1)
		{
			try
			{
				port = new Integer(args[0]);
			}
			catch(NumberFormatException e) {} // ignore and run on port 5000
		}
		
		System.out.print("Booting up server... ");
		
		ServerSocket serverSocket;
		try 
		{
			serverSocket = new ServerSocket(port);
			
			System.out.print("done!\n");
			
			for(;;)
			{
				Socket connection = serverSocket.accept();
				
				System.out.println("HELLO: " + connection.toString());
				
				Thread thread = new Thread(new LineServerWorker(connection, lines));
				thread.start();
			}
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Shuting down...");
		
	}
	
}
