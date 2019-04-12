package clueGame;

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
	
	// combo boxes for guesses of player, room, and weapon
	private JComboBox playerGuessMenu;
	private JComboBox roomGuessMenu;
	private JComboBox weaponGuessMenu;
	
	// seven panels: one to hold each of the above things, plus a main to hold those panels
	private JPanel playersPanel;
	private JPanel roomsPanel;
	private JPanel weaponsPanel;
	private JPanel playersGuessPanel;
	private JPanel roomsGuessPanel;
	private JPanel weaponsGuessPanel;
	private JPanel mainPanel;

	// takes in a board so it knows what players, rooms, and weapons there are
	public DetectiveNotes(Board b) {
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
		
		// each menu is actually a combo box
		playerGuessMenu = new JComboBox();
		roomGuessMenu = new JComboBox();
		weaponGuessMenu = new JComboBox();
		
		// add a none option to each checkbox
		playerGuessMenu.addItem("None");
		roomGuessMenu.addItem("None");
		weaponGuessMenu.addItem("None");

		// go through players, rooms, weapons
		// and add them to the checkboxes and the combo box options
		for (Player p : b.getPlayers()) {
			playerCheckBoxes.add(new JCheckBox(p.getName()));
			playerGuessMenu.addItem(p.getName());
		}
		//add every room to the check boxes
		for (Card c : b.getRoomCards()) {
			roomCheckBoxes.add(new JCheckBox(c.getName()));
			roomGuessMenu.addItem(c.getName());
		}
		//add every card to the check boxes
		for (Card c : b.getWeaponCards()) {
			weaponCheckBoxes.add(new JCheckBox(c.getName()));
			weaponGuessMenu.addItem(c.getName());
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

		playersGuessPanel.add(playerGuessMenu);

		roomsGuessPanel.add(roomGuessMenu);

		weaponsGuessPanel.add(weaponGuessMenu);
		
		// add each chekckbox to the middle of the guess panel
		playersGuessPanel.add(playerGuessMenu, BorderLayout.CENTER);
		roomsGuessPanel.add(roomGuessMenu, BorderLayout.CENTER);
		weaponsGuessPanel.add(weaponGuessMenu, BorderLayout.CENTER);

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
