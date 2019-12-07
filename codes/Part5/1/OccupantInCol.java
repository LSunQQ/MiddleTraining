import info.gridworld.grid.Grid;

public class OccupantInCol {
    private Object occupant;
    private int col;

    OccupantInCol(Object newObject, int newCol) {
    	occupant = newObject;
    	col = newCol;
    }

    public int getCol() {
    	return col;
    }

    public Object getObj() {
    	return occupant;
    }

    public void setCol(int newCol) {
    	col = newCol;
    }

    public void setObj(Object newObject) {
    	occupant = newObject;
    }
}