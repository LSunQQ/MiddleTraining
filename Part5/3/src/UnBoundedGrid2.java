/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * An <code>UnboundedGrid</code> is an open-ended rectangular grid with an unbounded number of rows and
 * columns both more than 0. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnBoundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the grid elements
    private static int defaultSize = 16;
    private int size;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnBoundedGrid2()
    {
        size = defaultSize;
        occupantArray = new Object[defaultSize][defaultSize];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return ((loc.getRow() >= 0) && (loc.getCol() >= 0));
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < size; r++)
        {
            for (int c = 0; c < size; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }
        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= size || loc.getCol() >= size)
            return null;
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        int _size = size;
        /**
		 * Since it is open-ended, if it's location is over the 
		 * size, the size should be bigger.
		 */
        while (loc.getRow() >= size || loc.getCol() >= size) {
            size = size * 2;
        }

        if (_size != size) {
            Object[][] newArray = new Object[size][size];
            for (int i = 0; i < _size; ++i) {
                for (int j = 0; j < _size; ++j) {
                    newArray[i][j] = occupantArray[i][j];
                }
            }
            occupantArray = newArray;
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
