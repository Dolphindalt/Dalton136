/**
 * This class was orginaly part of LinkedList but was moved due to all work
 * being done in the default package, which caused me some problems.
 *
 * @author Dalton Caron
 * @version 2/1/2018
 */
public class LinkedNode<U>
{
	/** The generic type value of the node. */
	public U value;
	/** The LinkedNode representing the next node in the list. */
	public LinkedNode<U> next;
	
	/**
	 * Constructs a linked node with a given generic value.
	 *
	 * @param U The generic to construct the linked node with
	 */	
	public LinkedNode(U value)
	{
		this.value = value;
		next = null;
	}
}
