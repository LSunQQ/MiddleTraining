import static org.junit.Assert.*;
import org.junit.Test;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

public class JumperTest {

    // test the distance it jump forwards and diagonally
    @Test
    public void testJumpForwards() {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();

        world.add(new Location(5, 5), alice);
        alice.act();
        assertEquals(3, alice.getLocation().getRow());
        assertEquals(5, alice.getLocation().getCol());
    }
    
    @Test
    public void testJumpDiagonally() {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
		alice.setDirection(Location.NORTHEAST);
        world.add(new Location(5, 5), alice);
        alice.act();
        assertEquals(3, alice.getLocation().getRow());
        assertEquals(7, alice.getLocation().getCol());
    }
	
    // jump to the cell with a flower or a rock
    @Test
    public void testJumpFlower() {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Flower ff = new Flower();

        world.add(new Location(5, 5), alice);
        world.add(new Location(3, 5), ff);
        alice.act();
        ff.act();
        assertEquals(3, alice.getLocation().getRow());
        assertEquals(5, alice.getLocation().getCol());
    }

    @Test
    public void testJumpRock() {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Rock rr = new Rock();

        world.add(new Location(5, 5), alice);
        world.add(new Location(3, 5), rr);
        alice.act();
        rr.act();
        assertEquals(5, alice.getLocation().getRow());
        assertEquals(5, alice.getLocation().getCol());
    }

	// jump replace the bug
    @Test
    public void testJumpBug() {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Bug bb = new Bug();

        world.add(new Location(5, 5), alice);
        world.add(new Location(4, 5), bb);
        bb.act();
        alice.act();
        assertEquals(3, alice.getLocation().getRow());
        assertEquals(5, alice.getLocation().getCol());
    }

	// jump with a jumper in three conditions
    @Test
    public void testJumpJumper1() {
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
    }

    @Test
    public void testJumpJumper2() {
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
    }
    
    @Test
    public void testJumpJumper3() {
    	ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Jumper bob = new Jumper();

        world.add(new Location(5, 5), alice);
        world.add(new Location(3, 3), bob);
        bob.setDirection(Location.EAST);
        bob.act();
        alice.act();
        assertEquals(5, alice.getLocation().getRow());
        assertEquals(5, alice.getLocation().getCol());
        assertEquals(3, bob.getLocation().getRow());
        assertEquals(5, bob.getLocation().getCol());
    }
}
