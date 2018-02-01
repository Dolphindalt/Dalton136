

public class Test
{

	public static void main(String[] args)
	{
		new Test();
	}
	
	public Test()
	{
		testLinkedList();
		System.out.println("Testing was a success");
	}
	
	public void testLinkedList()
	{
		LinkedList<String> linkedStrings = new LinkedList<String>();
		linkedStrings.pushBack("Hello there");
		linkedStrings.pushBack("Hello ther");
		linkedStrings.pushBack("Hello the");
		assert linkedStrings.getSize() == 3;
		assert linkedStrings.get(0).value.equals("Hello there");
		assert linkedStrings.get(1).value.equals("Hello ther");
		assert linkedStrings.get(2).value.equals("Hello the");
		
		linkedStrings.insertAfter(linkedStrings.get(0), "Liberstraumen");
		assert linkedStrings.get(1).value.equals("Liberstraumen");
		
		linkedStrings.clear();
		assert linkedStrings.getSize() == 0;
		
		LinkedList<Integer> linkedInts = new LinkedList<Integer>();
		linkedInts.pushBack(0);
		linkedInts.pushBack(1);
		linkedInts.pushBack(2);
		linkedInts.pushBack(3);

		linkedInts.insertAt(1, 20);
		assert linkedInts.get(1).value == 20;

		linkedInts.remove(1);
		assert linkedInts.get(1).value.equals(1);

		System.out.println(linkedInts.toString());
		linkedInts.remove(3);
		System.out.println(linkedInts.toString());
		assert linkedInts.getListTail().value == 2;
		
		linkedInts.insertAt(3, 200);
		assert linkedInts.get(3).value == 200;
		assert linkedInts.getListTail().value == 200;
		assert linkedInts.getListTail().next == linkedInts.getListHead();
		
		LinkedList<Point> linkedPoints = new LinkedList<Point>();
		linkedPoints.pushBack(new Point(0, 0));
		linkedPoints.insertAfter(linkedPoints.get(0), new Point(1, 0));

	}
	
}
