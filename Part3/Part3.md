### Set 3

Assume the following statements when answering the following questions.

```java
Location loc1 = new Location(4, 3);
Location loc2 = new Location(3, 4);
```

1. **How would you access the row value for loc1?**

   I can use the method `getRow()`

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Location.java
   // @line: 110~113
   public int getRow()
   {
       return row;
   }
   ```

   

2. **What is the value of b after the following statement is executed?**

   ```java
   boolean b = loc1.equals(loc2);
   ```

   The value of b is 0 because loc1 and loc2 are not in the same location.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Location.java
   // @line: 205~212
   public boolean equals(Object other)
   {
       if (!(other instanceof Location))
           return false;
   
       Location otherLoc = (Location) other;
       return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
   }
   ```

   

3. **What is the value of loc3 after the following statement is executed?**

   ```java
   Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
   ```

   The value of loc3 is (4, 4) (loc3.row = 4, loc3.col = 4)

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Location.java
   // @line: 130~169
   public Location getAdjacentLocation(int direction)
   {
       // reduce mod 360 and round to closest multiple of 45
       int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
       if (adjustedDirection < 0)
           adjustedDirection += FULL_CIRCLE;
   
       adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
       int dc = 0;
       int dr = 0;
       if (adjustedDirection == EAST)
           dc = 1;
       else if (adjustedDirection == SOUTHEAST)
       {
           dc = 1;
           dr = 1;
       }
       else if (adjustedDirection == SOUTH)
           dr = 1;
       else if (adjustedDirection == SOUTHWEST)
       {
           dc = -1;
           dr = 1;
       }
       else if (adjustedDirection == WEST)
           dc = -1;
       else if (adjustedDirection == NORTHWEST)
       {
           dc = -1;
           dr = -1;
       }
       else if (adjustedDirection == NORTH)
           dr = -1;
       else if (adjustedDirection == NORTHEAST)
       {
           dc = 1;
           dr = -1;
       }
       return new Location(getRow() + dr, getCol() + dc);
   }
   ```

   

4. **What is the value of dir after the following statement is executed?**

   ```java
   int dir = loc1.getDirectionToward(new Location(6, 5));
   ```

   The value of dir is 135.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Location.java
   // @line: 178~195
   public int getDirectionToward(Location target)
   {
       int dx = target.getCol() - getCol();
       int dy = target.getRow() - getRow();
       // y axis points opposite to mathematical orientation
       int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));
   
       // mathematical angle is counterclockwise from x-axis,
       // compass angle is clockwise from y-axis
       int compassAngle = RIGHT - angle;
       // prepare for truncating division by 45 degrees
       compassAngle += HALF_RIGHT / 2;
       // wrap negative angles
       if (compassAngle < 0)
           compassAngle += FULL_CIRCLE;
       // round to nearest multiple of 45
       return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
   }
   ```

   

5. **How does the getAdjacentLocation method know which adjacent location to return?**

   There is a parameter `direction` which tells the method `getAdjacentLoaction` the adjacent location. It use some static variable like `HALF_RIGHT` 、`FULL_CIRCLE` to calculate the direction between 0 degree and 360 degree. At last, plus with its row and column.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Location.java
   // @line: 130
   public Location getAdjacentLocation(int direction)
   {
       // reduce mod 360 and round to closest multiple of 45
       int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
       if (adjustedDirection < 0)
           adjustedDirection += FULL_CIRCLE;
   
       adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
       int dc = 0;
       int dr = 0;
       if (adjustedDirection == EAST)
           dc = 1;
       else if (adjustedDirection == SOUTHEAST)
       {
           dc = 1;
           dr = 1;
       }
       else if (adjustedDirection == SOUTH)
           dr = 1;
       else if (adjustedDirection == SOUTHWEST)
       {
           dc = -1;
           dr = 1;
       }
       else if (adjustedDirection == WEST)
           dc = -1;
       else if (adjustedDirection == NORTHWEST)
       {
           dc = -1;
           dr = -1;
       }
       else if (adjustedDirection == NORTH)
           dr = -1;
       else if (adjustedDirection == NORTHEAST)
       {
           dc = 1;
           dr = -1;
       }
       return new Location(getRow() + dr, getCol() + dc);
   }
   ```

   

### Set 4

1. How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

   I can use method  `ArrayList<Location> getOccupiedLocations()` to obtain a count of the objects in a grid. Because there is no function count the empty locations directly, so I can use the result of the occupied locations to count it.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Grid.java
   // @line: 85
   ArrayList<Location> getOccupiedLocations();
   
   EmptyLocations = getNumRows() * getNumCols() - getOccupiedLocations().size;
   ```

   

2. How can you check if location (10,10) is in a grid?

   I can use the method `boolean isValid(Location loc)` to check.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Grid.java
   // @line: 50
   boolean isValid(Location loc);
   ```

   

3. Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

   Because it is a interface. It doesn't need to implement the methods. I can find the implementations of these method in the file `AbstractGrid.java`, `BoundedGrid.java` and `UnboundedGrid.java`

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Grid.java
   // @line: 29
   public interface Grid<E>
   
   // @file: GridWorldCode/framework/info/girdworld/grid/AbstractGrid.java
   // @line: 26
   public abstract class AbstractGrid<E> implements Grid<E>
   
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 29
   public class BoundedGrid<E> extends AbstractGrid<E>
   
   // @file: GridWorldCode/framework/info/girdworld/grid/UnboundedGrid.java
   // @line: 31
   public class UnboundedGrid<E> extends AbstractGrid<E>
   ```

   

4. All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

   No, the `ArrayList` can expand its capacity automatically. Every we call the methods, we don't know the  size of the array so we need it allocate memory itself. Therefore, the `ArrayList` is much better.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Grid.java
   // @line: 85
   ArrayList<Location> getOccupiedLocations();
   // @line: 96
   ArrayList<Location> getValidAdjacentLocations(Location loc);
   // @line: 107
   ArrayList<Location> getEmptyAdjacentLocations(Location loc);
   // @line: 118
   ArrayList<Location> getOccupiedAdjacentLocations(Location loc);
   // @line: 129
   ArrayList<E> getNeighbors(Location loc);
   ```

   

### Set 5

1. Name three properties of every actor.

   location, direction, color.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Actor.java
   // @line: 32~35
   private Location location;
   private int direction;
   private Color color;
   ```

   

2. When an actor is constructed, what is its direction and color?

   The color is `Color.Blue` and the direction is `Location.NORTH`.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Actor.java
   // @line: 41~42
   color = Color.BLUE;
   direction = Location.NORTH;
   ```

   

3. Why do you think that the Actor class was created as a class instead of an interface?

   If it was created as an interface, it couldn't be instantiated. However, in some files like `Bug.java` and `ActorWorld.java`, they use `Actor` as an instantiate object and call its method. Therefore, the `Actor` class need to be created as a class.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/ActorWorld.java
   // @line: 41~42
   public Actor remove(Location loc)
   {
       Actor occupant = getGrid().get(loc);
       if (occupant == null)
           return null;
       occupant.removeSelfFromGrid();
       return occupant;
   }
   
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 100~101
   Actor neighbor = gr.get(next);
   return (neighbor == null) || (neighbor instanceof Flower);
   ```

   

4. Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

   - No. It will throw `java.lang.IllegalStateException: This actor is already contained in a grid.`

     ```java
     // @file: GridWorldCode/framework/info/girdworld/actor/Actor.java
     // @line: 135~137
     if (grid != null)
         throw new IllegalStateException(
         "This actor is already contained in a grid.");
     ```

   - No. It will throw `java.lang.IllegalStateException: This actor is not contained in a grid.`

     ```java
     // @file: GridWorldCode/framework/info/girdworld/actor/Actor.java
     // @line: 157~158
     if (grid == null)
         throw new IllegalStateException(
         "This actor is not contained in a grid.");
     ```

   - Yes. When it is removed, the actor is in a grid, so it can be removed successfully. When put itself back, the actor is not in a grid, so it can also be put in successfully.

   

5. How can an actor turn 90 degrees to the right?

   Call the method `public void setDirection(int newDirection)` and use the `Location.Right` as the parameter.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Actor.java
   // @line: 80~85
   public void setDirection(int newDirection)
   {
       direction = newDirection % Location.FULL_CIRCLE;
       if (direction < 0)
           direction += Location.FULL_CIRCLE;
   }
   ```

   

   

### Set 6

1. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

   It is the following three statements.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 97~99
   Location next = loc.getAdjacentLocation(getDirection());
   if (!gr.isValid(next))
       return false;
   ```

   

2. Which statement(s) in the canMove method determines that a bug will not walk into a rock?

   It is the following two statements.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 100~101
   Actor neighbor = gr.get(next);
   return (neighbor == null) || (neighbor instanceof Flower);
   ```

   

3. Which methods of the Grid interface are invoked by the canMove method and why?

   The method `isValid()` and the method `get()`.  `isValid()` is used to judge whether the next position  to go is in the girds or not. It ensures that the bug will not move out of the grids. `get()` method is used to get the location of the next position to go. And then judge whether it is the rock or not. It ensures that the bug will not walk into a rock.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 97~101
   Location next = loc.getAdjacentLocation(getDirection());
   if (!gr.isValid(next))
       return false;
   Actor neighbor = gr.get(next);
   return (neighbor == null) || (neighbor instanceof Flower);
   ```

   

4. Which method of the Location class is invoked by the canMove method and why?

   The method `getAdjacentLocation()`. This method count the location of the next position to go which use the judge whether the next position is valid or not.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 97
   Location next = loc.getAdjacentLocation(getDirection());
   ```

   

5. Which methods inherited from the Actor class are invoked in the canMove method?

   `getGrid()`, `getLocation()`, `getDirection()`.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 93
   Grid<Actor> gr = getGrid();
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 96~97
   Location loc = getLocation();
   Location next = loc.getAdjacentLocation(getDirection());
   ```

   

6. What happens in the move method when the location immediately in front of the bug is out of the grid?

   It will call the method `removeSelfFromGrid()`.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 78~81
   if (gr.isValid(next))
       moveTo(next);
   else
       removeSelfFromGrid();
   ```

   

7. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

   The variable loc is needed and it could not be avoid by calling `getLocation()` multiple times. After calling the method `moveTo` or `removeSelfFromGrid()`, the location will be changed. Therefore, we need a variable to store the location before moving to set the position of the flower.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 76~83
   Location loc = getLocation();
   Location next = loc.getAdjacentLocation(getDirection());
   if (gr.isValid(next))
       moveTo(next);
   else
       removeSelfFromGrid();
   Flower flower = new Flower(getColor());
   flower.putSelfInGrid(gr, loc);
   ```

   

8. Why do you think the flowers that are dropped by a bug have the same color as the bug?

   Because when initializing the flower, it use the method `getColor()` to get the color of the bug to set the color of the flower.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 82
   Flower flower = new Flower(getColor());
   ```

   

9. When a bug removes itself from the grid, will it place a flower into its previous location?

   Yes. After judge the location is valid or not, it will set a flower. Therefore, it will place a flower when a bug  remove itself from the grid.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 82~83
   Flower flower = new Flower(getColor());
   flower.putSelfInGrid(gr, loc);
   ```

   

10. Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

    It is the following two statements. The first one store the location and the second one use it to place the flower.

    ```java
    // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
    // @line: 76
    Location loc = getLocation();
    // @line: 83
    flower.putSelfInGrid(gr, loc);
    ```

    

11. If a bug needs to turn 180 degrees, how many times should it call the turn method?

    Four times. 45 degrees for once.

    ```java
    // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
    // @line: 62~65
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    ```

    