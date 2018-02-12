import java.util.Scanner;
import java.io.*;

/**
 * The main class for this StayingAlive game. I could
 * have seperated some of my parsing in the constructor
 * into multiple methods, but this is a freshman CS
 * course and this lab is not too fun. I do not know why
 * but I just really do not like drawing with StdDraw. I
 * mean I am okay with using it, but using it just bothers
 * me a little bit. I feel like the library is doing all the
 * fun stuff for me, kind of like the Java language in general.
 * Enough of my ranting, this project is below, but it could
 * be better. It meets all of the rubric grades though. :P
 * This lab was OKAY; personally liked the others more.
 * The software engineering one was frustrating but fun. 
 * Also I always write an essay in the main header, it is
 * important for the grader to understand what I was thinking
 * when I wrote this stuff.
 * 
 * @author Dalton Caron
 * @version 2/11/2018
 */
public class StayingAlive
{

    /**
     * Reads command line arguments and creates an instance of this class.
     * 
     * @param args The string array of command arguments. We use 0 as a filename read in.
     */
    public static void main(String[] args)
    {
        if(args.length < 1)
        {
          System.err.println("Usage: java StayingAlive [file]");
          return;
        }

        new StayingAlive(args[0]);
    }

    private final String background;
    private Player player;
    private Enemy[] enimies;
    private int score = 0;

    /**
     * Giant ass constructor that reads and parses data from
     * the input file while calling the main game loop.
     * 
     * @param fileName The string name of the file we are going to read
     */
    public StayingAlive(String fileName)
    {
      Scanner sc = null;
      try
      {
        sc = new Scanner(new File(fileName));
      }
      catch(IOException e)
      {
        System.err.println("Failed to read input file");
        System.exit(1);
      }

      if(!sc.hasNext())
      {
        System.err.println("A background is not specified!");
        System.exit(1);
      }

      background = sc.nextLine();

      if(!sc.hasNext())
      {
        System.err.println("A player must be specified!");
        System.exit(1);
      }

      String[] playerData = sc.nextLine().split(" ");
      if(playerData.length < 4)
      {
        System.err.println("The player line is missing information!");
        System.exit(1);
      }
      
      player = null;
      try
      {
        player = new Player(playerData[0], Double.parseDouble(playerData[1]), Double.parseDouble(playerData[2]), Double.parseDouble(playerData[3]), Integer.parseInt(playerData[4]));
      }
      catch(NumberFormatException e)
      {
        System.err.println("Failed to parse some player data!");
        System.exit(1);
      }

      if(!sc.hasNext())
      {
        System.err.println("The number of enimies was not specified!");
        System.exit(1);
      }
      
      int numEms = -1;
      try
      {
        numEms = Integer.parseInt(sc.nextLine());
      }
      catch(Exception e)
      {
        System.err.println("Failed to parse number of enimies!");
        System.exit(1);
      }

      enimies = new Enemy[numEms];
      
      for(int i = 0; i < numEms; i++)
      {
        if(!sc.hasNext())
        {
          System.out.println("Failed to find an enemy, too many specfied?");
          System.exit(1);
        }
        
        String[] emData = sc.nextLine().split(" ");
        if(emData[0].isEmpty()) 
        {
          i--;
          continue; // skip empty lines
        }
        if(emData.length < 5)
        {
          System.err.println("Enemy " + i + " is missing data!");
          System.exit(1);
        }
        Enemy newEm = null;
        try
        {
          newEm = new Enemy(emData[0], Double.parseDouble(emData[1]), Double.parseDouble(emData[2]), Double.parseDouble(emData[3]), Double.parseDouble(emData[4]), Double.parseDouble(emData[5]));
        }
        catch(NumberFormatException e)
        {
          System.err.println("Failed to parse enemy data for enemy " + i);
          System.exit(1);
        }
        enimies[i] = newEm;
      }
      
      System.out.println("PLAYER: " + player.toString());
      for(int i = 0; i < enimies.length; i++)
      {
        System.out.println("ENEMY " + i + ": " + enimies[i].toString());
      }

      gameOn();
    }

    private void gameOn()
    {
      StdDraw.setPenColor(StdDraw.RED);
      for(;;)
      {
        StdDraw.clear();

        StdDraw.picture(0.5, 0.5, background);

        player.updatePos(StdDraw.mouseX(), StdDraw.mouseY());
        player.draw();

        for(int i = 0; i < enimies.length; i++)
        {
          Enemy en = enimies[i];
          en.updatePos(en.getX(), en.getY());
          en.draw();
          if(player.intersects(en))
          {
            youLose();
          }
        }
        StdDraw.textLeft(0.1, 0.1, "Score: " + score);
        StdDraw.show(10);
        score++;
      }
    }

    private void youLose()
    {
      StdDraw.textLeft(0.1, 0.1, "Score: " + score);
      StdDraw.textRight(0.3, 0.3, "GAME OVER");
      StdDraw.show(5000);
      System.exit(0);
    }

}
