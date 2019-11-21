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

import java.util.ArrayList;

/**
 * A <code>ChameleonKid</code> takes on the color of only
 * front or behind neighbor actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ModifiedChameleonCritter
{
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> neighbors = getGrid().getNeighbors(getLocation());
        ArrayList<Actor> frontAndbehind = new ArrayList<Actor>();
        for (Actor a : neighbors) {
    		/**
			 * only the front direction and
			 * the behind direction will be selected
			 */
            if (getLocation().getDirectionToward(a.getLocation()) == Location.AHEAD ||
                getLocation().getDirectionToward(a.getLocation()) == Location.HALF_CIRCLE) {
                frontAndbehind.add(a);
            }
        }
        return frontAndbehind;
    }
}
