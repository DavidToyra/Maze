/**
 * Name: David Töyrä
 * File: Robot.java
 * CS:   dv15dta
 * Date: 2018-04-22
 */
public abstract class Robot
{
    private Position position;
    protected Maze maze;


    public Robot(Maze robotMaze)
    {
        maze = robotMaze;
        position = maze.getStartPosition();
    }

    /**
     * Abstract method, move is done by subclasses.
     */
    public abstract void move ();


    /**
     * Returns the current position of the robot.
     * @return
     */
    public Position getCurrentPosition()
    {
        return position;
    }

    /**
     * Set the current position.
     * @param newPosition
     */
    protected void setCurrentPosition(Position newPosition)
    {
        position = newPosition;
    }


    /**
     * Checks if the robot's current position is the goal.
     * @return
     */
    public boolean hasReachedGoal()
    {
        if(maze.isGoal(position))
        {
            return true;
        }
        return false;
    }

}