/*
 * Jordan Newport
 * Nicholas Carnival
 */
package clueGame;

import java.util.Set;

public class ComputerPlayer extends Player {
	public ComputerPlayer(String color, String name) {
		super(color, name);
	}
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return new BoardCell(0, 0);
	}
	public void makeAccusation() {
		
	}
	public void createSuggestion() {
		
	}

	public Card disproveSuggestion(Solution suggestion) {
		return new Card();
	}

}
