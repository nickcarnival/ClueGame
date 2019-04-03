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
	
	public Solution createSuggestion() {
		Solution suggestion = new Solution();
		Random random = new Random();
		// set person to one we haven't seen
		ArrayList<Card> people = new ArrayList<Card>();
		for(Card c : board.getPeopleCards()) {
			if(!seenCards.contains(c)) {
				people.add(c);
			}
		}
		suggestion.setPerson(people.get(random.nextInt(people.size())));

		// set weapon to one we haven't seen
		ArrayList<Card> weapons = new ArrayList<Card>();
		for(Card c : board.getWeaponCards()) {
			if(!seenCards.contains(c)) {
				weapons.add(c);
			}
		}
		suggestion.setWeapon(weapons.get(random.nextInt(weapons.size())));

		// set room to one we're in
		char currentRoom = location.getInitial();
		String room = board.getLegend().get(currentRoom);
		for (Card c : board.getRoomCards()) {
			if (c.getName().equals(room)) {
				suggestion.setRoom(c);
				break;
			}
		}
		return suggestion;
	}

	public Card disproveSuggestion(Solution suggestion) {
		return new Card();
	}

}
