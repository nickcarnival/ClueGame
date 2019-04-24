/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MakeAGuess extends JFrame implements ActionListener{
	private String roomText;
	private String personText;
	private String weaponText;
	
	private JPanel mainPanel = new JPanel(new GridLayout(0, 2));
	private JPanel leftPanel = new JPanel(new GridLayout(4,0));
	private JPanel rightPanel = new JPanel(new GridLayout(4, 0));
	private JPanel personDropPanel;
	private JPanel weaponDropPanel;
	
	private JComboBox<String> peopleBox;
	private JComboBox<String> weaponBox;
	
	private JDialog personDialog;
	private JDialog weaponDialog;
	private JDialog roomDialog;

	private static Board board;
	private static HumanPlayer humanPlayer;
	
	private static String currentRoomString;
	
	private Boolean isAccusation; 

	public MakeAGuess(Board b) {
		MakeAGuess.board = b;
		humanPlayer = board.getHumanPlayer();
		setTitle("Make A Guess");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}
	
	private void createLayout() {
		personDropPanel = new JPanel();
		weaponDropPanel = new JPanel();
		peopleBox = new JComboBox<String>();
		weaponBox = new JComboBox<String>();
		personDropPanel.add(peopleBox);
		weaponDropPanel.add(weaponBox);
		
		for (Player p : board.getPlayers()) {
			peopleBox.addItem(p.getName());
		}
		
		for (Card c : board.getWeaponCards()) {
			weaponBox.addItem(c.getName());
		}
		
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");

		submitButton.addActionListener(this);
		cancelButton.addActionListener(this);
		//modal dialog crap
//		JDialog jd = new JDialog(d2, "", Dialog.ModalityType.DOCUMENT_MODAL);

		JTextField roomLabel = new JTextField();
		JTextField personLabel = new JTextField();
		JTextField weaponLabel = new JTextField();

		JTextField yourRoomLabel = new JTextField();

		System.out.println("human room: " + board.getHumanPlayer().getLocation());
		roomText = board.getHumanPlayer().getLocation().getRoomName();
		yourRoomLabel.setText(roomText);
		yourRoomLabel.setEditable(false);

		roomLabel.setText("Your room:");
		roomLabel.setEditable(false);

		personLabel.setText("Person:");
		personLabel.setEditable(false);

		weaponLabel.setText("Weapon:");
		weaponLabel.setEditable(false);

		//left side of the main panel
		leftPanel.add(roomLabel);
		leftPanel.add(personLabel);
		leftPanel.add(weaponLabel);
		leftPanel.add(submitButton);

		mainPanel.add(leftPanel);

		//right side of the main panel
		rightPanel.add(yourRoomLabel);
		rightPanel.add(personDropPanel);
		rightPanel.add(weaponDropPanel);
		rightPanel.add(cancelButton);

		mainPanel.add(rightPanel);


		mainPanel.setPreferredSize(new Dimension(3000, 3000));
		setResizable(false);
		add(mainPanel);
	}
	
	//handles the buttons
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		switch (action) {
			//the submit button
			case "Submit" :
				break;
			case "Cancel" :
				setVisible(false);
				dispose();
				break;
			default:
				break;
		}
		
	}
	
	public static void main(String args[]) {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/testsMap.csv", "data/rooms.txt");		
		board.initialize();

		//set the human player
		humanPlayer = board.getHumanPlayer();
		ArrayList<Card> humanCards = humanPlayer.getMyCards();

		board.setCurrentPlayerIndex(board.getCurrentPlayerIndex()-1);
		
		currentRoomString = humanPlayer.getLocation().getName(); 
		System.out.println("Current room: " + currentRoomString);

		MakeAGuess mag = new MakeAGuess(board);
		mag.setVisible(true);
	}
}
