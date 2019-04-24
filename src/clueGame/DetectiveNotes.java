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
	
	// seven panels: one to hold each of the above things, plus a main to hold those panels
	private JPanel playersPanel;
	private JPanel roomsPanel;
	private JPanel weaponsPanel;
	private JPanel playersGuessPanel;
	private JPanel roomsGuessPanel;
	private JPanel weaponsGuessPanel;
	private JPanel mainPanel;

	// takes in a board so it knows what players, rooms, and weapons there are
	// takes in a state class so we can store info persistently
	public DetectiveNotes(DetectiveNotesState dns) {
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
		
		// add each checkbox to the correct panel
		for (JCheckBox cb : dns.playerChecks) {
			playersPanel.add(cb);
		}
		for (JCheckBox cb : dns.roomChecks) {
			roomsPanel.add(cb);
		}
		for (JCheckBox cb : dns.weaponChecks) {
			weaponsPanel.add(cb);
		}
		
		// add each combo box to the middle of the guess panel
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
	
}
