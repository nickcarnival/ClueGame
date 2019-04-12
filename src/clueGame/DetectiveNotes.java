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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DetectiveNotes extends JDialog {
	private ArrayList<JCheckBox> playerCheckBoxes = new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox> roomCheckBoxes = new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox> weaponCheckBoxes = new ArrayList<JCheckBox>();
	
	private JMenu playerGuessMenu;

	public DetectiveNotes() {
		// TODO Auto-generated constructor stub
	}
}
