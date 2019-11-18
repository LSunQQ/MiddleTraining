# Jumper Test

### Test design:

#### I test the jumper in four ways:

1. ###### the distance it jump

   This is the most foundation test. There is only one jumper in the grids.

   ```
   ActorWorld world = new ActorWorld();
   Jumper alice = new Jumper();
   world.add(new Location(5, 5), alice);
   alice.act();
   assertEquals(3, alice.getLocation().getRow());
   assertEquals(5, alice.getLocation().getCol());
   ```

   Then I check it jump in diagonal direction.

   ```java
   ActorWorld world = new ActorWorld();
   Jumper alice = new Jumper();
   alice.setDirection(NORTHEAST);
   world.add(new Location(5, 5), alice);
   alice.act();
   assertEquals(3, alice.getLocation().getRow());
   assertEquals(7, alice.getLocation().getCol());
   ```

2. ###### jump to the cell with a flower or a rock

   - This test check whether the jumper will jump to the cell with a flower in it.

     Since the flower should be replace, so the jumper will jump towards.

     ```
     ActorWorld world = new ActorWorld();
     Jumper alice = new Jumper();
     Flower ff = new Flower();
     world.add(new Location(5, 5), alice);
     world.add(new Location(3, 5), ff);
     alice.act();
     ff.act();
     assertEquals(3, alice.getLocation().getRow());
     assertEquals(5, alice.getLocation().getCol());
     ```

   - This test is similar with the flower test, but the result is contrary.

     ```
     ActorWorld world = new ActorWorld();
     Jumper alice = new Jumper();
     Rock rr = new Rock();
     world.add(new Location(5, 5), alice);
     world.add(new Location(3, 5), rr);
     alice.act();
     rr.act();
     assertEquals(5, alice.getLocation().getRow());
     assertEquals(5, alice.getLocation().getCol());
     ```

     

3. ###### jump replace the bug

   This test check whether the jumper will jump to the cell with a bug in it. Since the bug will also move, so the initial location should be changed.

   ```java
   ActorWorld world = new ActorWorld();
   Jumper alice = new Jumper();
   Bug bb = new Bug();
   
   world.add(new Location(5, 5), alice);
   world.add(new Location(4, 5), bb);
   bb.act();
   alice.act();
   assertEquals(3, alice.getLocation().getRow());
   assertEquals(5, alice.getLocation().getCol());
   ```

4. ###### jump with a jumper

   Since the jumper could not replace a jumper, so I test it in three conditions: The first two conditions is that the front jumper is two cells front of the behind one. The last condition is they want to jump into the same cell.

   - The front one jump first.

     Since the front one is move away, so the behind one can move towards.

     ```java
     ActorWorld world = new ActorWorld();
     Jumper alice = new Jumper();
     Jumper bob = new Jumper();
     
     world.add(new Location(5, 5), alice);
     world.add(new Location(3, 5), bob);
     alice.act();
     bob.act();
     assertEquals(5, alice.getLocation().getRow());
     assertEquals(5, alice.getLocation().getCol());
     assertEquals(1, bob.getLocation().getRow());
     assertEquals(5, bob.getLocation().getCol());
     ```

   - The behind one jump first.

     Since the front one block it, so the behind one could not move towards. The front one can move normally.

     ```java
     ActorWorld world = new ActorWorld();
     Jumper alice = new Jumper();
     Jumper bob = new Jumper();
     
     world.add(new Location(5, 5), alice);
     world.add(new Location(3, 5), bob);
     bob.act();
     alice.act();
     assertEquals(3, alice.getLocation().getRow());
     assertEquals(5, alice.getLocation().getCol());
     assertEquals(1, bob.getLocation().getRow());
     assertEquals(5, bob.getLocation().getCol());
     ```

   - The left one and the behind both wants to jump into the same place

     Since the left one jump first, so the left jump into the cell and the behind one should turn.

     ```java
     ActorWorld world = new ActorWorld();
     Jumper alice = new Jumper();
     Jumper bob = new Jumper();
     
     world.add(new Location(5, 5), alice);
     world.add(new Location(3, 3), bob);
     bob.setDirection(90);
     bob.act();
     alice.act();
     assertEquals(5, alice.getLocation().getRow());
     assertEquals(5, alice.getLocation().getCol());
     assertEquals(3, bob.getLocation().getRow());
     assertEquals(5, bob.getLocation().getCol());
     ```



### Test result:

I do `junit` in ant, so the result is shown in the terminal. I copy it to my test report:

```
lxz@lxz-virtual-machine:~/桌面/shixun/Part3/JumperTest$ ant
Buildfile: /home/lxz/桌面/shixun/Part3/JumperTest/build.xml

clean:
   [delete] Deleting directory /home/lxz/桌面/shixun/Part3/JumperTest/build

compile:
    [mkdir] Created dir: /home/lxz/桌面/shixun/Part3/JumperTest/build/classes
    [javac] Compiling 2 source files to /home/lxz/桌面/shixun/Part3/JumperTest/build/classes

jar:
    [mkdir] Created dir: /home/lxz/桌面/shixun/Part3/JumperTest/build/jar
      [jar] Building jar: /home/lxz/桌面/shixun/Part3/JumperTest/build/jar/JumperTest.jar

junit:
    [junit] Running JumperTest
    [junit] Testsuite: JumperTest
    [junit] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.036 sec
    [junit] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.036 sec
    [junit] 
    [junit] Testcase: testJumpDiagonally took 0.018 sec
    [junit] Testcase: testJumpJumper3 took 0 sec
    [junit] Testcase: testJumpForwards took 0 sec
    [junit] Testcase: testJumpFlower took 0 sec
    [junit] Testcase: testJumpRock took 0 sec
    [junit] Testcase: testJumpBug took 0 sec
    [junit] Testcase: testJumpJumper1 took 0 sec
    [junit] Testcase: testJumpJumper2 took 0 sec

BUILD SUCCESSFUL
Total time: 0 seconds
```



