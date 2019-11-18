# class `Jmper` design

I let this class inherit from class `Actor` because it is like the other actors such as Rock, Flower and Bug. They are only different from the way they move. Therefore, what I need to design is where to move and how to move.

I use the class `Bug` as a model and change the methods in it.

- in the method `move`, since the bug jump one space once while the jumper jump two space, I need to check the next location of the next location instead of the next location.

  ```java
  Location loc = getLocation();
  Location temp = loc.getAdjacentLocation(getDirection());
  Location next = temp.getAdjacentLocation(getDirection());
  if (gr.isValid(next))
      moveTo(next);
  else
      removeSelfFromGrid();
  ```

- also the same in the method `canMove`. Moreover, it can jump over the flower and the bug.

  ```java
  Location loc = getLocation();
  Location temp = loc.getAdjacentLocation(getDirection());
  Location next = temp.getAdjacentLocation(getDirection());
  if (!gr.isValid(next))
      return false;
  Actor neighbor = gr.get(next);
  return (neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Bug);
  ```

  

Inception: clarify the details of the problem:

1. **What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?**

   If it is a flower, it will replace the flower while if it is a rock, it will turn.

2. **What will a jumper do if the location two cells in front of the jumper is out of the grid?**

   It couldn't jump and it should turn.

3. **What will a jumper do if it is facing an edge of the grid?**

   It also should turn.

4. **What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?**

   If the another actor executes method `act()` first, the jumper behind can jump into the cell while if the another actor executes method `act()` after the jumper behind, the jumper behind couldn't jump into the cell and it only can turn.

5. **What will a jumper do if it encounters another jumper in its path?**

   One jumps ahead while another turns.

6. **Are there any other tests the jumper needs to make?**

   It should test the jumper that jump in diagonal direction.