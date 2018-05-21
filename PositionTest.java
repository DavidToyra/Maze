import static org.junit.Assert.*;
import org.junit.Test;

public class PositionTest
{
    private Position posTest;

    /**
     * Creates a Position Object
     * @throws Exception
     */
    @Test
        public void testPositionConstruct() throws Exception
        {
            posTest = new Position(1,1);
        }

    /**
     * Test if two objects with the same x and y values are equal.
     * @throws Exception
     */
    @Test
        public void testIfPositionsAreEqual()throws Exception
        {
            Position p1 = new Position(10,5);
            Position p2 = new Position(10,5);
            assertEquals(p1,p2);
        }

    /**
     * Tests if getSouthPos adds 1 y.
     */
    @Test
        public void testGetSouthPos() {

            Position p1 = new Position(1,2);
            Position p2 = p1.getPosToSouth();
            Position p3 = new Position(1, 3);

            assertEquals(p2, p3);

        }

        /**
        * Tests if getNorthPos subtracts 1 y.
        */
        @Test
        public void testGetNorthPos()
        {
            Position p1 = new Position(1,2);
            Position p2 = p1.getPosToNorth();
            Position p3 = new Position(1, 1);

            assertEquals(p2, p3);
        }

        /**
        * Tests if getWestPos subtracts 1 x.
        */
        @Test
        public void testGetWestPos()
        {
            Position p1 = new Position(1,2);
            Position p2 = p1.getPosToWest();
            Position p3 = new Position(0, 2);

            assertEquals(p2, p3);
        }

        /**
        * Tests if getEasttPos adds 1 x.
        */
        @Test
        public void testGetEastPos()
        {
            Position p1 = new Position(1,2);
            Position p2 = p1.getPosToEast();
            Position p3 = new Position(2, 2);

            assertEquals(p2, p3);
        }


	/**
        * Tests if gives different hashcodes.
        */
        @Test
        public void shouldHaveSameHashCode()
        {
            Position p1 = new Position(1,2);
            Position p2 = new Position(1,2);
	    int hCode1 = p1.hashCode();
	    int hCode2 = p2.hashCode();

            assertEquals(hCode1, hCode2);
        }

}
