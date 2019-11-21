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
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * A <code>SparseBoundedGrid</code> a rectangular grid store in
 * LinkedList. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    
	// the ArrayList storing the grid elements
	private ArrayList<LinkedList<OccupantInCol>> occupantArray;
	private int cols, rows;

	/**
	 * Constructs an empty SparseBounded grid with the given dimensions.
	 * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
	 * @param rows number of rows in SparseBounded
	 * @param cols number of columns in SparseBounded
	 */
	public SparseBoundedGrid(int newRow, int newCol)
	{
		if (newRow <= 0)
        {
			throw new IllegalArgumentException("rows <= 0");
        }
		if (newCol <= 0)
        {
			throw new IllegalArgumentException("cols <= 0");
        }
		rows = newRow;
		cols = newCol;
		occupantArray = new ArrayList<LinkedList<OccupantInCol>>(rows);
		for (int i = 0; i < rows; ++i) {
			occupantArray.add(new LinkedList<OccupantInCol>());
		}
	}

	public int getNumRows()
	{
		return rows;
	}

	public int getNumCols()
	{
		return cols;
	}

	public boolean isValid(Location loc)
	{
		return 0 <= loc.getRow() && loc.getRow() < getNumRows()
			&& 0 <= loc.getCol() && loc.getCol() < getNumCols();
	}

	public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < occupantArray.get(r).size(); c++)
            {
                // All object in the LinkedList is occupied, put all of them in the array.
                Location loc = new Location(r, occupantArray.get(r).get(c).getCol());
                theLocations.add(loc);
            }
        }
        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        LinkedList<OccupantInCol> occupantList = occupantArray.get(loc.getRow());
        /**
		 * Check all column in the row list.
		 */
        for (int i = 0; i < occupantList.size(); ++i) {
        	if (occupantList.get(i).getCol() == loc.getCol()) {
        		return (E) occupantList.get(i).getObj();
        	}
        }
        return null; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null)
        {
            throw new NullPointerException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        OccupantInCol newObj = new OccupantInCol(obj, loc.getCol());
        occupantArray.get(loc.getRow()).add(newObj);
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        // Remove the object from the grid.
        E r = get(loc);
        LinkedList<OccupantInCol> occupantList = occupantArray.get(loc.getRow());
        for (int i = 0; i < occupantList.size(); ++i) {
        	if (occupantList.get(i).getCol() == loc.getCol()) {
        		occupantList.remove(i);
        	}
        }
        return r;
    }
}
