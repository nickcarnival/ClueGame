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
	public DetectiveNotesState() {
		playerChecks = new ArrayList<JCheckBox>();
		roomChecks = new ArrayList<JCheckBox>();
		weaponChecks = new ArrayList<JCheckBox>();
		playerGuess = new JComboBox<String>();
		roomGuess = new JComboBox<String>();
		weaponGuess = new JComboBox<String>();
	}

}
