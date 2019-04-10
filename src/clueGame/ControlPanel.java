/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/*
 * This class controls all of the GUI
 */

public class ControlPanel extends JFrame{

	private static Board board;
	//these are all set to temporary values, but will later be updated by the board
	private String whoseTurnString = "Miss Scarlet";
	private String pastGuess = "Miss Scarley Lounge Candlestick";
	private String diceValue = "4";
	private BoardCell boardCell;

	public ControlPanel() {

		setTitle("Clue Game");
		//seems like a good size
		setSize(1080, 720);
		//exit on close so that the program ends when the window closes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			System.out.println(action.getSource());
		}
		
	}
	public void createLayout() {

        /*
         * Desired Layout of the GUI:
			 * Board
			 * Card
			 * Weapon
			 * People 
			 * Room
			 * Whose Turn
			 * Buttons
			 * Dice
			 * Guess
         */
		
		

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        
        //board
        JPanel boardPanel = new JPanel(new BorderLayout());

        //cards
        JPanel cardPanel = new JPanel(new GridLayout(3, 0));
        JLabel myCards = new JLabel("My Cards");

        JPanel peopleCardPanel = new JPanel();
        JPanel weaponCardPanel = new JPanel();
        JPanel roomCardPanel = new JPanel();

        //contains upperBottomPanel and lowerBottomPanel
        JPanel bottomPanel = new JPanel(new GridLayout(2, 0));

        //contains buttons and whoseturn
        JPanel upperBottomPanel = new JPanel(new GridLayout(1, 3)); //2rows 3columns

        //whose turn
        JPanel whoseTurnPanel = new JPanel();

			//this contains the actual text
        JTextField whoseTurnField = new JTextField();
        whoseTurnField.setEditable(false);
        whoseTurnField.setText(whoseTurnString );

        //buttons
        JButton nextPlayerButton = new JButton("Next Player");
        JButton makeAccusationButton = new JButton("Make Accusation");

        //contains dice roll and guess
        JPanel lowerBottomPanel = new JPanel(new GridLayout(1, 3));

        //dice
        JPanel diePanel = new JPanel(new GridLayout(1,2));
        JLabel diceLabel = new JLabel("Dice");
        JTextField diceRoll = new JTextField();
        diceRoll.setEditable(false);
        diceRoll.setText(diceValue);
        
        //guess's
        JPanel guessPanel = new JPanel(new GridLayout(1,2));
        JTextField guess = new JTextField(pastGuess);
        guess.setEditable(false);

        JPanel guessResultPanel = new JPanel(new GridLayout(1,2));
        JLabel guessResultLabel = new JLabel("Respone");
        JTextField guessResult = new JTextField();
        guessResult.setEditable(false);
        guessResultPanel.add(guessResultLabel);
        guessResultPanel.add(guessResult);

        
        
        //set panel sizes
        boardPanel.setPreferredSize(new Dimension(640, 640));
        cardPanel.setPreferredSize(new Dimension(150, 680));

        //set borders for each panel
        mainPanel.setBorder(new TitledBorder("File"));
        cardPanel.setBorder(new TitledBorder("Card Panel"));
        weaponCardPanel.setBorder(new TitledBorder("Weapons"));
        peopleCardPanel.setBorder(new TitledBorder("People"));
        roomCardPanel.setBorder(new TitledBorder("Rooms"));
        diePanel.setBorder(new TitledBorder("Dice"));
        guessPanel.setBorder(new TitledBorder("Guess"));
        guessResultPanel.setBorder(new TitledBorder("Guess Result"));
        whoseTurnPanel.setBorder(new TitledBorder("Whose Turn"));
        bottomPanel.setBorder(new TitledBorder("Bottom Panel"));
        boardPanel.setBorder(new TitledBorder("Board Panel"));

        /*
         * Add components to each panel
         */

        //add the board to the board panel

        System.out.println("Board Cell: " + boardCell);
        boardPanel.add(board, BorderLayout.CENTER);

        
        //adding each type of card to the card panel
        cardPanel.add(weaponCardPanel, BorderLayout.NORTH);
        cardPanel.add(peopleCardPanel, BorderLayout.CENTER);

        cardPanel.add(roomCardPanel, BorderLayout.SOUTH);

        diePanel.add(diceLabel);
        diePanel.add(diceRoll);

        whoseTurnPanel.add(whoseTurnField);
        
        guessPanel.add(guess);

        //add whoseturn and buttons to the upper bottom panel
        upperBottomPanel.add(whoseTurnPanel);
        upperBottomPanel.add(nextPlayerButton);
        upperBottomPanel.add(makeAccusationButton);

        //add dice, and guess panels to the bottom most panel
        lowerBottomPanel.add(diePanel);
        lowerBottomPanel.add(guessPanel);
        lowerBottomPanel.add(guessResultPanel);
    
    
        //add both of the bottom panels to the bottom of the GUI
        bottomPanel.add(upperBottomPanel);
        bottomPanel.add(lowerBottomPanel);

        //add all of the panels to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(cardPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        add(mainPanel);
        setVisible(true);
	}
	
	//whose turn getters and setters
	public void setWhoseTurn(String whose) {
		whoseTurnString = whose;
	}

	public String getWhoseTurn() {
		return whoseTurnString;
	}

	//dice getters and setters
	public void setDiceValue(String dice) {
		diceValue = dice;
	}
	public String getDiceValue() {
		return diceValue;
	}
	
	//dice getters and setters
	public void setPastGuess(String past) {
		pastGuess = past;
	}

	public String getPastGuess() {
		return pastGuess;
	}
	
	public static void main(String args[]) {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/testsMap.csv", "data/rooms.txt");		
		board.initialize();

		ControlPanel cp = new ControlPanel();
		cp.setVisible(true);
		
	}

}
