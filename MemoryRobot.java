/**
 * Name: David Töyrä
 * File: MemoryRobot.java
 * CS:   dv15dta
 * Date: 2018-04-22
 */
import java.util.ArrayList;
import java.util.Stack;


public class MemoryRobot extends Robot
{
    private Stack<Position> backTrack;
    private ArrayList<Position> visited;

    /**
     * Constructor for MemoryRobot, sets attributes.
     * @param memoryMaze the maze that the robot runs on.
     */
    public MemoryRobot(Maze memoryMaze)
    {
        super(memoryMaze);
        backTrack = new Stack<>();
        visited = new ArrayList<>();
        backTrack.push(getCurrentPosition());
        visited.add(getCurrentPosition());
    }

    /**
     * Move method for MemoryRobot.
     * Will attempt to solve the maze in a depth-first search, will move
     * in the direction order of N-E-S-W. Will put its position in a stack, and
     * if it found no place to move it will backtrack until it can move again.
     */
    public void move()
    {

        if(maze.isGoal(getCurrentPosition()))
        {
            System.out.println("Memory robot completes maze!");
            return;
        }

        else if(this.maze.isMovable(getCurrentPosition().getPosToNorth()) &&
                !visited.contains(getCurrentPosition().getPosToNorth()))
        {

            setCurrentPosition(getCurrentPosition().getPosToNorth());
            backTrack.push(getCurrentPosition());
            visited.add(getCurrentPosition());

        }

        else if(this.maze.isMovable(getCurrentPosition().getPosToEast())  &&
                !visited.contains(getCurrentPosition().getPosToEast()))
        {

            setCurrentPosition(getCurrentPosition().getPosToEast());
            backTrack.push(getCurrentPosition());
            visited.add(getCurrentPosition());

        }

        else if(this.maze.isMovable(getCurrentPosition().getPosToSouth())  &&
                !visited.contains(getCurrentPosition().getPosToSouth()))
        {


            setCurrentPosition(getCurrentPosition().getPosToSouth());
            backTrack.push(getCurrentPosition());
            visited.add(getCurrentPosition());

        }

        else if(this.maze.isMovable(getCurrentPosition().getPosToWest())  &&
                !visited.contains(getCurrentPosition().getPosToWest()))
        {

            setCurrentPosition(getCurrentPosition().getPosToWest());
            backTrack.push(getCurrentPosition());
            visited.add(getCurrentPosition());

        }

        //If robot cannot walk forward anymore he will go back one step and try again.
        else if(!backTrack.isEmpty())
        {
            setCurrentPosition(backTrack.peek());
            backTrack.pop();

        }

    }
}