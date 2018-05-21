/**
 * Name: David Töyrä
 * File: RightHandRuleRobot.java
 * CS:   dv15dta
 * Date: 2018-04-22
 */

public class RightHandRuleRobot extends Robot
{

    private String robotFacing = "none";
    private final String directions[] ={"up", "right", "down", "left", "up", "right", "down", "left"};
    private static final int UP = 4;
    private static final int RIGHT = 5;
    private static final int DOWN = 6;
    private static final int LEFT = 7;


    /** Constructor for RightHandRuleRobot class
     *
     */
    public RightHandRuleRobot(Maze rightHandMaze)
    {
        super(rightHandMaze);
    }

    /**
     * The actual move method, uses an array of positions and an array of strings
     * to loop through the directions so that the robot will attempt to move in the
     * order of right-forward-left-backward.
     * @param i - an integer that corresponds to a direction
     * @return true if the robot has moved, false if it has not moved.
     */
    private boolean step(int i)
    {

        int counter = 0;
        Position up = up();
        Position right = right();
        Position down = down();
        Position left = left();
        Position dirPositions[] = {up, right, down, left, up, right, down, left};
        boolean hadWall = true;

        while(counter<4)
        {

            //Terminates if robot has a void to its right.
            if(!maze.isExisting(dirPositions[i]))
            {
                return false;
            }

            //When the robot is going straight, apply special verification.
            if(counter==1 || counter ==3)
            {
                if((maze.isMovable(dirPositions[i])) && hadWall)
                {
                    if(isWallOrNotEdge(directions[i]) || goalRightOfNextPos(directions[i]))
                    {
                        setCurrentPosition(dirPositions[i]);
                        if(counter==3)
                        {
                            robotFacing = directions[i];
                        }
                        return true;
                    }
                }
            }

            if (maze.isMovable(dirPositions[i]))
            {
                hadWall = false;
                if(wallRightOfNextPos(directions[i]) || goalRightOfNextPos(directions[i]))
                {
                    setCurrentPosition(dirPositions[i]);
                    robotFacing = directions[i];
                    return true;
                }
            }
            i--;
            counter++;
        }
        return false;
    }
    /**
     * Helper method, gets the position to the right
     * seen as the screen viewer.
     * @return Position
     */
    private Position right()
    {
        return getCurrentPosition().getPosToEast();
    }

    /**
     * Helper method, gets the position to the left
     * seen as the screen viewer.
     * @return Position
     */
    private Position left()
    {
        return getCurrentPosition().getPosToWest();
    }

    /**
     * Helper method, gets the position to the north
     * seen as the screen viewer.
     * @return Position
     */
    private Position up()
    {
        return getCurrentPosition().getPosToNorth();
    }

    /**
     * Helper method, gets the position to the right
     * seen as the screen viewer.
     * @return Position
     */
    private Position down()
    {
        return getCurrentPosition().getPosToSouth();
    }

    /**
     * Helper method, checks if the position the robot wants to go to
     * has a wall to the right side of the robot.
     * @return true if a wall has been found, else returns false.
     */
    private Boolean goalRightOfNextPos(String direction)
    {
        if(direction.equals("up") && maze.isGoal(up().getPosToEast()))
        {
            return true;
        }

        if(direction.equals("right") && maze.isGoal(right().getPosToSouth()))
        {
            return true;
        }

        if(direction.equals("down") && maze.isGoal(down().getPosToWest()))
        {
            return true;
        }

        if(direction.equals("left") && maze.isGoal(left().getPosToNorth()))
        {
            return true;
        }
        return false;
    }

    private Boolean wallRightOfNextPos(String direction)
    {
        if(direction.equals("up") && maze.isWall(up().getPosToEast()))
        {
            return true;
        }

        if(direction.equals("right") && maze.isWall(right().getPosToSouth()))
        {
            return true;
        }

        if(direction.equals("down") && maze.isWall(down().getPosToWest()))
        {
            return true;
        }

        if(direction.equals("left") && maze.isWall(left().getPosToNorth()))
        {
            return true;
        }
        return false;
    }

    /**
     * Helper method, checks if the next position has either a wall or non-edge position
     * @param direction the position to check
     * @return true if it's a wall or non-edge, false otherwise.
     */
    private boolean isWallOrNotEdge(String direction)
    {
        if(direction.equals("up"))
        {
            if(!maze.isEdge(up().getPosToEast()) || maze.isWall(up().getPosToEast()))
            {
                return true;
            }
        }

        else if(direction.equals("right"))
        {
            if(!maze.isEdge(right().getPosToSouth()) || maze.isWall(right().getPosToSouth()))
            {
                return true;
            }
        }

        else if(direction.equals("down"))
        {
            if(!maze.isEdge(down().getPosToWest()) || maze.isWall(down().getPosToWest()))
            {
                return true;
            }
        }

        else if(direction.equals("left"))
        {
            if(!maze.isEdge(left().getPosToNorth()) || maze.isWall(left().getPosToNorth()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Move method for robot.
     * Will check for moveable positions from the start and get an initial direction
     * then it will attempt to move in the following ordered directions: right, straight forward,
     * left or backwards.
     */
    public void move()
    {

        if (robotFacing.equals("none"))
        {
            if (maze.isMovable(up()) && maze.isWall(right()))
            {
                robotFacing = "up";
            }
            else if (maze.isMovable(right()) && maze.isWall(down()))
            {
                robotFacing = "right";
            }
            else if (maze.isMovable(down()) && maze.isWall(left()))
            {
                robotFacing = "down";
            }
            else if (maze.isMovable(left()) && maze.isWall(up()))
            {
                robotFacing = "left";
            }
        }

        /*Test each direction if the robot can go there, will start by
        attempting to move to it's right, forward, left and lastly backwards if nothing
        else works.
        */
        switch(robotFacing)
        {
            //If robot is facing north
            case "up":
            {
                step(RIGHT);
                break;
            }

            // if robot is facing east
            case "right":
            {
                step(DOWN);
                break;
            }

            //If robot is facing south.
            case "down":
            {
               step(LEFT);
               break;
            }

            //If robot is facing west
            case "left":
            {
                step(UP);
                break;
            }

            default:
            {
                System.out.println("RightHandRule Robot can't move!");
            }

        }

    }

}