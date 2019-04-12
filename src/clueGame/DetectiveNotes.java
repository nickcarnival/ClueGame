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

	public DetectiveNotes(Board b) {
		setTitle("Login Dialog");
		setSize(800, 600);
		setLayout(new GridLayout(1, 3));

		playersPanel = new JPanel();
		playersPanel.setBorder(new TitledBorder("Players"));
		roomsPanel = new JPanel();
		roomsPanel.setBorder(new TitledBorder("Rooms"));
		weaponsPanel = new JPanel();
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

		for (Player p : b.getPlayers()) {
			playerCheckBoxes.add(new JCheckBox(p.getName()));
			JMenuItem m = new JMenuItem(p.getName());
			playerGuessMenu.add(m);
		}
		for (Card c : b.getRoomCards()) {
			roomCheckBoxes.add(new JCheckBox(c.getName()));
			JMenuItem m = new JMenuItem(c.getName());
			playerGuessMenu.add(m);
		}
		for (Card c : b.getWeaponCards()) {
			weaponCheckBoxes.add(new JCheckBox(c.getName()));
			JMenuItem m = new JMenuItem(c.getName());
			playerGuessMenu.add(m);
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
		playersGuessPanel.add(playerGuessMenu);
		roomsGuessPanel.add(roomGuessMenu);
		weaponsGuessPanel.add(weaponGuessMenu);
	}
}
