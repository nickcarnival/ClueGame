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
	private Solution suggestion;
	
	protected Card disproveCard;

	private String roomText;
	private String personText;
	private String weaponText;
	
	private JPanel mainPanel = new JPanel(new GridLayout(0, 2));
	private JPanel leftPanel = new JPanel(new GridLayout(4,0));
	private JPanel rightPanel = new JPanel(new GridLayout(4, 0));
	private JPanel personDropPanel;
	private JPanel weaponDropPanel;
	private JPanel roomDropPanel;
	
	private JTextField yourRoomLabel;
	private JTextField roomLabel = new JTextField();
	private JTextField personLabel = new JTextField();
	private JTextField weaponLabel = new JTextField();
	
	private JComboBox<String> peopleBox;
	private JComboBox<String> weaponBox;
	private JComboBox<String> roomBox;
	
	private JDialog personDialog;
	private JDialog weaponDialog;
	private JDialog roomDialog;
	
	private String accuseRoom;
	private Card accuseWeapon;
	private Player accusePerson;

	private static Board board;
	private static HumanPlayer humanPlayer;
	
	private static String currentRoomString;
	
	private Boolean isAccusation; 
	
	private ControlPanel cp;

	public MakeAGuess(Board b, Boolean bool, ControlPanel cp) {
		isAccusation = bool;
		this.cp = cp;
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
		roomDropPanel = new JPanel();
		peopleBox = new JComboBox<String>();
		weaponBox = new JComboBox<String>();
		roomBox = new JComboBox<String>();
		personDropPanel.add(peopleBox);
		weaponDropPanel.add(weaponBox);
		roomDropPanel.add(roomBox);
		

		roomLabel = new JTextField();

		if(isAccusation) {
			//draw room cards
			for (Card room : board.getRoomCards()) {
				roomBox.addItem(room.getName());
			}

			//draw player cards
			for (Player person : board.getPlayers()) {
				peopleBox.addItem(person.getName());
			}
			
			//draw weapon cards
			for (Card weapon : board.getWeaponCards()) {
				weaponBox.addItem(weapon.getName());
			}
			
			roomLabel.setText("Room:");
			roomLabel.setEditable(false);
			
			roomDropPanel.add(roomBox);
			
			leftPanel.add(roomLabel);
			rightPanel.add(roomDropPanel);

		} else {
			//draw player cards
			for (Player p : board.getPlayers()) {
				peopleBox.addItem(p.getName());
			}
			
			//draw weapon cards
			for (Card c : board.getWeaponCards()) {
				weaponBox.addItem(c.getName());
			}
			yourRoomLabel = new JTextField();

			roomText = board.getHumanPlayer().getLocation().getRoomName();
			yourRoomLabel.setText(roomText);
			yourRoomLabel.setEditable(false);

			roomLabel.setText("Your room:");
			roomLabel.setEditable(false);
			
			leftPanel.add(roomLabel);
			rightPanel.add(yourRoomLabel);
		}

		personLabel.setText("Person:");
		personLabel.setEditable(false);

		weaponLabel.setText("Weapon:");
		weaponLabel.setEditable(false);
		
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");

		submitButton.addActionListener(this);
		cancelButton.addActionListener(this);


		//left side of the main panel
		leftPanel.add(personLabel);
		leftPanel.add(weaponLabel);
		leftPanel.add(submitButton);

		mainPanel.add(leftPanel);

		//right side of the main panel
		rightPanel.add(personDropPanel);
		rightPanel.add(weaponDropPanel);
		rightPanel.add(cancelButton);

		mainPanel.add(rightPanel);


		setResizable(false);
		add(mainPanel);
	}
	
	//handles the buttons
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		switch (action) {
			//the submit button
			case "Submit" :
				//this is where the accusation handling happens
				Card accusePlayer, accuseRoom, accuseWeapon;

				accusePlayer = new Card(peopleBox.getSelectedItem().toString(), CardType.PERSON);
				if (isAccusation) {
					accuseRoom = new Card(roomBox.getSelectedItem().toString(), CardType.ROOM);
				} else {
					accuseRoom = new Card(roomText, CardType.ROOM);
				}
				accuseWeapon = new Card(weaponBox.getSelectedItem().toString(), CardType.WEAPON);
				suggestion = new Solution(accuseWeapon, accusePlayer, accuseRoom);

				Player disprovingPlayer = board.handleSuggestion(suggestion, isAccusation);

				if (!isAccusation) {
					//add the disproving card to players cards
					disproveCard = disprovingPlayer.disprovingCard;
					// need to do handling suggestion here
					board.setGuess(suggestion);
					board.setDisprovingPlayer(disprovingPlayer);
					cp.getGuess().setText(board.getGuess().toString());
					cp.getGuessResult().setText(board.getDisprovingPlayer().disprovingCard.getName());
				} else {
					cp.validateAccusation(suggestion, true);
				}
				break;
			case "Cancel" :
				setVisible(false);
				break;
			default:
				break;
		}
		dispose();
		
	}
/*	
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

		MakeAGuess mag = new MakeAGuess(board, true, cp);
		mag.setVisible(true);
	}
	*/
}
