/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MakeAGuess extends JFrame{
	private JPanel mainPanel = new JPanel();
	private JPanel leftPanel = new JPanel(new GridLayout(2, 0));
	private JPanel rightPanel = new JPanel(new GridLayout(2, 0));
	
	private JDialog personDialog;
	private JDialog weaponDialog;

	private static Board board;
	private static HumanPlayer humanPlayer;
	
	private static String currentRoomString;

	public MakeAGuess(Board b) {
		MakeAGuess.board = b;
		humanPlayer = board.getHumanPlayer();
		System.out.println("mag has been made: " + board.getHumanPlayer());
		setTitle("Make A Guess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}
	
	private void createLayout() {
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");

		//modal dialog crap
//		JDialog jd = new JDialog(d2, "", Dialog.ModalityType.DOCUMENT_MODAL);

		JTextField roomLabel = new JTextField();
		JTextField personLabel = new JTextField();
		JTextField weaponLabel = new JTextField();
		
		//right side of the main panel
		rightPanel.add(cancelButton);
		mainPanel.add(rightPanel, BorderLayout.EAST);

		//left side of the main panel
		leftPanel.add(submitButton);
		mainPanel.add(leftPanel, BorderLayout.WEST);

		add(mainPanel);
	}
	
	//handles the buttons
	private void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		switch (action) {
			//the submit button
			case "Submit" :
				break;
			case "Cancel" :
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