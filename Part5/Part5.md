### Set 10

The source code for the AbstractGrid class is in Appendix D.

1. Where is the isValid method specified? Which classes provide an implementation of this method?

   `isValid()` method is specified in the class `Grid`. The classes `BoundedGrid` and `UnboundedGrid` provide an implementation of this method.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/Grid.java
   // @line: 50
   boolean isValid(Location loc);
   
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 60~64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   // @file: GridWorldCode/framework/info/girdworld/grid/UnboundedGrid.java
   // @line: 53~56
   public boolean isValid(Location loc)
   {
       return true;
   }
   ```

   

2. Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?

   The method `getValidAdjacentLocations()`. Because other methods all call the method `getValidAdjacentLocations()` which ensures the location is valid.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/AbstractGrid.java
   // @line: 36~49
   public ArrayList<Location> getValidAdjacentLocations(Location loc)
   {
       ArrayList<Location> locs = new ArrayList<Location>();
   
       int d = Location.NORTH;
       for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
       {
           Location neighborLoc = loc.getAdjacentLocation(d);
           if (isValid(neighborLoc))
               locs.add(neighborLoc);
           d = d + Location.HALF_RIGHT;
       }
       return locs;
   }
   ```

   

3. Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

   `getOccupiedAdjacentLocations()` and `get()` are  called in the `getNeighbors` method.

   The class `AbstractGrid` provides implementations of `getOccupiedAdjacentLocations()` method.

   The classes `BoundedGrid` and `UnboundedGrid` provide implementations of `get()` method.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/AbstractGrid.java
   // @line: 28~34
   public ArrayList<E> getNeighbors(Location loc)
   {
       ArrayList<E> neighbors = new ArrayList<E>();
       for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
           neighbors.add(get(neighborLoc));
       return neighbors;
   }
   
   // @file: GridWorldCode/framework/info/girdworld/grid/AbstractGrid.java
   // @line: 62
   public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
   
   // @file: GridWorldCode/framework/info/girdworld/grid/UnboundedGrid.java
   // @line: 66
   public E get(Location loc)
   
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 85
   public E get(Location loc)
   ```

   

4. Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

   Because we can use the return value both to judge the location is empty or not and add it to the list.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 85~91
   public E get(Location loc)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
       return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
   }
   
   // @file: GridWorldCode/framework/info/girdworld/grid/UnboundedGrid.java
   // @line: 66~71
   public E get(Location loc)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       return occupantMap.get(loc);
   }
   ```

   

5. What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

   If the replacement happens, the direction it check will be only East, South, West and North. And the valid locations are among them.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/AbstractGrid.java
   // @line: 36~49
   public ArrayList<Location> getValidAdjacentLocations(Location loc)
   {
       ArrayList<Location> locs = new ArrayList<Location>();
   
       int d = Location.NORTH;
       for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
       {
           Location neighborLoc = loc.getAdjacentLocation(d);
           if (isValid(neighborLoc))
               locs.add(neighborLoc);
           d = d + Location.HALF_RIGHT;
       }
       return locs;
   }
   ```

   

### Set 11

The source code for the BoundedGrid class is in Appendix D.

1. What ensures that a grid has at least one valid location?

   It is the constructor that ensures the row and the column are more than one, so the grid has at least one valid location

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 41~44
   if (rows <= 0)
       throw new IllegalArgumentException("rows <= 0");
   if (cols <= 0)
       throw new IllegalArgumentException("cols <= 0");
   ```

   

2. How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

   It is determined by `occupantArray[0].length`. according to the constructor precondition, `numRows() > 0`, so `theGrid[0]` is non-null.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 53~58
   public int getNumCols()
   {
       // Note: according to the constructor precondition, numRows() > 0, so
       // theGrid[0] is non-null.
       return occupantArray[0].length;
   }
   ```

   

3. What are the requirements for a Location to be valid in a BoundedGrid?

   The row should more than 0 and no more than the rows set in the constructor. The column should more than 0 and no more than the cols set in the constructor.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 60~64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ```

   

In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.

1. What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

   Type `ArrayList<Location>`. The time complexity (Big-Oh) for this method is O(r*c).

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 66~83
   public ArrayList<Location> getOccupiedLocations()
   {
       ArrayList<Location> theLocations = new ArrayList<Location>();
   
       // Look at all grid locations.
       for (int r = 0; r < getNumRows(); r++)
       {
           for (int c = 0; c < getNumCols(); c++)
           {
               // If there's an object at this location, put it in the array.
               Location loc = new Location(r, c);
               if (get(loc) != null)
                   theLocations.add(loc);
           }
       }
   
       return theLocations;
   }
   ```

   

2. What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

   Type `E`. It needs a parameter which type is `Location`. The time complexity (Big-Oh) for this method is O(1).

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 85~91
   public E get(Location loc)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
       return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
   }
   ```

   

3. What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

   If the location is out of the grid or the object is null, it will cause an exception to be thrown by the put method. The time complexity (Big-Oh) for this method is O(1).

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 93~105
   public E put(Location loc, E obj)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
       if (obj == null)
           throw new NullPointerException("obj == null");
   
       // Add the object to the grid.
       E oldOccupant = get(loc);
       occupantArray[loc.getRow()][loc.getCol()] = obj;
       return oldOccupant;
   }
   ```

   

4. What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

   Type `E`. It will throw `IllegalArgumentException`. The time complexity (Big-Oh) for this method is O(1).

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 107~117
   public E remove(Location loc)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
   
       // Remove the object from the grid.
       E r = get(loc);
       occupantArray[loc.getRow()][loc.getCol()] = null;
       return r;
   }
   ```

   

5. Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

   Yes. Only the time complexity (Big-Oh) for method `getOccupiedLocations` is O(r*c), other are all O(1). Therefore, it is an efficient implementation.



### Set 12

The source code for the UnboundedGrid class is in Appendix D.

1. Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

   - The methods `hashCode` and `equals`. When the `equals` method return true for two locations, the `hashCode` method must return the same value for the two locations.
   - The method `compareTo` should return 0 when the return value of the `equals` method is true. The interface `comparable` should be implement.
   - Yes, it satisfies these requirements.

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
   // @line: 218~221
   public int hashCode()
   {
       return getRow() * 3737 + getCol();
   }
   // @line: 234~246
   public int compareTo(Object other)
   {
       Location otherLoc = (Location) other;
       if (getRow() < otherLoc.getRow())
           return -1;
       if (getRow() > otherLoc.getRow())
           return 1;
       if (getCol() < otherLoc.getCol())
           return -1;
       if (getCol() > otherLoc.getCol())
           return 1;
       return 0;
   }
   ```

   

2. Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

   Because class `BoundedGrid` has the method `isValid` which can checks whether the location is valid or not while the method `isValid` in class `UnboundedGrid` always return true and couldn't check. Therefore, it need to checks for null.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 60~64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   
   // @file: GridWorldCode/framework/info/girdworld/grid/UnboundedGrid.java
   // @line: 53~56
   public boolean isValid(Location loc)
   {
       return true;
   }
   ```

   

3. What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

   The average time complexity (Big-Oh) for the three methods: `get`, `put`, and `remove` is O(1). Because the time complexity of these three methods are all O(1) since it use hash table. If TreeMap were used, all the time complexity will be O(log n).(n is the number of the location that has an actor)

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/UnboundedGrid.java
   // @line: 66~87
   public E get(Location loc)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       return occupantMap.get(loc);
   }
   
   public E put(Location loc, E obj)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       if (obj == null)
           throw new NullPointerException("obj == null");
       return occupantMap.put(loc, obj);
   }
   
   public E remove(Location loc)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       return occupantMap.remove(loc);
   }
   ```

   

4. How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

   The order of the `occupantMap` return in method `getOccupiedLocations()` will be changed. Because traverse the HashMap is different from the way of TreeMap.

   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/UnboundedGrid.java
   // @line: 58~64
   public ArrayList<Location> getOccupiedLocations()
   {
       ArrayList<Location> a = new ArrayList<Location>();
       for (Location loc : occupantMap.keySet())
           a.add(loc);
       return a;
   }
   ```

   

5. Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

   Yes. A map may be better in some way.

   If the grid is nearly full, a two-dimensional array will be much better because a map has to store much more things which cost much memory than a two-dimensional array.
   
   ```java
   // @file: GridWorldCode/framework/info/girdworld/grid/BoundedGrid.java
   // @line: 31
   private Object[][] occupantArray; // the array storing the grid elements
   ```
   
   