### Set 7

The source code for the Critter class is in the critters directory

1. What methods are implemented in Critter?

   `act`, `getActors`, `processActors`, `getMoveLocation`, `selectMoveLocation` and `makeMove`.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 38
   public void act()
   // @line: 56
   public ArrayList<Actor> getActors()
   // @line: 71
   public void processActors(ArrayList<Actor> actors)
   // @line: 88
   public ArrayList<Location> getMoveLocations()
   // @line: 104
   public Location selectMoveLocation(ArrayList<Location> locs)
   // @line: 125
   public void makeMove(Location loc)
   ```

   

2. What are the five basic actions common to all critters when they act?

   A critter acts by getting a list of other actors, processing that list,getting locations to move to, selecting one of them, and moving to the selected location.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 38~47
   public void act()
   {
       if (getGrid() == null)
           return;
       ArrayList<Actor> actors = getActors();
       processActors(actors);
       ArrayList<Location> moveLocs = getMoveLocations();
       Location loc = selectMoveLocation(moveLocs);
       makeMove(loc);
   }
   ```

   

3. Should subclasses of Critter override the getActors method? Explain.

   Yes, they can Override this method in subclasses to look elsewhere for actors to process.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 56~59
   public ArrayList<Actor> getActors()
   {
       return getGrid().getNeighbors(getLocation());
   }
   ```

   

4. Describe the way that a critter could process actors.

   New actors may be added to empty locations. To remove the selected actors that are not rocks or critters.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 71~78
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

   

5. What three methods must be invoked to make a critter move? Explain each of these methods.

   `getMoveLocations()`, `selectMoveLocation()` and `makeMove()`.

   - `getMoveLocations()`: Gets a list of possible locations for the next move
   - `selectMoveLocation()`: Selects the location for the next move.
   - `makeMove()`: Moves this critter to the given location, or removes this critter from its grid if the location is null.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 88~91
   public ArrayList<Location> getMoveLocations()
   {
       return getGrid().getEmptyAdjacentLocations(getLocation());
   }
   // @line: 104~111
   public Location selectMoveLocation(ArrayList<Location> locs)
   {
       int n = locs.size();
       if (n == 0)
           return getLocation();
       int r = (int) (Math.random() * n);
       return locs.get(r);
   }
   // @line: 125~131
   public void makeMove(Location loc)
   {
       if (loc == null)
           removeSelfFromGrid();
       else
           moveTo(loc);
   }
   ```

   

6. Why is there no Critter constructor?

   Because it is inherits from the class `Actor` and the class `Actor` has a constructor. When instantiate a `Critter` it will call the constructor of the class `Actor`.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 31
   public class Critter extends Actor
   ```

   

### Set 8

The source code for the ChameleonCritter class is in the critters directory

1. Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

   Because the method `processActors()` and the method `makeMove()` have been override. These two method are used in the method `act()`. Therefore, it means that the method `act()` has been changed.

   ```java
   // @file: GridWorldCode/projects/critters/ChameleonCritter.java
   // @line: 36
   public void processActors(ArrayList<Actor> actors)
   // @line: 50
   public void makeMove(Location loc)
   ```

   

2. Why does the makeMove method of ChameleonCritter call super.makeMove?

   In the `makeMove` method of class `ChameleonCritter`, it only count the location of the next position to move. It don't judge whether it is valid or not and it don't move it actually. Therefore, it call `super.makeMove` to do the above things.

   ```java
   // @file: GridWorldCode/projects/critters/ChameleonCritter.java
   // @line: 50~54
   public void makeMove(Location loc)
   {
       setDirection(getLocation().getDirectionToward(loc));
       super.makeMove(loc);
   }
   ```

   

3. How would you make the ChameleonCritter drop flowers in its old location when it moves?

   It should add some statements in the method `makeMove()`.

   ```java
   // @file: GridWorldCode/projects/critters/ChameleonCritter.java
   // @line: 50~54
   public void makeMove(Location loc)
   {
       Location location = getLocation(); // add
       setDirection(getLocation().getDirectionToward(loc));
       super.makeMove(loc);
       if (!loc.equal(location)) { // add
           Flower flower = new Flower(getColor());
           flower.putSelfInGrid(gr, location);
       }
   }
   ```

   

4. Why doesn’t ChameleonCritter override the getActors method?

   Because the class `ChameleonCritter` use the method `getActors` the same as the class `Critter`.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 56~59
   public ArrayList<Actor> getActors()
   {
       return getGrid().getNeighbors(getLocation());
   }
   ```

   

5. Which class contains the getLocation method?

   The class `Actor`.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Actor.java
   // @line: 102~105
   public Location getLocation()
   {
       return location;
   }
   ```

   

6. How can a Critter access its own grid?

   It can call the method `getGrid`.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Actor.java
   // @line: 92~95    
   public Grid<Actor> getGrid()
   {
       return grid;
   }
   ```

   

### Set 9

The source code for the CrabCritter class is reproduced at the end of this part of GridWorld.

1. Why doesn’t CrabCritter override the processActors method?

   Because the class `CrabCritter` inherits from the class `Critter` which has the method `processActors`. The both use the method `processActors` to eat the actor, so it doesn't to override the method.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 71~78
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

   

2. Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

   It is call method `getActors()` to get the actors in the three locations immediately in front, to its front-right and to its front-left. Then it call the method `processActors()` to eat the actors that are not rocks or critters. Therefore, if its neighboring actors are rocks or critters, it will not eat them.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 42~43
   ArrayList<Actor> actors = getActors();
   processActors(actors);
   
   // @file: GridWorldCode/projects/critters/CrabCritter.java
   // @line: 44~57
   public ArrayList<Actor> getActors()
   {
       ArrayList<Actor> actors = new ArrayList<Actor>();
       int[] dirs =
       { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
       for (Location loc : getLocationsInDirections(dirs))
       {
           Actor a = getGrid().get(loc);
           if (a != null)
               actors.add(a);
       }
   
       return actors;
   }
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 71~78
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

   

3. Why is the getLocationsInDirections method used in CrabCritter?

   Because it need to get the location front, front-left, front-right, right or left of the crab.

   ```java
   // @file: GridWorldCode/projects/critters/CrabCritter.java
   // @line: 49
   for (Location loc : getLocationsInDirections(dirs))
   // @line: 67
   for (Location loc : getLocationsInDirections(dirs))
   ```

   

4. If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

   location(4, 3), location(4, 4) and location(4, 5).

   ```java
   // @file: GridWorldCode/projects/critters/CrabCritter.java
   // @line: 44~57
   public ArrayList<Actor> getActors()
   {
       ArrayList<Actor> actors = new ArrayList<Actor>();
       int[] dirs =
       { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
       for (Location loc : getLocationsInDirections(dirs))
       {
           Actor a = getGrid().get(loc);
           if (a != null)
               actors.add(a);
       }
   
       return actors;
   }
   ```

   

5. What are the similarities and differences between the movements of a CrabCritter and a Critter?

   Similarities: They face to the same direction before moving and after moving even when they are in the edge. And the both move randomly.

   Differences:

   - The Critter can move to any neighbor except the rock while the CrabCritter can only move right or left. 
   - The Critter can not change the direction it face to any time while the CrabCritter can change the direction it face to when it couldn't move both left and right.

   ```java
   // @file: GridWorldCode/projects/critters/CrabCritter.java
   // @line: 77~91
   public void makeMove(Location loc)
   {
       if (loc.equals(getLocation()))
       {
           double r = Math.random();
           int angle;
           if (r < 0.5)
               angle = Location.LEFT;
           else
               angle = Location.RIGHT;
           setDirection(getDirection() + angle);
       }
       else
           super.makeMove(loc);
   }
   ```

   

6. How does a CrabCritter determine when it turns instead of moving?

   If there are two actors it couldn't eat both at its left side and its right side, it turns instead of moving.

   ```java
   // @file: GridWorldCode/projects/critters/CrabCritter.java
   // @line: 79~88
   if (loc.equals(getLocation()))
   {
       double r = Math.random();
       int angle;
       if (r < 0.5)
           angle = Location.LEFT;
       else
           angle = Location.RIGHT;
       setDirection(getDirection() + angle);
   }
   ```

   

7. Why don’t the CrabCritter objects eat each other?

   Because it doesn't eat rocks or critters. The `CrabCritter` is inherit from `Critter` so it is also a critter.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Critter.java
   // @line: 71~78
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

   