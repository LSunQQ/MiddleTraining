//package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	public int [] directionProbability = new int[4];

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);

		for (int i = 0; i < 4; ++i) {
			directionProbability[i] = 1;
		}
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
			ArrayList<Location> lastLocation = crossLocation.pop();
			lastLocation.add(getLocation());
			crossLocation.push(lastLocation);

			ArrayList<Location> presentLocation = new ArrayList<Location>();
			presentLocation.add(getLocation());
			crossLocation.push(presentLocation);
		} else {
			if (!crossLocation.empty()) {
				crossLocation.pop();
				if (!crossLocation.empty()) {
					ArrayList<Location> lastLocation = crossLocation.peek();
					last = lastLocation.get(0);

					int tempDirection = getLocation().getDirectionToward(last);
					setDirection(tempDirection);
					int lessProbability = tempDirection / 90 + 2;
					if (lessProbability > 4) {
						lessProbability -= 4;
					}
					--directionProbability[lessProbability];
					next = last;

					move();
					++stepCount;
				}
			}
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		for (int i = 0; i < 4; ++i) {
			Location tempLocation = loc.getAdjacentLocation(getDirection() + i * 90);

			if (gr.isValid(tempLocation)) {
				Actor actor = gr.get(tempLocation);
				if (actor instanceof Rock && actor.getColor().equals(Color.RED)) {
					isEnd = true;
					valid.add(tempLocation);

					setDirection(getLocation().getDirectionToward(tempLocation));
					moveTo(tempLocation);

					break;
				} else if (actor == null) {
					valid.add(tempLocation);
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Grid<Actor> grid = getGrid();
		if (grid == null) {
			return false;
		}

		ArrayList<Location> validLocations = getValid(getLocation());
		if (validLocations.size() != 0) {
			ArrayList<Location> tempLocations = new ArrayList<Location>();
			for (int i = 0; i < validLocations.size(); ++i) {
				if (grid.get(validLocations.get(i)) instanceof Rock) {
					next = validLocations.get(i);
					return true;
				} else {
					tempLocations.add(validLocations.get(i));
				}
			}
			next = chooseLocation(tempLocations);
			return true;
		}
		return false;
	}

	/**
	 * Choose the best location to move.
	 */
	public Location chooseLocation(ArrayList<Location> tempLocations) {
		if (tempLocations.size() == 1) {
			return tempLocations.get(0);
		} else {
			int sumOfProbability = 0;
			Location bestLocation = null;
			Location [] tempDirection = new Location[4];
			boolean [] hasDirection = new boolean[4];
			for (int i = 0; i < 4; ++i) {
				sumOfProbability += directionProbability[i];
				hasDirection[i] = false;
			}
			for (int i = 0; i < tempLocations.size(); ++i) {
				int presentDirection = getLocation().getDirectionToward(tempLocations.get(i)) / 90;
				hasDirection[presentDirection] = true;
				tempDirection[presentDirection] = tempLocations.get(i);
			}

			int randomDirection = (int) (Math.random() * sumOfProbability);
			int tempProbability = 0;
			for (int i = 0; i < 4; ++i) {
				tempProbability += directionProbability[i];
				if (randomDirection < tempProbability && hasDirection[i]) {
					bestLocation = tempLocations.get(i);
					break;
				}
			}
			if (bestLocation == null) {
				int maxProbability = 0;
				int temp = 0;
				for (int i = 0; i < 4; ++i) {
					int presentLocation = getLocation().getDirectionToward(tempLocations.get(i));
					if (directionProbability[presentLocation / 90] > maxProbability) {
						maxProbability = directionProbability[presentLocation / 90];
						temp = i;
					}
				}
				bestLocation = tempLocations.get(temp);
			}
			return bestLocation;
		}
	}


	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		if (gr.isValid(next)) {
			int moveDirection = getLocation().getDirectionToward(next);
			setDirection(moveDirection);
			++directionProbability[moveDirection / 90];
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}