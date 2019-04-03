/*
 * Jordan Newport
 * Nicholas Carnival
 */
package clueGame;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	private BoardCell location;
	private BoardCell lastVisited;

	public ComputerPlayer(String color, String name) {
		super(color, name);
	}
	public BoardCell pickLocation(Set<BoardCell> targets) {
		BoardCell currentCell = null;
		//check if any of the cells are doors
		for(BoardCell b : targets) {
			if(b.isDoorway()) {
				currentCell = b;
				this.lastVisited = currentCell;
				break;
			}
		}
		//if there are no rooms set to a random cell
		if(currentCell == null) {
			currentCell = getRandomCell(targets);
		}
		this.location = currentCell;
		return currentCell;
	}
	//randomly selects a board cell from a set
	private BoardCell getRandomCell(Set<BoardCell> boardSet) {
		BoardCell randomCell = null;
		int randomInt = new Random().nextInt(boardSet.size());
		int i = 0;
		for(BoardCell cell : boardSet) {
			if(i == randomInt) {
				randomCell = cell;
				this.lastVisited = randomCell;
				break;
			}
			i++;
		}
		return randomCell;
		
	}
	public BoardCell getLocation() {
		return this.location;
	}
	public void makeAccusation() {
		
	}
	public void createSuggestion() {
		
	}

	public Card disproveSuggestion(Solution suggestion) {
		return new Card();
	}

}
