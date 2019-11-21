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
import java.util.ArrayList;

/**
 * This class runs a world that contains King crab. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrabRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        KingCrab alice = new KingCrab();
        alice.setColor(Color.ORANGE);
        KingCrab bob = new KingCrab();
        Flower ff = new Flower(Color.GREEN);
        Flower ff2 = new Flower(Color.BLUE);

        world.add(new Location(3, 3), ff);
        world.add(new Location(4, 6), ff2);
        Rock rr = new Rock(Color.PINK);
        Rock rr2 = new Rock();
        world.add(new Location(1, 8), rr);
        world.add(new Location(3, 1), rr2);
        world.add(new Location(3, 2), alice);
        world.add(new Location(5, 5), bob);

        world.show();
    }
}