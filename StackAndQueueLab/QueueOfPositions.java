/*************************************************************************
 * Name        : Keith Vertanen 
 * Username    : kvertanen
 * Description : Queue that can hold positions, uses a linked list that 
 *               tracks the head and tail of the list.
 * Modified by Dalton Caron on 1/24/2018
 *************************************************************************/
public class QueueOfPositions
{	
	private class Node
	{
		private Position pos;
		private Node	 next;
	}
	
	private Node first = null;
	private Node last  = null;
	       
	// Add a new position to the queue
	public void enqueue(Position s)  
	{
		Node node = new Node();
		node.pos = s;
		node.next = null;
		
		if (last != null)
			last.next = node;
		last = node;
		
		if (first == null)
			first = node;
	}
	
	// Remove the least recently added position
	public Position dequeue()
	{
		if (first == null)
			throw new RuntimeException("Queue is empty!");
		Position result = first.pos;				
		first = first.next;
		if (first == null)
			last = null;
		return result;
	}
    
	// Return a string representation of the queue	
	public String toString()
	{
		String result = "";
		Node current = first;
		while (current != null)
		{
			result += current.pos.toString();
			result += " ";
			current = current.next;
		}
		return result;
	}
	
	// Check if the queue is empty
	public boolean isEmpty()          
    {
		return (first == null);
    }
    
	// main method for testing out the class
	/*public static void main(String [] args)
	{
		QueueOfPositions q = new QueueOfPositions();
		
		System.out.println("queue = " + q);
		
		q.enqueue("this");
		System.out.println(q);
		
		System.out.println("dequeue = " + q.dequeue());
		System.out.println(q);
		
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		System.out.println(q);
		
		System.out.println("dequeue = " + q.dequeue());
		System.out.println(q);
		System.out.println("dequeue = " + q.dequeue());
		System.out.println(q);
		System.out.println("dequeue = " + q.dequeue());
		System.out.println(q);		
	}*/	
}