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

	private int width = 25;
	private int height = 25;
	private int doorWidth = width/5;
	private int doorHeight = height/5;
	private int x = 0;
	private int y = 0;

	private DoorDirection doorDirection;
	private boolean isPlayer;
	private int playerCount = 0;
	private Color playerColor;
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
	
	private Color handleColor(Color foo, boolean specialColor) {
		if (specialColor) {
			return Color.CYAN;
		} else {
			return foo;
		}
	}

	// specialColor defines whether or not we want to highlight this cell
	// (e.g. for target display)
	public BoardCell draw(Graphics g, boolean specialColor) { 
		x = (width * column);
		y = (height * row);

		if(specialColor) {
			super.repaint();
			super.paintComponent(g);
			g.setColor(Color.cyan);
			g.fillRect(x, y, width, height);
		}

		//if is not doorway
		if(this.isWalkway) {
			super.repaint();
			super.paintComponent(g);
			g.setColor(handleColor(Color.YELLOW, specialColor));
			//x, y, width, height
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		
		//if is doorway
		else if (this.isDoorway && this.isRoom){
			//display doorway direction
			switch (this.doorDirection) {
			//display each cell's direction image
			case LEFT:
				super.paintComponent(g);
				g.setColor(handleColor(Color.GRAY, specialColor));
				g.fillRect(x, y, width + 10, height );
				g.setColor(Color.BLUE);
				g.fillRect(x , y, doorWidth, height);
				break;
			case RIGHT:
				super.paintComponent(g);
				g.setColor(handleColor(Color.GRAY, specialColor));
				g.fillRect(x, y, width, height);
				g.setColor(Color.BLUE);
				g.fillRect(x + 20, y, doorWidth, height);
				break;
			case UP:
				super.paintComponent(g);
				g.setColor(handleColor(Color.GRAY, specialColor));
				g.fillRect(x, y, width, height);
				g.setColor(Color.BLUE);
				g.fillRect(x, y, width, doorHeight);
				break;
			case DOWN:
				super.paintComponent(g);
				g.setColor(handleColor(Color.GRAY, specialColor));
				g.fillRect(x, y, width, height);
				g.setColor(Color.BLUE);
				g.fillRect(x, y + 20, width, doorHeight);
				break;
			default:
				break;
			}
			
		}
		//if the cell is a room
		if(this.isRoom && !this.isDoorway) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			g.fillRect(x, y, width, height );
		}
		//if is closet
		if(this.isCloset && !this.isDoorway) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		}
		//draw the players and the player colors
		if(this.isPlayer) {
			super.paintComponent(g);
			g.setColor(this.playerColor);
			g.fillOval(x, y, width, height);
		}
		
		//this displays the room name
		if(isNameDrawer && !this.isDoorway) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			g.drawString(board.getLegend().get(initial), x, y);
			return this;
		} 
		else {
			return null;
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
	
	//deals with having two players on the same square
	public int getPlayerCount() {
		return playerCount;
	}
	
	public void setPlayerCount(int i) {
		this.playerCount = i;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + ", doorDirection=" + doorDirection + ", isRoom="
				+ isRoom + ", isDoorway=" + isDoorway + ", initial=" + initial + ", isNameDrawer=" + isNameDrawer + "]";
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

	public void setPlayerColor(Color color) {
		this.playerColor = color;
	}

	public void setPlayer(boolean player) {
		this.isPlayer = player;
	}

	public void setDoorway(boolean isDoorway) {
		this.isDoorway = isDoorway;
	}

	public boolean isPlayer() {
		return isPlayer;
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

	public boolean isCloset() {
		return isCloset;
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
