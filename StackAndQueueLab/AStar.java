import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Behold, the AStar implementation of the stack and queue maze lab. I wrote this class during the ACM
 * lan party because everyone else was doing homework instead of playing video games. That was, by far,
 * the best decision I made that night. 
 * 
 * The AStar algorithm here is based on the pseudocode code from the Wikipedia page for AStar.
 * Shout out to the ACM persons for helping me decide what h(n) distance calculation to use.
 * 
 * This class depends on StdDraw, Maze, and Position.
 * 
 * @author Dalton Caron
 * @version 1/26/2018
 * @see StdDraw
 * @see Maze
 * @see Position
 * @see https://en.wikipedia.org/wiki/A*_search_algorithm
 */
public class AStar
{

    /**
     * Standard main method to execute path finder program.
     * 
     * @param args The first index of this array will contain an integer dimension for the maze
     */
    public static void main(String[] args)
    {
        int n = 20;
        try
        {
            n = Integer.parseInt(args[0]);
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException ex)
        {
            System.err.println("Usage: java [integer]");
        }
        new AStar(n);
    }

    /**
     * I tried writing this without this class and using hashmaps for everything, not good.
     */
    private class AStarPosition
    {
        /** 
         * Position of the node
         * 
         * @see Position
         */
        public Position position;

        private int g = Integer.MAX_VALUE, h = 0;

        /**
         * Constructs an AStarPosition from Position coordinates.
         * 
         * @param x The x coordinate position
         * @param y The y coordinate position
         * @see Position
         */
        public AStarPosition(int x, int y)
        {
            position = new Position(x, y);
        }

        @Override
        public boolean equals(Object o)
        {
            if(!(o instanceof AStarPosition)) return false;
            if(((AStarPosition) o).position.getX() == position.getX() && ((AStarPosition)o).position.getY() == position.getY()) return true;
            return false;
        }

        @Override
        public int hashCode()
        {
            int hash = 1;
            hash = 37 * hash + position.getX() + position.getY();
            return hash;
        }

        /**
         * The G value is the cost to get to the next node from the current node. 1 represents
         * the cost of travel, which is always 1.
         * 
         * @param position The current Position to calculate a node travel from
         * @see Position
         */
        public void calculateG(AStarPosition position) // how far away are we from the start
        {
            g = position.getG() + 1;
        }

        /**
         * Hueristic guess value. Manhattan distance is what Kenney called it.
         */
        public void calculateH() // guess distance to the goal
        {
            h = (int)(Math.abs(position.getX() - end.getX()) + Math.abs(position.getY() - end.getY()));
        }

        /** @return The G value of the node */
        public int getG()
        {
            return g;
        }

        /** @param g G value to set the node to */
        public void setG(int g)
        {
            this.g = g;
        }

        /** @return The H value of the node */
        public int getH()
        {
            return h;
        }

        /** @param h H value to set the node to */
        public void setH(int h)
        {
            this.h = h;
        }

        /** @return The F value of the node, g + h */
        public int getF()
        {
            return g + h;
        }
    }

    private Maze maze;
    private int n, nodesExpanded;
    private Position start, end;
    private AStarPosition[][] positions;

    private PriorityQueue<AStarPosition> openNodes;
    private ArrayList<AStarPosition> closedNodes;
    private HashMap<AStarPosition, AStarPosition> path;

    /**
     * Lets get this show on the road.
     * 
     * @param n The dimensions of the maze
     * @see Maze
     */
    public AStar(int n)
    {
        maze = new Maze(n);
        maze.draw();
        this.n = n + 1;

        solveAStar();
    }

    /**
     * Implementation for use in a different class.
     * 
     * @param n The dimensions of the maze
     * @see Maze
     */
    public AStar(Maze maze, int n)
    {
        this.maze = maze;
        this.n = n + 1;
        solveAStar();
    }

    private void solveAStar()
    {
        nodesExpanded = 0;
        start = maze.getStart();
        end = maze.getFinish();

        if(start.equals(end))
        {
            System.out.println("The start node is the end node!");
            return;
        }

        positions = new AStarPosition[n][n];
        for(int i = 1; i < n; i++)
        {
            for(int j = 1; j < n; j++)
            {
                positions[i][j] = new AStarPosition(i, j);
            }
        }

        openNodes = new PriorityQueue<AStarPosition>((p1, p2) -> Integer.compare(p1.getF(), p2.getF()));
        closedNodes = new ArrayList<AStarPosition>(n*n);
        path = new HashMap<AStarPosition, AStarPosition>();

        positions[start.getX()][start.getY()].setG(0);

        openNodes.add(positions[start.getX()][start.getY()]);

        while(openNodes.size() != 0)
        {
            AStarPosition current = openNodes.peek();
            if(current.position.equals(end))
            {
                drawPath(current);
                System.out.println("Total nodes expanded: " + nodesExpanded);
                return;
            }

            openNodes.poll();
            closedNodes.add(current);
            if(!(current.position.equals(start)) && !(current.position.equals(end)))
                current.position.draw(StdDraw.PINK);
            
            nodesExpanded++;
            AStarPosition[] neighbors = validNeighbors(current);
            for(int i = 0; i < neighbors.length; i++)
            {
                AStarPosition neighbor = neighbors[i];

                if(neighbor == null) 
                    continue;

                if(closedNodes.contains(neighbor)) 
                    continue;

                if(!(openNodes.contains(neighbor)))
                {
                    openNodes.add(neighbor);
                    if(!(neighbor.position.equals(start)) && !(neighbor.position.equals(end)))
                        neighbor.position.draw(StdDraw.YELLOW);
                }

                int tenativeG = current.getG() + 1;
                if(tenativeG >= neighbor.getG())
                    continue;
                
                path.put(neighbor, current);
                neighbor.calculateH();
                neighbor.calculateG(current);
            }
        }

        System.out.println("I cannot find a solution!");
    }

    private void drawPath(AStarPosition current)
    {
        while(path.containsKey(current))
        {
            if(!(current.position.equals(start)) && !(current.position.equals(end)))
            {
                current.position.draw(StdDraw.MAGENTA);
            }
            current = path.get(current);
        }
    }

    private AStarPosition[] validNeighbors(AStarPosition p)
    {
        AStarPosition[] neighbors = new AStarPosition[4];
        if(maze.openNorth(p.position))
            neighbors[0] = positions[p.position.getX()][p.position.getY() + 1]; 
        if(maze.openWest(p.position))
            neighbors[1] = positions[p.position.getX() - 1][p.position.getY()]; 
        if(maze.openEast(p.position))
            neighbors[2] = positions[p.position.getX() + 1][p.position.getY()]; 
        if(maze.openSouth(p.position))
            neighbors[3] = positions[p.position.getX()][p.position.getY() - 1]; 
        return neighbors;
    }

}
