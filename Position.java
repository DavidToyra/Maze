/**
 * Name: David Töyrä
 * File: Position.java
 * CS:   dv15dta
 * Date: 2018-04-22
 */
public class Position
{
    private  int x;
    private  int y;

    /**
     * Constructor for Position, sets x and y.
     * @param x
     * @param y
     */

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns coordinate x.
     * @return position
     */
    public int getX ()
    {
        return this.x;
    }

    /**
     * Returns coordinate y.
     * @return position
     */
    public int getY ()
    {
        return this.y;
    }

    /**
     * Get south coordinate
     * @return position
     */
    public Position getPosToSouth()
    {
        return new Position(x, y+1);

    }

    /**
     * Get north coordinate
     * @return position
     */
    public Position getPosToNorth()
    {
        return new Position(x, y-1);
    }

    /**
     * Get west coordinate
     * @return position
     */
    public Position getPosToWest()
    {
        return new Position(x-1, y);
    }

    /**
     * Get east coordinate
     * @return position
     */
    public Position getPosToEast()
    {
        return new Position(x+1, y);
    }

    /**
     * Compares if two Position objects are the same.
     * @param o the position object
     * @return true if it is the same positon, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        Position other = (Position) o;
        if(this.getX() == other.getX() && this.getY() == other.getY())
        {
            return true;
        }
        return false;
    }

    /**
     * Gives a numberical value based on x and y.
     * @return hashcode.
     */
    @Override
    public int hashCode()
    {
        int hash = 17;

        hash = 31 * hash + getX();
        hash = 31 * hash + getY();
        return hash;
    }
}
