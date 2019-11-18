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

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains RockHound. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class RockHoundRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        RockHound alice = new RockHound();
        alice.setColor(Color.ORANGE);
        RockHound bob = new RockHound();
        Flower ff = new Flower(Color.GREEN);
        world.add(new Location(3, 3), ff);
        Rock rr = new Rock(Color.PINK);
        Rock rrr = new Rock(Color.YELLOW);
        world.add(new Location(6, 1), rrr);
        world.add(new Location(4, 6), rr);
        world.add(new Location(3, 2), alice);
        world.add(new Location(5, 5), bob);

        world.show();
    }
}