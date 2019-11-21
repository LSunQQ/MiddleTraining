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

/**
 * A <code>DancingBug</code> it dances according to the given array. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int steps;
    private int array[];

    /**
     * Constructs a dancing bug that traces a square of a given side length
     * @param length the side length
     */
    public DancingBug(int[] arr)
    {
        steps = 0;
        if (null != arr)
        {
            array = arr;
        }
        else
        {
            arry = new int[0];
        }
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        /**
         * Turn before move.
         */
        for (int i = 0; i < array[steps]; ++i)
        {
            turn();
        }
        if (canMove())
        {
            move();
            steps++;
            /**
             * After carrying out the last turn in the array, 
             * it should start again with the initial array value 
             * so that the dancing bug continually repeats the same turning pattern.
             */
            if (steps == array.length)
            {
                steps = 0;
            }
        }
        else
        {
            turn();
        }
    }
}
