public class LinkedList<T>
{
	private int size;
	private LinkedNode<T> head, tail;
	
	public LinkedList()
	{
		size = 0;
		head = null;
	}
	
	public int getSize()
	{
		return size;
	}
	
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
	
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	public LinkedNode<T> getListHead()
	{
		return head;
	}
	
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