/**
 * This lab was not as harsh as the other one, but it was still fun, spend too
 * much time on my linked list, which is circular.
 * This class handles all calculations for the TSP. 
 * 
 * * * * STA ANALYSIS * * * *
 * NearestInsertion tsp1000.txt : { Distance: 27868.710634854797, Points: 1000 }
 * SmallestInsertion tsp1000.txt : { Distance: 17265.628155352584, Points: 1000 }
 * NearestInsertion tsp100.txt : { Distance: 7389.929676351667, Points: 100 }
 * SmallestInsertion tsp100.txt : { Distance: 4887.219040311983, Points: 100 }
 * NearestInsertion usa13509.txt { Distance: 77449.97941714071, Points: 13509 }
 * SmallestInsertion usa13509.txt : { Distance: 45074.77692017051, Points: 13509 }
 * NearestInsertion circuit1290.txt : { Distance: 25029.790452731024, Points: 1290 }
 * SmallestInsertion circuit1290.txt : { Distance: 14596.097124575306, Points: 1290 }
 * NearestInsertion tsp10.txt : { Distance: 1566.1363051360363, Points: 10 }
 * SmallestInsertion tsp10.txt : { Distance: 1655.7461857661865, Points: 10 }
 * 
 * The Nearest Distance algorithm is subpar in most cases. Distance simply
 * The Nearest by inserting a Point after the Point it is closest to. It does 
 * not take into account the distance of the point next to the point it is
 * inserting to. The Smallest Insertion alogrithm takes into account
 * both points and is able to add a point between two points where the
 * change in distance is the smallest. This may not be true with the Nearest
 * Distance, where the distance from one point is evaluated, rather than
 * two.
 * 
 * Despite this fact, when tsp10.txt was ran, the Smallest Insert was able to
 * out do the Nearest Insert. It seems Nearest Insert has a hard time inserting
 * points when they are in a mostly circular pattern with no strange deviations.
 * This could be a hit for finding a better solution to this problem, but in 
 * most cases, it still seems SmallestInsertion works best in most random cases.
 * * * * END ANALYSIS * * * *
 * 
 * @author Dalton Caron
 * @version 2/1/2018
 * @see Point
 */
public class Tour
{
	
	private LinkedList<Point> points = new LinkedList<Point>();
	
	/**
	 * Do nothing. But implemented to follow the prototype. Program 
	 * would still work without this though.
	 */
	public Tour()
	{

	}

	/**
	 * Calculate a small link of Points for some reason.
	 * 
	 * @param a First node, the head
 	 * @param b Second node
	 * @param c Third node
	 * @param d Fourth node, the tail
	 */
	public Tour(Point a, Point b, Point c, Point d)
	{
		points.pushBack(a);
		points.pushBack(b);
		points.pushBack(c);
		points.pushBack(d);
		// linked list will make this structure: a->b->c->d->a
	}
	
	/**
	 * Prints out the total distance between nodes, amount of nodes,
	 * and all Points.toString() on each new line.
	 * 
	 * @see Point.toString()
	 */
	public void show()
	{
		System.out.println("Tour distance: " + distance());
		System.out.println("Number of points: " + points.getSize());
		/*LinkedNode<Point> current = points.getListHead(), tail = points.getListTail();
		do {
			System.out.println(current.value.toString());
			current = current.next;
		}
		while(current != tail.next);*/
	}
	
	/**
	 * Draws the TSP. The screen is cleared in case the animation
	 * argument is enabled.
	 */
	public void draw()
	{
		StdDraw.clear();
		LinkedNode<Point> head = points.getListHead(),  
		current = points.getListHead().next;
		do {
			current.value.draw();
			current.value.drawTo(current.next.value);
			current = current.next;
		} while(current != head.next);
	}
	
	/**
	 * Returns the amount of points present in the linked list of proccessed nodes.
	 * 
	 * @return The size or amount of all points processed so far
	 */
	public int size()
	{
		return points.getSize();
	}
	
	/**
	 * This method returns the total distance between each node relative
	 * to the linked list order. This method should not be called very often
	 * as the linked list will need to be iterated over and distance
	 * recalculated every method call. It is very fortunate that this
	 * method will only need to be called once after every calculation,
	 * but it is note worthy that some of the implementation programs,
	 * not written by me, do make use of this method, which seems
	 * counter productive.
	 * 
	 * @return The total distance between all nodes in he linked list
	 */
	public double distance()
	{
		double distance = 0;

		LinkedNode<Point> head = points.getListHead(), current = points.getListHead();
		do {
			distance += Math.abs(current.value.distanceTo(current.next.value));
			current = current.next;
		} while(current != head);

		return distance;
	}
	
	/**
	 * Inserts a point into the linked list after the node that it
	 * shares the shortest distance between. Justin told me we are
	 * suppose to error check for invalid points, so I included
	 * error checks.
	 * 
	 * @param p The Point to insert into the TSP linked list
	 */
	public void insertNearest(Point p)
	{
		if(p == null)
		{
			System.err.println("The point value read was null");
			return;
		}

		if(points.getSize() >= 1)
		{
			LinkedNode<Point> smallestNode = points.getListHead(), current = points.getListHead(), tail = points.getListTail();
			double smallestDistance = Math.abs(smallestNode.value.distanceTo(p));
			do {
				double tmp = Math.abs(current.value.distanceTo(p));
				if(smallestDistance > tmp)
				{
					smallestNode = current;
					smallestDistance = tmp;
				}
				current = current.next;
			} while(current != tail.next);
			
			points.insertAfter(smallestNode, p);
		}
		else
		{
			points.pushBack(p);
		}
	}
	
	/**
	 * This approach inserts the node into the linked list where the
	 * change in distance between the three points, including the point
	 * inserted, minus the old distance between the two points,
	 * is the smallest compared to all other three point comparisons.
	 * 
	 * @param p The Point to insert into the TSP linked list
	 */
	public void insertSmallest(Point p)
	{
		if(p == null)
		{
			System.err.println("The point value read was null");
			return;
		}

		if(points.getSize() >= 2)
		{
			double smallest = Double.POSITIVE_INFINITY;
			LinkedNode<Point> currentl, currentr, current = points.getListHead(), insertAfter = null, head = points.getListHead();
			do {
				currentl = current;
				currentr = current.next;
				
				double prev = Math.abs(currentl.value.distanceTo(currentr.value));
				double newdist = Math.abs(currentl.value.distanceTo(p)) + Math.abs(p.distanceTo(currentr.value));
				double change = newdist - prev;

				if(smallest > change)
				{
					smallest = change;
					insertAfter = currentl;
				}

				current = current.next;
			} while(current != head);

			points.insertAfter(insertAfter, p);
		}
		else
		{
			points.pushBack(p);
		}
	}
}
