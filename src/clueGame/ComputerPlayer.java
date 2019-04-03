/*
 * Jordan Newport
 * Nicholas Carnival
 */
package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	private BoardCell location;
	private BoardCell lastVisited;

	public ComputerPlayer(String color, String name) {
		super(color, name);
	}
	//picks a location, prioritizing rooms
	public BoardCell pickLocation(Set<BoardCell> targets) {
		BoardCell currentCell = null;
		//check if any of the cells are doors
		for(BoardCell b : targets) {
			if(b.isDoorway()) {
				if(b != lastVisited) {
					currentCell = b;
					this.lastVisited = currentCell;
					break;
				}
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
	//this is only for JUnit tests
	public void setLastVisited(BoardCell last) {
		this.lastVisited = last;
	}
	public BoardCell getLocation() {
		return this.location;
	}
	//a suggestion is ...
	public Solution createSuggestion() {
		Solution suggestion = new Solution();
		for(Card c : peopleCards) {
			if(!seenCards.contains(c)) {
				suggestion.setPerson(c);
				break;
			}
		}
		for(Card c : roomCards) {
			if(!seenCards.contains(c)) {
				suggestion.setRoom(c);
				break;
			}
		}
		for(Card c : weaponCards) {
			if(!seenCards.contains(c)) {
				suggestion.setWeapon(c);
				break;
			}
		}
		return suggestion;
		// char currentRoom = location.getInitial();
	}

	public Card disproveSuggestion(Solution suggestion) {
		return new Card();
	}

}
