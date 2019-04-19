/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

import java.awt.BorderLayout;
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
	private JPanel mainPanel = new JPanel(new GridLayout(0, 2));
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	
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
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}
	
	private void createLayout() {
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");

		submitButton.addActionListener(this);
		cancelButton.addActionListener(this);
		//modal dialog crap
//		JDialog jd = new JDialog(d2, "", Dialog.ModalityType.DOCUMENT_MODAL);

		JTextField roomLabel = new JTextField();
		JTextField personLabel = new JTextField();
		JTextField weaponLabel = new JTextField();
		
		//left side of the main panel
		leftPanel.add(submitButton);
		mainPanel.add(leftPanel);

		//right side of the main panel
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
