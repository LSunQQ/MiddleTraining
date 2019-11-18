import info.gridworld.grid.Grid;

public class OccupantInCol {
    private Object occupant;
    private int col;

    OccupantInCol(Object _obj, int _col) {
    	occupant = _obj;
    	col = _col;
    }

    public int getCol() {
    	return col;
    }

    public Object getObj() {
    	return occupant;
    }

    public void setCol(int _col) {
    	col = _col;
    }

    public void setObj(Object _obj) {
    	occupant = _obj;
    }
}