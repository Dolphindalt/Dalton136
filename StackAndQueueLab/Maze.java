/**
 *  Compilation:  javac Maze.java
 *  Execution:    java Maze.java n
 *  Dependencies: StdDraw.java
 *
 *  Generates a perfect n-by-n maze 
 *  
 *  Originally developed at Princeton
 *  
 *  @author Michele Van Dyne
 *  
 *  Modified to use Positions and cleaned up code a bit
 *
 */
public class Maze {
    private int n;                 // dimension of maze
    private boolean[][] north;     // is there a wall to north of cell i, j
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;	// has a cell been visited
    private Position start;			// where the maze starts
    private Position finish;		// the goal position

    /**
     * Constructor for the maze.
     * It sets up the scale for the drawing window, initializes start and finish
     * positions, calls the init() function to generate a maze with all walls
     * added, calls generate to remove walls so that a path is created between 
     * start and finish, and clears the maze from any tags left from generation.
     * 
     * @param n the length of one of the walls of the maze
     */
    public Maze(int n) {
        this.n = n;
        StdDraw.setXscale(0, n+2);
        StdDraw.setYscale(0, n+2);
        //start = new Position(1,1);
        //finish = new Position(n/2, n/2);
        start = new Position((int) (Math.random()*(n)+1), (int) (Math.random()*(n)+1));
        finish = new Position((int) (Math.random()*(n)+1), (int) (Math.random()*(n)+1));
        init();
        generate();
        clear();
        }

    /**
     * Initializes the nxn grid containing the maze so that all squares are
     * initially walled off.
     */
    private void init() {
        // initialize border cells as already visited
        visited = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            visited[x][0] = true;
            visited[x][n+1] = true;
        }
        for (int y = 0; y < n+2; y++) {
            visited[0][y] = true;
            visited[n+1][y] = true;
        }

        // initialize all walls as present
        north = new boolean[n+2][n+2];
        east  = new boolean[n+2][n+2];
        south = new boolean[n+2][n+2];
        west  = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            for (int y = 0; y < n+2; y++) {
                north[x][y] = true;
                east[x][y]  = true;
                south[x][y] = true;
                west[x][y]  = true;
            }
        }
    }

    /**
     * Starts with a totally walled off maze and randomly chooses walls to remove
     * such that there will be a path from start to finish. The function calls
     * itself recursively.
     * 
     * @param x the x position we are currently investigating
     * @param y the y position we are currently investigating
     */
    private void generate(int x, int y) {
        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            while (true) {
                // pick random neighbor 
                int r = (int) (Math.random() * 4);
                
                // if that node has not been investigated before, remove some walls
                //   and call itself using the appropriate neighbor
                if (r == 0 && !visited[x][y+1]) {
                    north[x][y] = false;
                    south[x][y+1] = false;
                    generate(x, y + 1);
                    break;
                }
                else if (r == 1 && !visited[x+1][y]) {
                    east[x][y] = false;
                    west[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                else if (r == 2 && !visited[x][y-1]) {
                    south[x][y] = false;
                    north[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                else if (r == 3 && !visited[x-1][y]) {
                    west[x][y] = false;
                    east[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }

    /**
     * Generate the maze starting from lower left
     */
    private void generate() {
        generate(1, 1);     
    }

    /**
     * Draw the maze.
     */
    public void draw() {
    	start.draw(StdDraw.RED);
    	finish.draw(StdDraw.RED);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                if (south[x][y]) StdDraw.line(x, y, x+1, y);
                if (north[x][y]) StdDraw.line(x, y+1, x+1, y+1);
                if (west[x][y])  StdDraw.line(x, y, x, y+1);
                if (east[x][y])  StdDraw.line(x+1, y, x+1, y+1);
            }
        }
        StdDraw.show(1000);
    }
    
    /**
     * Accessor for the start Position
     * 
     * @return the start Position
     */
    public Position getStart()
    {
    	return start;
    }
    
    /**
     * Accessor for the finish Position
     * 
     * @return the finish Position
     */
    public Position getFinish()
    {
    	return finish;
    }
    
    /**
     * Check to see if there is no wall to the north
     * 
     * @param p the Position we are currently looking at
     * @return true if there is no wall to the north, false otherwise
     */
    public boolean openNorth(Position p)
    {
    	return !north[p.getX()][p.getY()];
    }

    /**
     * Check to see if there is no wall to the south
     * 
     * @param p the Position we are currently looking at
     * @return true if there is no wall to the south, false otherwise
     */
    public boolean openSouth(Position p)
    {
    	return !south[p.getX()][p.getY()];
    }

    /**
     * Check to see if there is no wall to the east
     * 
     * @param p the Position we are currently looking at
     * @return true if there is no wall to the east, false otherwise
     */
    public boolean openEast(Position p)
    {
    	return !east[p.getX()][p.getY()];
    }

    /**
     * Check to see if there is no wall to the west
     * 
     * @param p the Position we are currently looking at
     * @return true if there is no wall to the west, false otherwise
     */
    public boolean openWest(Position p)
    {
    	return !west[p.getX()][p.getY()];
    }
    
    /**
     * Check to see if a Position has been visited before so we don't visit 
     * it againg
     * 
     * @param p the Position we are currently looking at
     * @return true if we've been here before, false otherwise
     */
    public boolean visited(Position p)
    {
    	return visited[p.getX()][p.getY()];
    }

    /**
     * Sets a flag in a maze position once we have been there
     * 
     * @param p the Position we are currently at
     */
    public void setVisited(Position p)
    {
    	visited[p.getX()][p.getY()] = true;
    }
    
    /**
     * Clear the maze from any visited markings so that we can try a
     * different solution
     */
    public void clear()
    {
        for (int x = 1; x <= n; x++)
            for (int y = 1; y <= n; y++)
                visited[x][y] = false;
    	start.draw(StdDraw.RED);
    	finish.draw(StdDraw.RED);
    }

    /**
     * A test main method so that we can make sure the maze is generating
     * properly.
     * 
     * @param args the length of the sides of the maze
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Maze maze = new Maze(n);
        maze.draw();
    }

}