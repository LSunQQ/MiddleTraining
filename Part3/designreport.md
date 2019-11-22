### Inception: clarify the details of the problem:

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