package clueGame;
/*
 * Nicholas Carnival
 * Jordan Newport
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JDialog {
	
	// Lists of checkboxes for player, room and weapon
	private ArrayList<JCheckBox> playerCheckBoxes = new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox> roomCheckBoxes = new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox> weaponCheckBoxes = new ArrayList<JCheckBox>();
	
	// seven panels: one to hold each of the above things, plus a main to hold those panels
	private JPanel playersPanel;
	private JPanel roomsPanel;
	private JPanel weaponsPanel;
	private JPanel playersGuessPanel;
	private JPanel roomsGuessPanel;
	private JPanel weaponsGuessPanel;
	private JPanel mainPanel;

	// takes in a board so it knows what players, rooms, and weapons there are
	public DetectiveNotes(Board b, DetectiveNotesState dns) {
		setTitle("Detective Notes");
		setSize(750, 750);
		setLayout(new GridLayout(1, 3));

		// 2 by 3 grid
		mainPanel = new JPanel(new GridLayout(3, 2));

		// each checkbox panel should have a name and a grid of checkboxes
		playersPanel = new JPanel(new GridLayout(3, 2));
		playersPanel.setBorder(new TitledBorder("Players"));

		roomsPanel = new JPanel(new GridLayout(3, 2));
		roomsPanel.setBorder(new TitledBorder("Rooms"));

		weaponsPanel = new JPanel(new GridLayout(3, 2));
		weaponsPanel.setBorder(new TitledBorder("Weapons"));

		// each guess panel should have a name
		playersGuessPanel = new JPanel();
		playersGuessPanel.setBorder(new TitledBorder("Player Guess"));

		roomsGuessPanel = new JPanel();
		roomsGuessPanel.setBorder(new TitledBorder("Room Guess"));

		weaponsGuessPanel = new JPanel();
		weaponsGuessPanel.setBorder(new TitledBorder("Weapon Guess"));
		
		// add a none option to each combo box
		dns.playerGuess.addItem("None");
		dns.roomGuess.addItem("None");
		dns.weaponGuess.addItem("None");

		// go through players, rooms, weapons
		// and add them to the checkboxes and the combo box options
		for (Player p : b.getPlayers()) {
			dns.playerChecks.add(new JCheckBox(p.getName()));
			playerCheckBoxes.add(dns.playerChecks.get(dns.playerChecks.size()-1));
			dns.playerGuess.addItem(p.getName());
		}
		//add every room to the check boxes
		for (Card c : b.getRoomCards()) {
			dns.roomChecks.add(new JCheckBox(c.getName()));
			roomCheckBoxes.add(dns.roomChecks.get(dns.roomChecks.size()-1));
			dns.roomGuess.addItem(c.getName());
		}
		//add every card to the check boxes
		for (Card c : b.getWeaponCards()) {
			dns.weaponChecks.add(new JCheckBox(c.getName()));
			weaponCheckBoxes.add(dns.weaponChecks.get(dns.weaponChecks.size()-1));
			dns.weaponGuess.addItem(c.getName());
		}
		
		// add each checkbox to the correct panel
		for (JCheckBox cb : playerCheckBoxes) {
			playersPanel.add(cb);
		}
		for (JCheckBox cb : roomCheckBoxes) {
			roomsPanel.add(cb);
		}
		for (JCheckBox cb : weaponCheckBoxes) {
			weaponsPanel.add(cb);
		}
		
		// add each checkbox to the middle of the guess panel
		playersGuessPanel.add(dns.playerGuess, BorderLayout.CENTER);
		roomsGuessPanel.add(dns.roomGuess, BorderLayout.CENTER);
		weaponsGuessPanel.add(dns.weaponGuess, BorderLayout.CENTER);

		mainPanel.add(playersPanel);
		mainPanel.add(playersGuessPanel);
		mainPanel.add(roomsPanel);
		mainPanel.add(roomsGuessPanel);
		mainPanel.add(weaponsPanel);
		mainPanel.add(weaponsGuessPanel);

		//set the main JDialog to be visible, and add main panel to the view
		add(mainPanel);
		setVisible(true);
	}
	
	//allows us to test detective notes without having to go through control panel
	/*
	public static void main(String args[]) {
		Board b = Board.getInstance();
		b.setConfigFiles("data/testsMap.csv", "data/rooms.txt");
		b.initialize();
		
		DetectiveNotes dn = new DetectiveNotes(b);
		dn.setVisible(true);
	}
	*/
}
