/**
 * The linked list for storing many different kinds of data.
 */ 
public class LinkedList<T>
{
	private int size;
	private LinkedNode<T> head, tail;
	
	/**
	 * Create an empty linked list.
	 */ 
	public LinkedList()
	{
		size = 0;
		head = null;
	}
	
	/**
	 * Simply returns the size of the linked list.
	 *
	 * @return The size of the linked list
	 */ 
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Get a linked node at an index. Index should be less than the size of the linked list.
	 *
	 * @param index An int value representing the position of the linkednode you wish to return
	 * @return the linkednode at the index specified
	 */ 
	public LinkedNode<T> get(int index)
	{
		if(index >= size)
			throw new RuntimeException("Invalid linked list index access");
		LinkedNode<T> current = head;
		for(int i = 0; i < index; i++)
		{
			current = current.next;
		}
		return current;
	}
	
	/**
	 * Removes the linked node, at the index specified, from the list.
	 *
	 * @param index The index of the node to remove; should be less than the size of the linked list
	 */ 
	public void remove(int index)
	{
		if(index >= size)
			throw new RuntimeException("Invalid linked list index access");
		LinkedNode<T> current = head;
		
		if(index+1 == size)
		{;
			for(int i = 0; i < size-2; i++)
			{
				current = current.next;
			}
			current.next = head;
			tail = current;
			size--;
			return;
		}
		
		for(int i = 0; i < index-1; i++)
		{
			current = current.next;
		}
		current.next = current.next.next;
		size--;
	}
	
	/**
	 * The same as an add() function in the java implementation of linked list. Adds a linkednode to the end of the linked list.
	 *
	 * @param thing The generic data type to package into a linkednode and add to the list
	 */ 
	public void pushBack(T thing)
	{
		LinkedNode<T> current = head;
		if(current == null)
		{
			head = new LinkedNode<T>(thing);
			tail = head;
			head.next = tail;
			tail.next = head;
			size++;
			return;
		}
		while(current != tail)
			current = current.next;
		current.next = new LinkedNode<T>(thing);
		tail = current.next;
		tail.next = head;
		size++;
	}
	
	/**
	 * Insert a node behind the node at that index.
	 *
	 * @param index The index to insert the node behind
	 * @param thing Generic type to package as a linkednode and insert into the linked list
	 */ 
	public void insertAt(int index, T thing)
	{
		if(index > size)
			throw new RuntimeException("Invalid linked list index access");
		
		LinkedNode<T> current = head;
		for(int i = 0; i < index-1; i++)
		{
			current = current.next;
		}
		LinkedNode<T> newNode = new LinkedNode<T>(thing);
		newNode.next = current.next;
		current.next = newNode;
		size++;
		
		current = head;
		while(current.next != head)
		{
			current = current.next;
		}
		tail = current;
	}
	
	/**
	 * The instructions mentioned a lot of insert after, so I wrote this.
	 *
	 * @param nodeAfter The linkednode present in the linked list to insert the thing after.
	 * @param thing The thing to insert into the linked list packaged as a linkednode.
	 */ 
	public void insertAfter(LinkedNode<T> nodeAfter, T thing)
	{
		LinkedNode<T> current = head;
		while(current != nodeAfter)
		{
			current = current.next;
		}
		LinkedNode<T> newNode = new LinkedNode<T>(thing);
		newNode.next = current.next;
		current.next = newNode;
		size++;
		
		current = head;
		assert current != null;
		while(current.next != head)
		{
			current = current.next;
		}
		tail = current;
	}
	
	/**
	 * Clear and empty the entire linked list.
	 */ 
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * Returns the head of the linked list, which points to the second linkednode.
	 *
	 * @return The linkednode head of the linked list
	 */ 
	public LinkedNode<T> getListHead()
	{
		return head;
	}
	
	/**
	 * Returns the tail of the linked list, which points to the head.
	 *
	 * @return The linkednode tail of the linked list
	 */ 
	public LinkedNode<T> getListTail()
	{
		return tail;
	}
	
	@Override
	public String toString()
	{
		if(size == 0) return "[]";
		StringBuilder sb = new StringBuilder();
		LinkedNode<T> current = head;
		sb.append("[ ");
		do {
			sb.append(current.value.toString());
			sb.append(' ');
			current = current.next;
		}
		while(current != head);
		sb.append(']');
		return sb.toString();
	}
}
