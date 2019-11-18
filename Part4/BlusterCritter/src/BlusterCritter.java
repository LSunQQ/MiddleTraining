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
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>BlusterCritter</code> darken itself if there
 * is no neighbor within two steps square as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
	private int c;
	private static final double DARKENING_FACTOR = 0.05;

	public BlusterCritter(int _c) {
		/**
		 * super must be the first statement in constructor
		 */
		super();
		c = _c;
	}

	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Grid<Actor> grid = getGrid();

		Location location = getLocation();
		int row = location.getRow();
		int col = location.getCol();
		/**
		 * find the edge within two steps of the location
		 */
		int up = (row - 2 > 0) ? (row - 2) : 0;
		int down = (row + 2 > getGrid().getNumRows()) ? grid.getNumRows() : (row + 2);
		int left = (col - 2 > 0) ? (col - 2) : 0;
		int right = (col + 2 > getGrid().getNumCols()) ? grid.getNumCols() : (col + 2);

		for (int i = up; i < down; ++i) {
			for (int j = left; j < right; ++j) {
				if (grid.get(new Location(i, j)) != null) {
					actors.add(grid.get(new Location(i, j)));
				}
			}
		}
		return actors;
	}

    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a: actors) {
            if (!(a instanceof Rock) && !(a instanceof Critter)) {
                a.removeSelfFromGrid();
            }
        }
		/**
		 * darken or lighten the color
		 */
        Color color = getColor();
        int red, green, blue;
        if (actors.size() >= c) {
            red = (int) (color.getRed() * (1 - DARKENING_FACTOR));
            green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
            blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
            if (red < 0) red = 0;
            if (green < 0) green = 0;
            if (blue < 0) blue = 0;
        } else {
            red = (int) (color.getRed() * (1 + DARKENING_FACTOR));
            green = (int) (color.getGreen() * (1 + DARKENING_FACTOR));
            blue = (int) (color.getBlue() * (1 + DARKENING_FACTOR));
            if (red > 255) red = 255;
            if (green > 255) green = 255;
            if (blue > 255) blue = 255;
        }
        setColor(new Color(red, green, blue));
    }
}
