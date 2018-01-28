/**
 * I am a fool. I see why this was easy. Before I would choose one route when at a node and
 * this caused many problems such as the start node being popped or dequeued. I have found that
 * just pushing or enqueuing all possible pathways in each node provides dependable results,
 * opposed to the method I used before, we nodes that had three or more different routes would
 * have a route ignored.
 * 
 * This class is my submission for the Lab 3 - Stacks and Queues. I like this class.
 * 
 * @author Dalton Caron
 * @version 1/24/2018
 */
public class Solve 
{

	/**
	 * Standard main method that creates dynamic entry point for the Solve class.
	 * 
	 * @param args args[0] must be an integer that decides the n dimensions of the maze
	 */
	public static void main(String[] args)
	{
		try
		{
			new Solve(Integer.parseInt(args[0]));
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException ex)
		{
			System.err.println("Usage: java Solve [integer value]");
		}
	}
	
	/**
	 * Maze member provided. Not to be touched by anything outside this class.
	 * 
	 * @see Maze
	 */
	private Maze maze;
	
	/**
	 * The counter keeps track of how many pushes or enqueues occur.
	 */
	private int counter;
	
	/**
	 * Constructs the maze member class provided. Draws it, then executes the pathfinder using
	 * the stack and queue solutions.
	 * 
	 * @param n The dimensions of the maze
	 */
	public Solve(int n)
	{
		maze = new Maze(n);
		maze.draw();
		solveStack();
		solveQueue();
		new AStar(maze, n);
	}
	
	/**
	 * Executes the pathfinder solution using a queue
	 */
	private void solveQueue()
	{
		maze.clear();
		counter = 0;
		QueueOfPositions queue = new QueueOfPositions();
		Position current = maze.getStart();
		maze.setVisited(current);
		queue.enqueue(current);
		counter++;
		if(current.equals(maze.getFinish()))
		{
			System.out.println("Amount of enqueues to solve the maze with a queue: " + counter);
			return;
		}
		while(!current.equals(maze.getFinish()))
		{
			if(!current.equals(maze.getStart()))
				current.draw(StdDraw.GREEN);
			
			processNode(queue, current);
			
			if(!current.equals(maze.getStart()))
				current.draw(StdDraw.LIGHT_GRAY);
			current = queue.dequeue();
			if(current.equals(maze.getFinish()))
			{
				System.out.println("Amount of enqueues to solve the maze with a queue: " + counter);
				return;
			}
		}
	}
	
	/**
	 * Process a node by enqueuing all nodes adjacent to our current node that have not
	 * be visited or are not seperated by a wall.
	 * 
	 * @param queue The queue to store positions
	 * @param current The current position, used in processing
	 */
	private void processNode(QueueOfPositions queue, Position current)
	{
		if(maze.openNorth(current))
		{
			Position tmp = new Position(current.getX(), current.getY() + 1);
			if(!maze.visited(tmp))
			{
				maze.setVisited(tmp);
				queue.enqueue(tmp);
				counter++;
			}
			
		}
		if(maze.openWest(current))
		{
			Position tmp = new Position(current.getX() - 1, current.getY());
			if(!maze.visited(tmp))
			{
				maze.setVisited(tmp);
				queue.enqueue(tmp);
				counter++;
			}
		}
		if(maze.openEast(current))
		{
			Position tmp = new Position(current.getX() + 1, current.getY());
			if(!maze.visited(tmp))
			{
				maze.setVisited(tmp);
				queue.enqueue(tmp);
				counter++;
			}
		}
		if(maze.openSouth(current))
		{
			Position tmp = new Position(current.getX(), current.getY() - 1);
			if(!maze.visited(tmp))
			{
				maze.setVisited(tmp);
				queue.enqueue(tmp);
				counter++;
			}
		}
	}
	
	/**
	 * Executes the pathfinder solution using a stack.
	 */
	private void solveStack()
	{
		maze.clear();
		counter = 0;
		StackOfPositions stack = new StackOfPositions();
		Position current = maze.getStart();
		maze.setVisited(current);
		stack.push(current);
		counter++;
		while(!current.equals(maze.getFinish()))
		{
			if(!current.equals(maze.getStart()))
				current.draw(StdDraw.BLUE);
			
			processNode(stack, current);
			
			if(!current.equals(maze.getStart()))
				current.draw(StdDraw.GRAY);
			current = stack.pop();
			if(current.equals(maze.getFinish()))
			{
				System.out.println("Amount of pushes to solve the maze with a stack: " + counter);
				return;
			}
		}
	}
	
	/**
	 * Process a node by pushing all nodes adjacent to our current node that have not
	 * be visited or are not seperated by a wall.
	 * 
	 * @param stack The stack to store the positions
	 * @param current The current position, to be used in processing
	 */
	private void processNode(StackOfPositions stack, Position current)
	{
		if(maze.openNorth(current))
		{
			Position tmp = new Position(current.getX(), current.getY() + 1);
			if(!maze.visited(tmp))
			{
				maze.setVisited(tmp);
				stack.push(tmp);
				counter++;
			}
			
		}
		if(maze.openWest(current))
		{
			Position tmp = new Position(current.getX() - 1, current.getY());
			if(!maze.visited(tmp))
			{
				maze.setVisited(tmp);
				stack.push(tmp);
				counter++;
			}
		}
		if(maze.openEast(current))
		{
			Position tmp = new Position(current.getX() + 1, current.getY());
			if(!maze.visited(tmp))
			{
				maze.setVisited(tmp);
				stack.push(tmp);
				counter++;
			}
		}
		if(maze.openSouth(current))
		{
			Position tmp = new Position(current.getX(), current.getY() - 1);
			if(!maze.visited(tmp))
			{
				maze.setVisited(tmp);
				stack.push(tmp);
				counter++;
			}
		}
	}
	
}
