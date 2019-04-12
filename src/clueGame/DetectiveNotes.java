package clueGame;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JDialog {

	private ArrayList<JCheckBox> playerCheckBoxes = new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox> roomCheckBoxes = new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox> weaponCheckBoxes = new ArrayList<JCheckBox>();
	
	private JMenu playerGuessMenu;
	private JMenu roomGuessMenu;
	private JMenu weaponGuessMenu;
	
	private JPanel playersPanel;
	private JPanel roomsPanel;
	private JPanel weaponsPanel;
	private JPanel playersGuessPanel;
	private JPanel roomsGuessPanel;
	private JPanel weaponsGuessPanel;
	private JPanel mainPanel;

	public DetectiveNotes(Board b) {
		setTitle("Detective Notes");
		setSize(750, 750);
		setLayout(new GridLayout(1, 3));

		//this panel contains everything 
		mainPanel = new JPanel(new GridLayout(3, 2));

		playersPanel = new JPanel(new GridLayout(3, 2));
		playersPanel.setBorder(new TitledBorder("Players"));

		roomsPanel = new JPanel(new GridLayout(3, 2));
		roomsPanel.setBorder(new TitledBorder("Rooms"));

		weaponsPanel = new JPanel(new GridLayout(3, 2));
		weaponsPanel.setBorder(new TitledBorder("Weapons"));

		playersGuessPanel = new JPanel();
		playersGuessPanel.setBorder(new TitledBorder("Player Guess"));

		roomsGuessPanel = new JPanel();
		roomsGuessPanel.setBorder(new TitledBorder("Room Guess"));

		weaponsGuessPanel = new JPanel();
		weaponsGuessPanel.setBorder(new TitledBorder("Weapon Guess"));
		
		playerGuessMenu = new JMenu("Player Guess");
		roomGuessMenu = new JMenu("Room Guess");
		weaponGuessMenu = new JMenu("Weapon Guess");

		//add every player to the check boxes
		for (Player p : b.getPlayers()) {
			playerCheckBoxes.add(new JCheckBox(p.getName()));
			JMenuItem m = new JMenuItem(p.getName());
			playerGuessMenu.add(m);
		}
		//add every room to the check boxes
		for (Card c : b.getRoomCards()) {
			roomCheckBoxes.add(new JCheckBox(c.getName()));
			JMenuItem m = new JMenuItem(c.getName());
			playerGuessMenu.add(m);
		}
		//add every card to the check boxes
		for (Card c : b.getWeaponCards()) {
			weaponCheckBoxes.add(new JCheckBox(c.getName()));
			JMenuItem m = new JMenuItem(c.getName());
			playerGuessMenu.add(m);
		}

		/********************************************************************
		 * Add all of the players, cards, and room to their associated panels
		 *********************************************************************/

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
	
	public static void main(String args[]) {
		Board b = Board.getInstance();
		b.setConfigFiles("data/testsMap.csv", "data/rooms.txt");
		b.initialize();
		
		DetectiveNotes dn = new DetectiveNotes(b);
		dn.setVisible(true);
	}
}
