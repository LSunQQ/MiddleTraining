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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>QuickCrab</code> make fast movement than crab as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
	/**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };

		/**
		 * get the status of the left two cells
		 * and the right two cells
		 */
        Location leftloc = getLocation().getAdjacentLocation(getDirection() + dirs[0]);
        if (getGrid().isValid(leftloc) && getGrid().get(leftloc) == null) {
        	Location lefttwoloc = leftloc.getAdjacentLocation(getDirection() + dirs[0]);
        	if (getGrid().isValid(lefttwoloc) && getGrid().get(lefttwoloc) == null) {
        		locs.add(lefttwoloc);
        	}
        }

        Location rightloc = getLocation().getAdjacentLocation(getDirection() + dirs[1]);
        if (getGrid().isValid(rightloc) && getGrid().get(rightloc) == null) {
        	Location righttwoloc = rightloc.getAdjacentLocation(getDirection() + dirs[1]);
        	if (getGrid().isValid(righttwoloc) && getGrid().get(righttwoloc) == null) {
        		locs.add(righttwoloc);
        	}
        }
		/**
		 * if the left two or the right two is available,
		 * select from them. Otherwise select the same as a crab.
		 */
        if (locs.size() == 0) {
	        for (Location loc : getLocationsInDirections(dirs))
	            if (getGrid().get(loc) == null)
	                locs.add(loc);
        }

        return locs;
    }
}
