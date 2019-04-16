package clueGame;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class DetectiveNotesState {

	public ArrayList<JCheckBox> playerChecks;
	public ArrayList<JCheckBox> roomChecks;
	public ArrayList<JCheckBox> weaponChecks;
	public JComboBox<String> playerGuess;
	public JComboBox<String> roomGuess;
	public JComboBox<String> weaponGuess;
	public DetectiveNotesState(Board b) {
		playerChecks = new ArrayList<JCheckBox>();
		roomChecks = new ArrayList<JCheckBox>();
		weaponChecks = new ArrayList<JCheckBox>();
		playerGuess = new JComboBox<String>();
		roomGuess = new JComboBox<String>();
		weaponGuess = new JComboBox<String>();
		// add a none option to each combo box
		playerGuess.addItem("None");
		roomGuess.addItem("None");
		weaponGuess.addItem("None");

		// go through players, rooms, weapons
		// and add them to the checkboxes and the combo box options
		for (Player p : b.getPlayers()) {
			playerChecks.add(new JCheckBox(p.getName()));
			playerGuess.addItem(p.getName());
		}
		//add every room to the check boxes
		for (Card c : b.getRoomCards()) {
			roomChecks.add(new JCheckBox(c.getName()));
			roomGuess.addItem(c.getName());
		}
		//add every card to the check boxes
		for (Card c : b.getWeaponCards()) {
			weaponChecks.add(new JCheckBox(c.getName()));
			weaponGuess.addItem(c.getName());
		}
		
	}

}
