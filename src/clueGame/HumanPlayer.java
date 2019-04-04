/*
 * Jordan Newport
 * Nicholas Carnival
 */
package clueGame;

import java.util.ArrayList;
import java.util.Random;

public class HumanPlayer extends Player {
	public Card disproveSuggestion(Solution suggestion) {
		Random random = new Random();
		ArrayList<Card> matches = new ArrayList<Card>();
		if(myCards.contains(suggestion.getRoom())) {
			matches.add(suggestion.getRoom());
		}
		if(myCards.contains(suggestion.getPerson())) {
			matches.add(suggestion.getPerson());
		}
		if(myCards.contains(suggestion.getWeapon())) {
			matches.add(suggestion.getWeapon());
		}
		if(matches.size() > 0) {
			return matches.get(random.nextInt(matches.size()));
		} else {
			return null;
		}
	}

	public HumanPlayer(String color, String name) {
		// TODO Auto-generated constructor stub
		super(color, name);
	}

	
}
