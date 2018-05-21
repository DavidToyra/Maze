import static org.junit.Assert.*;
import org.junit.Test;
import java.io.StringReader;

public class MazeTest
{


    /**
     * Tests if a valid maze can be read.
     * @throws Exception
     */
    @Test
    public void createValidMaze() throws Exception
    {
        String mazeString =
                        "*S**\n"+
                        "*  *\n"+
                        "*G**\n";
        StringReader s = new StringReader(mazeString);
        new Maze(s);
    }


    /**
     * Tests if a maze without start will fail.
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void shouldFailNoStart() throws Exception
    {
        String mazeString =
                        "****\n"+
                        "*  *\n"+
                        "*G**\n";
        StringReader s = new StringReader(mazeString);
        new Maze(s);
    }

    /**
     * Tests if a maze with more than one start will fail.
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void shouldFailTwoStart() throws Exception
    {
        String mazeString =
                        "SS**\n"+
                        "*  *\n"+
                        "*G**\n";
        StringReader s = new StringReader(mazeString);
        new Maze(s);
    }

    /**
     * Tests if a maze without goal will fail.
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void shouldFailNoGoal() throws Exception
    {
        String mazeString =
                        "*S**\n"+
                        "*  *\n"+
                        "****\n";
        StringReader s = new StringReader(mazeString);
        new Maze(s);
    }

    /**
     * Tests if a position outside the maze is not moveable.
     * @throws Exception
     */
    @Test
    public void shouldNotBeMoveable() throws Exception
    {
        String mazeString =
                        "*G**\n"+
                        "*  *\n"+
                        "*S**\n";
        StringReader s = new StringReader(mazeString);
        Maze mazeTest = new Maze(s);
        Position pos = (mazeTest.getStartPosition()).getPosToSouth();
        assertFalse(mazeTest.isMovable(pos));
    }

    /**
     * Tests if a position inside the maze is moveable.
     * @throws Exception
     */
    @Test
    public void shouldBeMoveable() throws Exception
    {
        String mazeString =
                        "*G**\n"+
                        "*  *\n"+
                        "*S**\n";
        StringReader s = new StringReader(mazeString);
        Maze mazeTest = new Maze(s);
        Position pos = new Position(2,1);
        assertTrue(mazeTest.isMovable(pos));
    }


    /**
     * Tests if getStartPosition finds the position.
     */
    @Test
    public void testGetStart() throws Exception
    {
        String mazeString = "*S*G*\n";
        StringReader s = new StringReader(mazeString);
        Maze mazeTest = new Maze(s);
        Position startPos = mazeTest.getStartPosition();
        Position actualStartPos = new Position(1,0);
        assertEquals(actualStartPos, startPos);
    }

    /**
     * Tests if isGoal returns true on actual goal.
     */
    @Test
    public void testIsGoal() throws Exception
    {
        String mazeString = "*S*G\n";
        StringReader s = new StringReader(mazeString);
        Maze mazeTest = new Maze(s);
        Position goalPos = new Position(3,0);
        assertTrue(mazeTest.isGoal(goalPos));
    }
}