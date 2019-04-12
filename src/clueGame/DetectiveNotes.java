package clueGame;

import java.awt.BorderLayout;
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
	
	private JComboBox playerGuessMenu;
	private JComboBox roomGuessMenu;
	private JComboBox weaponGuessMenu;
	
	private JPanel playersPanel;
	private JPanel roomsPanel;
	private JPanel weaponsPanel;
	private JPanel playersGuessPanel;
	private JPanel roomsGuessPanel;
	private JPanel weaponsGuessPanel;
	private JPanel mainPanel;

	public DetectiveNotes(Board b) {
		setTitle("Login Dialog");
		setSize(750, 750);

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
		
		playerGuessMenu = new JComboBox();
		roomGuessMenu = new JComboBox();
		weaponGuessMenu = new JComboBox();

		for (Player p : b.getPlayers()) {
			playerCheckBoxes.add(new JCheckBox(p.getName()));
			playerGuessMenu.addItem(p.getName());
		}
		for (Card c : b.getRoomCards()) {
			roomCheckBoxes.add(new JCheckBox(c.getName()));
			roomGuessMenu.addItem(c.getName());
		}
		for (Card c : b.getWeaponCards()) {
			weaponCheckBoxes.add(new JCheckBox(c.getName()));
			weaponGuessMenu.addItem(c.getName());
		}

		for (JCheckBox cb : playerCheckBoxes) {
			playersPanel.add(cb);
		}
		for (JCheckBox cb : roomCheckBoxes) {
			roomsPanel.add(cb);
		}
		for (JCheckBox cb : weaponCheckBoxes) {
			weaponsPanel.add(cb);
		}
		playersGuessPanel.add(playerGuessMenu, BorderLayout.CENTER);
		roomsGuessPanel.add(roomGuessMenu, BorderLayout.CENTER);
		weaponsGuessPanel.add(weaponGuessMenu, BorderLayout.CENTER);
		mainPanel.add(playersPanel);
		mainPanel.add(playersGuessPanel);
		mainPanel.add(roomsPanel);
		mainPanel.add(roomsGuessPanel);
		mainPanel.add(weaponsPanel);
		mainPanel.add(weaponsGuessPanel);
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
