/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
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
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * A <code>ZBug</code> it shows a "Z" path then stops. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int judge;

    /**
     * Constructs a z bug that traces a square of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        sideLength = length;
        judge = 0;
        // Set the default direction
        setDirection(Location.EAST);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
    	/**
    	 * if the bug could not move,
    	 * it will stay still.
    	 */
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            if (judge < 2)
            {
                if (steps == sideLength)
                {
                	/**
                	 * Since the default direction is east,
                	 * the first turn should turn to southwest
                	 * and the second turn should turn to east.
                	 */
                    if (getDirection() == Location.EAST)
                    {
                        setDirection(Location.SOUTHWEST);
                    }
                    else if (getDirection() == Location.SOUTHWEST)
                    {
                        setDirection(Location.EAST);
                    }
                    ++judge;
                    steps = 0;
                }
            }
        }
    }
}
