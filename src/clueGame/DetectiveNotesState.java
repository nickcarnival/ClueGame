package clueGame;

import java.util.ArrayList;

public class DetectiveNotesState {

	public ArrayList<String> playerChecks;
	public ArrayList<String> roomChecks;
	public ArrayList<String> weaponChecks;
	public String playerGuess;
	public String roomGuess;
	public String weaponGuess;
	public DetectiveNotesState() {
		playerChecks = new ArrayList<String>();
		roomChecks = new ArrayList<String>();
		weaponChecks = new ArrayList<String>();
		playerGuess = new String();
		roomGuess = new String();
		weaponGuess = new String();
	}

}
