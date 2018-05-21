
/**
 * Name: David Töyrä
 * File: Maze.java
 * CS:   dv15dta
 * Date: 2018-05-11
 */

import java.io.*;
import java.util.*;

public class Maze
{

    private int rows;
    private int cols;
    private LinkedHashMap<Position, Character> mazeData = new LinkedHashMap<>();

    /**
     * Maze constructor, reads file and puts characters in an array.
     * with positions as key.
     * @throws IOException if file cannot be read.
     */
    public Maze(java.io.Reader fileInput) throws IOException
    {
        BufferedReader reader = new BufferedReader(fileInput);
        String readLine;
        StringBuilder mazeString = new StringBuilder();

        //Read through file first to get rows and columns.
        while ((readLine = reader.readLine()) != null)
        {
            // process the line
            rows++;
            cols = 0;
            cols = readLine.length(); // always the size of the last line in the file
            mazeString.append(readLine);
        }

        for(int i = 0; i<mazeString.length(); i++)
        {
            for(int r = 0; r < rows; r++)
            {
                //loop through the columns of each row
                for(int c = 0; c < cols; c++,i++)
                {
                    mazeData.put(new Position(c,r),mazeString.charAt(i));
                }
            }
        }

        //verify the maze
        hasAtleastOneGoal();
        hasOneStart();

    }

    /**
     * Not part of the official program.
     * Will print the maze.
     */
    public void printMaze(Position pos, char robotChar)
    {
        ArrayList<Character> mazeList = new ArrayList<>();
        LinkedHashMap<Position, Character> tempMap = new LinkedHashMap<>();
        tempMap.putAll(mazeData);
        if(tempMap.containsKey(pos))
        {
            tempMap.put(pos, robotChar);
        }
        mazeList.addAll(tempMap.values());
        Iterator<Character> mazeIterator = mazeList.iterator();
        for(int r = 0; r < rows; r++)
        {
            //loop through the columns of each row
            for(int c = 0; c < cols; c++)
            {
                System.out.print(mazeIterator.next());
            }
            System.out.println();
        }
        System.out.println("||||||||||||||||||||||||||||||||||");
    }

    /**
     * Checks if the position parameter is a character the robot can move on.
     * @param mazePosition the position object that is tested.
     * @return boolean
     */
    public boolean isMovable(Position mazePosition)
    {

        if(!mazeData.containsKey((mazePosition)))
        {
            return false;
        }

        else if(mazeData.get(mazePosition)=='*')
        {

            return false;
        }
        return true;
    }

    public boolean isExisting(Position pos)
    {
        if(mazeData.containsKey(pos))
        {
            return true;
        }
        return false;
    }

    /**
     * Checks if the position is at an edge, if it does not have 4 neighbours it is.
     * @param pos the position to check.
     * @return true if position is an edge.
     */
    public boolean isEdge(Position pos)
    {
        if(!mazeData.containsKey(pos.getPosToEast()))
        {
            return true;
        }
        if(!mazeData.containsKey(pos.getPosToNorth()))
        {
            return true;
        }
        if(!mazeData.containsKey(pos.getPosToSouth()))
        {
            return true;
        }
        if(!mazeData.containsKey(pos.getPosToWest()))
        {
            return true;
        }
        return false;
    }


    /**
     * Checks if the maze has exactly one start.
     * @throws IllegalStateException if one start is not found.
     * @returns true if one start is found.
     */
    private boolean hasOneStart() throws IllegalStateException
    {
        Character tableValue = 'S';
        int startCounter=0;

        for(Map.Entry<Position, Character> entry: mazeData.entrySet())
        {
            if(tableValue.equals(entry.getValue()))
            {
                startCounter++;
            }
        }
        if(startCounter!=1)
        {
            throw new IllegalStateException("Does not have exactly one start!");
        }
        return true;
    }

    /**
     * Checks if the maze has at least on goal.
     * @throws IllegalStateException if at least on goal is not found.
     * @returns true if one or more goal is found.
     */
    private boolean hasAtleastOneGoal() throws IllegalStateException
    {
        {
            Character tableValue = 'G';
            int goalCounter=0;

            for(Map.Entry<Position, Character> entry: mazeData.entrySet())
            {
                if(tableValue.equals(entry.getValue()))
                {
                    goalCounter++;
                }
            }
            if(goalCounter<1)
            {
                throw new IllegalStateException("Maze does not have at least one goal!");
            }
            return true;
        }
    }


    /**
     * Checks if the position parameter is the goal.
     * @param pos
     * @return boolean
     */
    public boolean isGoal(Position pos)
    {
        if(mazeData.containsKey(pos))
        {
            if(mazeData.get(pos).equals('G'))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Iterates through hash map to find value that represents start in
     * the maze, returns position associated with that value.
     * @return position
     */
    public Position getStartPosition()
    {
        Character tableValue = 'S';

        for(Map.Entry<Position, Character> entry: mazeData.entrySet())
        {
            if(tableValue.equals(entry.getValue()))
            {
                return entry.getKey();
            }
        }

        System.out.println("No starting position");
        return null;
    }

    /**
     * Checks if the position parameter is the goal.
     * @param position check if this position is a wall.
     * @return true if pos is wall, false otherwise.
     */
    public boolean isWall(Position position)
    {
        if(mazeData.containsKey(position))
        {
            if (mazeData.get(position) == '*')
            {
                return true;
            }
        }
        return false;
    }
}