By clicking on a cell containing a bug, flower, or rock, do the following.

1. **Test the setDirection method with the following inputs and complete the table, giving the compass direction each input represents.**

   | Degrees | Compass Direction |
   | :-----: | :---------------: |
   |    0    |       North       |
   |   45    |     Northeast     |
   |   90    |       East        |
   |   135   |     Southeast     |
   |   180   |       South       |
   |   225   |     Southwest     |
   |   270   |       West        |
   |   315   |     Northwest     |
   |   360   |       North       |

2. **Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?**

   I can move it to every grid in the 10*10 grids , and it will keep the direction it towards to. The furthest distance is from the top left to the bottom right.

   If I try to move the bug outside the grid, a dialog box pops up which shows `java.lang.IllegalArgumentException: Location (x, x) is not valid.` and many other messages. Meanwhile, my action is aborted.

3. **Change the color of a bug, a flower, and a rock. Which method did you use?**

   `void setColor(java.awt.Color);`

4. **Move a rock on top of a bug and then move the rock again. What happened to the bug?**

   When the rock move on top of a bug, the bug was covered by the rock. When the rock move away, the bug was disappear.

   
