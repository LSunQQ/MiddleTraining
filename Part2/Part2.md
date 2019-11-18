### Set 2

1. What is the role of the instance variable sideLength?**

   Look at the code below,  the variable `sideLength` is assigned by another variable `length` and it is the precondition of moving. Therefore, it is the length of the edge and the most longest distance a bug can move in one direction continuously.

   ```java
   // @file: GridWorldCode/projects/boxBug/BoxBug.java
   // @line: 28
   private int sideLength; // defined
   
   // @line: 34~38
   public BoxBug(int length)
   {
       steps = 0;
       sideLength = length; // assigned
   }
   
   // @line: 45~49
   if (steps < sideLength && canMove()) // used
   {
       move();
       steps++;
   }
   ```

   

2. **What is the role of the instance variable steps?**

   Also see the code below, the variable `steps` self-increase when the bug can move and clear to zero when it has to turn. Therefore, it is like a counter the count the steps the bug move in one direction continuously.

   ```java
   // @file: GridWorldCode/projects/boxBug/BoxBug.java
   // @line: 45~55
   if (steps < sideLength && canMove())
   {
       move();
       steps++;
   }
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ```

   

3. **Why is the turn method called twice when steps becomes equal to sideLength?**

   Because the `turn` method turns 45 degrees once, but if it want to move, it need to turn 90 degrees. Therefore, it need to be called twice.

   ```java
   // @file: GridWorldCode/projects/boxBug/BoxBug.java
   // @line: 50~55
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ```

   

4. **Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?**

   Because it import the package `Bug` and in the class `Bug` and the class `BoxBug` extend the class `Bug`, which define the `move` method. Therefore, the class `BoxBug` can use the method `move`.

   ```java
   // @file: GridWorldCode/projects/boxBug/BoxBug.java
   // @line: 19
   import info.gridworld.actor.Bug;
   // @line: 25
   public class BoxBug extends Bug
   
   // @file: GridWorldCode/framework/info/girdworld/actor/Bug.java
   // @line: 71~84
   public void move()
   {
       Grid<Actor> gr = getGrid();
       if (gr == null)
           return;
       Location loc = getLocation();
       Location next = loc.getAdjacentLocation(getDirection());
       if (gr.isValid(next))
           moveTo(next);
       else
           removeSelfFromGrid();
       Flower flower = new Flower(getColor());
       flower.putSelfInGrid(gr, loc);
   }
   ```

   

5. **After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?**

   No. Because the variable `sideLength` only assigned in the constructor function. After constructing a box, there is no any other method to change it.

   ```java
   // @file: GridWorldCode/projects/boxBug/BoxBug.java
   // @line: 34~38
   public BoxBug(int length)
   {
       steps = 0;
       sideLength = length;
   }
   ```

   

6. **Can the path a BoxBug travels ever change? Why or why not?**

   If there is a rock of another bug in front of it, the bug's travels path will change, if there is on another barriers, the path will be the same. 

   ```java
   // @file: GridWorldCode/projects/boxBug/BoxBug.java
   // @line: 50~55
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ```

   

7. **When will the value of steps be zero?**

   There are two time the value of steps be zero. One is the initial time, the variable `steps` is set to zero, another is when the bug meet the edge or can not move, it need to turn and the variable `steps` is also set to zero.

   ```java
   // @file: GridWorldCode/projects/boxBug/BoxBug.java
   // @line: 34~38
   public BoxBug(int length)
   {
       steps = 0;
       sideLength = length;
   }
   
   // @line: 50~55
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ```

   