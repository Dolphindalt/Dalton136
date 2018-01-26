/*************************************************************************
 * Name        : Keith Vertanen 
 * Username    : kvertanen
 * Description : Stack that can hold positions, uses a linked list that 
 *               tracks the head and tail of the list.
 * Modified by Dalton Caron on 1/24/2018
 *************************************************************************/
public class StackOfPositions
{	
	private class Node
	{
		private Position	pos;
		private Node	next;
	}
	
	private Node first = null;
	       
	// Add a new position to the queue
	public void push(Position s)  
	{
		Node node = new Node();
		node.pos = s;
		
		if (first == null)
			first = node;
		else
		{
			node.next = first;
			first = node;
		}
	}
	
	// Remove the least recently added position
	public Position pop()
	{
		if (first == null)
			throw new RuntimeException("Stack is empty!");
		Position result = first.pos;				
		first = first.next;
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
		StackOfPositions q = new StackOfPositions();
		
		System.out.println("stack = " + q);
		
		q.push("this");
		System.out.println(q);
		
		System.out.println("pop = " + q.pop());
		System.out.println(q);
		
		q.push("a");
		q.push("b");
		q.push("c");
		System.out.println(q);
		
		System.out.println("pop = " + q.pop());
		System.out.println(q);
		System.out.println("pop = " + q.pop());
		System.out.println(q);
		System.out.println("pop = " + q.pop());
		System.out.println(q);		
	}*/	
}