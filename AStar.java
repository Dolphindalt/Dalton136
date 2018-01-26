import java.util.AbstractSet;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class AStar
{

    public static void main(String[] args)
    {
        try
        {
            new AStar(Integer.parseInt(args[0]));
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException ex)
        {
            System.err.println("Usage: java [integer]");
        }
    }

    private Maze maze;

    AbstractSet<Position> closedNodes = new HashSet<Position>();
    AbstractSet<Position> openNodes = new HashSet<Position>();

    AbstractMap<Position, Position> cameFrom = new HashMap<Position, Position>();
    AbstractMap<Position, Integer> gScore = new HashMap<Position, Integer>();
    
    AbstractMap<Position, Integer> fScore = new HashMap<Position, Integer>();

    public AStar(int n)
    {
        maze = new Maze(n);
        maze.draw();
        solveAStar();
    }

    private AbstractSet<Position> solveAStar()
    {
        Position start = maze.getStart();
        Position end = maze.getFinish();

        openNodes.add(start);
        
        gScore.put(start, 0);

        fScore.put(start, distanceEstimate(start, end));

        while(openNodes.size() != 0)
        {
            Position current = start;
            current.draw(StdDraw.BLUE);
            Iterator<Position> itr = openNodes.iterator();
            while(itr.hasNext())
            {
                Position comp = itr.next();
                if(fScore.get(comp) < fScore.get(current))
                    current = comp;
            }
            
            if(current.equals(end))
            {
                return reconstructPath(cameFrom, current);
            }

            openNodes.remove(current);
            closedNodes.add(current);
            current.draw(StdDraw.GRAY);

            AbstractSet<Position> neighbors = getValidNeighbors(current);
            for(Position n : neighbors)
            {
                if(closedNodes.contains(n))
                    continue;
                if(!openNodes.contains(n))
                {
                    openNodes.add(n);
                    maze.setVisited(n);
                    n.draw(StdDraw.GREEN);
                }
                
                int tenativeGScore = gScore.get(current) + distanceEstimate(current, n);
                if(tenativeGScore >= gScore.get(n))
                    continue;
                cameFrom.put(n, current);
                gScore.put(n, tenativeGScore);
                fScore.put(n, gScore.get(n) + distanceEstimate(n, end));
            }
        }
        return new HashSet<Position>();
    }

    private AbstractSet<Position> reconstructPath(AbstractMap<Position, Position> from, Position current)
    {
        AbstractSet<Position> totalPath = new HashSet<Position>();
        totalPath.add(current);
        while(from.containsKey(current))
        {
            current = from.get(current);
            totalPath.add(current);
        }
        return totalPath;
    }

    private AbstractSet<Position> getValidNeighbors(Position c)
    {
        AbstractSet<Position> neighbors = new HashSet<Position>();
        if(maze.openNorth(c) && (!maze.visited(new Position(c.getX(), c.getY() + 1))))
        {
            neighbors.add(new Position(c.getX(), c.getY() + 1));
        }
        if(maze.openWest(c) && (!maze.visited(new Position(c.getX(), c.getY() + 1))))
        {
            neighbors.add(new Position(c.getX() - 1, c.getY()));
        }
        if(maze.openEast(c) && (!maze.visited(new Position(c.getX() + 1, c.getY()))))
        {
            neighbors.add(new Position(c.getX() + 1, c.getY()));
        }
        if(maze.openSouth(c) && (!maze.visited(new Position(c.getX(), c.getY() - 1))))
        {
            neighbors.add(new Position(c.getX(), c.getY() - 1));
        }
        return neighbors;
    }

    private int distanceEstimate(Position pos1, Position pos2)
    {
        return (int)(Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) + Math.pow(pos1.getY() - pos2.getY(), 2)));
    }

}