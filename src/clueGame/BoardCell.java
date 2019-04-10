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
	private boolean isCloset;
	private boolean isNameDrawer;
	private char initial;
	private Board board;

	public BoardCell(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public void draw(Graphics g) { 


		x = (width * row);
		y = (height * column);

		//if is not doorway
		if(!this.isDoorway) {
			super.repaint();
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			//x, y, width, height
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		//if is doorway
		else if (isDoorway){
			//display doorway direction
			switch (this.doorDirection) {
			//display each cell's direction image
			case LEFT:
				super.paintComponent(g);
				g.setColor(Color.gray);
				g.drawRect(x, y, width + 10, height );
				g.drawRect(x , y, width + 10, height );
				break;
			case RIGHT:
				super.paintComponent(g);
				g.setColor(Color.gray);
				g.fillRect((width * row), (height * column), width, height);
				break;
			case UP:
				super.paintComponent(g);
				g.setColor(Color.gray);
				g.fillRect((width * row), (height * column), width, height);
				break;
			case DOWN:
				super.paintComponent(g);
				g.setColor(Color.gray);
				g.fillRect((width * row), (height * column), width, height);
				break;
			default:
				g.setColor(Color.ORANGE);
				g.fillRect((width * row), (height * column), width, height);
				break;
			}
			
		}
		//if the cell is a room
		if(this.isRoom) {
			System.out.println("This is a room: " + this);
			super.paintComponent(g);
			g.setColor(Color.gray);
			g.fillRect((width * row), (height * column), width, height );
		}
		//if is closet
		if(this.isCloset) {
			System.out.println("This is a closet");
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillRect((width * row), (height * column), width, height);
		}
		if(isNameDrawer) {
			System.out.print("It is my job to draw the name, and my name is");
			System.out.println(board.getLegend().get(initial));
			super.paintComponent(g);
			g.setColor(Color.white);
			g.drawString(board.getLegend().get(initial), x, y + 32);
		}
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
	
	public void setIsCloset(Boolean bool) {
		this.isCloset = bool;
	}
}
