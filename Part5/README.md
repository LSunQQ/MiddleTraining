There are three folders in Part 5.

The first one is the class `SparseBoundedGrid`. A Bounded Grid implement using a `LinkedList` as a helper class.

The second one is the class `SparseBoundedGrid2`. A Bounded Grid store in a Hash Map.

The third one is the class `UnBoundedGrid2`. An open-ended Unbounded Grid.



1. **Why is this a more time-efficient implementation than BoundedGrid?**

   The class  `SparseBoundedGrid` use the Linked List so its time complexity (Big-Oh) is O(rows) while the time complexity (Big-Oh) of the class `BoundedGrid` is O(rows * columns).

   <br/>

2. **How could you use the UnboundedGrid class to accomplish this task?** 

   In order to decrease the things to store. I let the Unbounded Grid be open-ended. When creating an actor out of bound, the size of the grid grows double.

   <br/>

3. **Which methods of UnboundedGrid could be used without change?** 

   `getNumRows()`, `getNumCols()`, `remove()`. Because they are both unbounded.

   <br/>

4. **Fill in the following chart to compare the expected Big-Oh efficiencies for each implementation of the `SparseBoundedGrid`.**

   (Let r = number of rows, c = number of columns, and n = number of occupied locations)

   | Methods                          | `SparseGridNode` | `LinkedList<OccupantInCol>` | `HashMap ` | `TreeMap ` |
   | :------------------------------- | ---------------- | --------------------------- | ---------- | ---------- |
   | `getNeighbors()`                 | O(c)             | O(c)                        | O(1)       | O(log n)   |
   | `getEmptyAdjacentLocations()`    | O(c)             | O(c)                        | O(1)       | O(log n)   |
   | `getOccupiedAdjacentLocations()` | O(c)             | O(c)                        | O(1)       | O(log n)   |
   | `getOccupiedLocations()`         | O(r+n)           | O(r+n)                      | O(n)       | O(n)       |
   | `get()`                          | O(c)             | O(c)                        | O(1)       | O(log n)   |
   | `put()`                          | O(c)             | O(c)                        | O(1)       | O(log n)   |
   | `remove()`                       | O(c)             | O(c)                        | O(1)       | O(log n)   |

   <br/>

5. **What is the Big-Oh efficiency of the get method?** 

   Since it is an Array\[\]\[\], so the Big-Oh efficiency of the get method is O(1).

   <br/>

6. **What is the efficiency of the put method when the row and column index values are within the current array bounds?** 

   It is also O(1) with the same reason of question 5.

   <br/>

7. **What is the efficiency when the array needs to be resized?**

   If the new size is `size`, the efficiency will be O(size*size).