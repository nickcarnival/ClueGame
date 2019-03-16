package clueGame;
/*
 * Jordan Newport
 * Nicholas Carnival
 * All of the code in this class is just
 * automatically generated functions. Super simple.
 */

public class BoardCell {
	private int row;
	private int column;
	private DoorDirection doorDirection;
	private boolean isRoom;
	private boolean isDoorway;
	private boolean isWalkway;
	private char initial;
	
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

	public void setDoorway(boolean isDoorway) {
		this.isDoorway = isDoorway;
	}

	public boolean isDoorway() {
		return isDoorway;
	}

	public void setWalkway(boolean isWalkway) {
		this.isWalkway = isWalkway;
	}
	public boolean isWalkway() {
		return isWalkway;
	}
	public DoorDirection getDoorDirection() {
		return this.doorDirection;
	}

	public char getInitial() {
		return this.initial;
	}

	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}

	public void setInitial(char initial) {
		this.initial = initial;
	}

	public boolean isRoom() {
		return isRoom;
	}

	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}
}
