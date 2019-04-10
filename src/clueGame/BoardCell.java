package clueGame;
/*
 * Jordan Newport
 * Nicholas Carnival
 * All of the code in this class is just
 * automatically generated functions. Super simple.
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardCell extends JPanel{
	private int row;
	private int column;

	private int width = 32;
	private int height = 32;
	private int x = 0;
	private int y = 0;

	private DoorDirection doorDirection;
	private boolean isRoom;
	private boolean isDoorway;
	private boolean isWalkway;
	private boolean isNameDrawer;
	private char initial;
	private Board board;

	public BoardCell(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public void draw(Graphics g) { 

		//if is not doorway
		System.out.println("Current Cell: " + " Is Doorway: " + isDoorway + " isNameDrawer: " + isNameDrawer +
				" Row: " + row + " Column: " + column);
		if(!this.isDoorway) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			//x, y, width, height
			g.fillRect( (32 * row), (32 * column), width, height);
			g.setColor(Color.PINK);
			g.fillRect(0, 0, width, height);
		}
		//if is doorway
		else {
			System.out.println("This is a doorway, coming from BoardCell draw");
			super.paintComponent(g);
			g.setColor(Color.GREEN);
			g.fillRect(32 + (2 * row), 32 + (20 * column), width, height);

			//display doorway direction
			switch (this.doorDirection) {
			//display each cell's direction image
			case LEFT:
				System.out.println("left door");
				super.paintComponent(g);
				g.setColor(Color.RED);
				g.drawRect(200, 200, width, height);
				break;
			case RIGHT:
				System.out.println("right door");
				super.paintComponent(g);
				g.setColor(Color.YELLOW);
				g.drawRect(200, 200, width, height);
				break;
			case UP:
				System.out.println("up door");
				super.paintComponent(g);
				g.setColor(Color.ORANGE);
				g.drawRect(200, 200, 21, 21);
				break;
			case DOWN:
				System.out.println("down door");
				super.paintComponent(g);
				g.setColor(Color.GREEN);
				g.drawRect(200, 200, width, height);
				break;
			default:
				break;
			}
		}
		if(isNameDrawer) {
			System.out.print("It is my job to draw the name, and my name is");
			System.out.println(board.getLegend().get(initial));
		}
		repaint();
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int getWidth () {
		return width;
	}

	public int getHeight() {
		return height;
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

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setNameDrawer(boolean isNameDrawer) {
		this.isNameDrawer = isNameDrawer;
	}
}
