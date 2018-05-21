/*
 * Name: David Töyrä
 * File: RobotCompare.java
 * CS:   dv15dta
 * Date: 2018-05-11
 */


import java.io.*;
public class RobotCompare
{

    /**
     * Main program for the maze, the two robots will race against each other
     * Takes a file as argument and will read the maze from it. Then loads
     * two robots with this maze and will loop their move() methods until the race is over.
     *
     * IMPORTANT! Integer variable "rounds" must be altered depending on the maze size.
     *
     * @param args a text file with a maze.
     * @throws IOException if file cannot be read.
     * @throws IllegalStateException if maze format is wrong.
     */
    public static void  main(String[] args) throws IOException, IllegalStateException
    {

        int rounds = 1500;
        BufferedReader r = null;
        Maze testMaze = null;

        //Verify input
        if(args.length == 0)
        {
            System.out.println("No input detected, proper usage is: RobotCompare [maze_text_file]");
            System.exit(1);
        }

        //Reading the file
        try
        {
            r = new BufferedReader(new FileReader(args[0]));
        }
        catch(FileNotFoundException e)
        {
            System.out.print("File error: ");
            e.printStackTrace();
            System.exit(1);
        }

        //Verifying the maze
        try
        {
            testMaze = new Maze(r);
        }
        catch(IOException | IllegalStateException e)
        {
            System.out.print("Maze error: ");
            e.printStackTrace();
            System.exit(1);
        }

        //Create the Robots
        RightHandRuleRobot rhrRobot = new RightHandRuleRobot(testMaze);
        MemoryRobot memRobot = new MemoryRobot(testMaze);

        int memRobotRounds = 0;
        int rhrRobotRounds = 0;

        //Run the robots simultaneously
        System.out.println("Start!");
        for (int path = 0; path < rounds; path++)
        {
            //testMaze.printMaze(memRobot.getCurrentPosition(), 'M'); if you want to print the maze
            if (!rhrRobot.hasReachedGoal())
            {
                rhrRobotRounds++;
                rhrRobot.move();
            }

            if (!memRobot.hasReachedGoal())
            {
                memRobotRounds++;
                memRobot.move();
            }
        }

        //Check rounds and determine winner
        if (rhrRobot.hasReachedGoal())
        {
            System.out.printf("Right Hand Rule Robot has reached the goal.\nIt took %d moves\n",
                    rhrRobotRounds);
        }
        else
        {
            System.out.println("Right Hand Rule Robot did not reach the goal.");

        }

        if (memRobot.hasReachedGoal())
        {
            System.out.printf("Memory Robot has reached the goal.\nIt took %d moves.\n",
                    memRobotRounds);
        } else
        {
            System.out.println("Memory Robot did not reach the goal");
        }

        if (memRobotRounds == rhrRobotRounds)
        {
            System.out.println("It's a draw!\n");
        }
        else if (memRobotRounds < rhrRobotRounds)
        {
            System.out.println("The Memory Robot wins!\n");
        }
        else
        {
            System.out.println("The Right Hand Rule Robot wins!\n");
        }
    }
}