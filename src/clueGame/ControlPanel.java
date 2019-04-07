/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

	//these values go in the text fields
	private String whoseTurnString = "Miss Scarlet";
	private String pastGuess = "Miss Scarley Lounge Candlestick";
	private String diceValue = "4";
	private String emptyString = "    ";

	public ControlPanel() {

		setTitle("Clue Game");
		setSize(1080, 720);
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
		
        JLabel board = new JLabel("board");

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        
        //board
        JPanel boardPanel = new JPanel();

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

        JPanel guessResultPanel = new JPanel(new GridLayout(1,2));
        JLabel guessResultLabel = new JLabel("Respone");
        JTextField guessResult = new JTextField();
        guessResult.setEditable(false);
        guessResultPanel.add(guessResultLabel);
        guessResultPanel.add(guessResult);

        
        boardPanel.add(board);
        
        board.setPreferredSize(new Dimension(550, 550));
        cardPanel.setPreferredSize(new Dimension(150, 680));

        //setting border colors
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

        //adding each type of card to the card panel
        cardPanel.add(weaponCardPanel, BorderLayout.NORTH);
        cardPanel.add(peopleCardPanel, BorderLayout.CENTER);
        cardPanel.add(roomCardPanel, BorderLayout.SOUTH);

        diePanel.add(diceLabel);
        diePanel.add(diceRoll);

        whoseTurnPanel.add(whoseTurnField);
        
        guessPanel.add(guess);

        upperBottomPanel.add(whoseTurnPanel);
        upperBottomPanel.add(nextPlayerButton);
        upperBottomPanel.add(makeAccusationButton);

        lowerBottomPanel.add(diePanel);
        lowerBottomPanel.add(guessPanel);
        lowerBottomPanel.add(guessResultPanel);
    
    
        bottomPanel.add(upperBottomPanel);
        bottomPanel.add(lowerBottomPanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.WEST);
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
	
	
	public static void main(String args[]) {
		ControlPanel cp = new ControlPanel();
		cp.setVisible(true);
		
	}

}
