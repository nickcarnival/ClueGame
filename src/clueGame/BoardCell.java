//use this board cell because the other one is the wrong one...
package clueGame;

public class BoardCell {
	private int row;
	private int column;
	DoorDirection doorDirection;
	private boolean isRoom;
	public boolean isDoorway = false;
	public char initial;
	
	public BoardCell(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}




	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + ", doorDirection=" + doorDirection + ", isRoom="
				+ isRoom + ", isDoorway=" + isDoorway + ", initial=" + initial + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardCell other = (BoardCell) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return isDoorway;
	}

	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return this.doorDirection;
	}

	public char getInitial() {
		// TODO Auto-generated method stub
		return this.initial;
	}

}
