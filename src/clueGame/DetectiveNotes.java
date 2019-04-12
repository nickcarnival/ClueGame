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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
		weaponsPanel = new JPanel();
		playersGuessPanel = new JPanel();
		roomsGuessPanel = new JPanel();
		weaponsGuessPanel = new JPanel();

		for (Player p : b.getPlayers()) {
			playerCheckBoxes.add(new JCheckBox(p.getName()));
		}
		for (Card c : b.getRoomCards()) {
			roomCheckBoxes.add(new JCheckBox(c.getName()));
		}
		for (Card c : b.getWeaponCards()) {
			weaponCheckBoxes.add(new JCheckBox(c.getName()));
		}
		add(playersLabel);
		for (JCheckBox cb : playerCheckBoxes) {
			add(cb);
		}
		add(roomsLabel);
		for (JCheckBox cb : roomCheckBoxes) {
			add(cb);
		}
		add(weaponLabel);
		for (JCheckBox cb : weaponCheckBoxes) {
			add(cb);
		}
		playerGuessMenu = new JMenuBar();
		roomGuessMenu = new JMenuBar();
		weaponGuessMenu = new JMenuBar();
	}
}
