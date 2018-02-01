public class LinkedNode<U>
	{
		public U value;
		public LinkedNode<U> next;
		
		public LinkedNode(U value)
		{
			this.value = value;
			next = null;
		}
	}