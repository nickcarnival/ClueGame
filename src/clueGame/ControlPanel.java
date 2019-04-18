/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/*
 * This class controls all of the GUI
 */

public class ControlPanel extends JFrame implements ActionListener {

	private static Board board;
	//these are all set to temporary values, but will later be updated by the board
	private static String whoseTurnString = "No player yet";
	private String pastGuess = "No suggestions yet";
	private String diceValue = "No dice roll yet";

	private JPanel mainPanel;
	
	private static HumanPlayer humanPlayer;
	private static ArrayList<Card> humanCards;

	//mouse stuff
	private ArrayList<Point> points; 

	private static DetectiveNotesState dns;
	
	private JPanel diePanel;
	private JTextField diceRoll;
	
	private JTextField whoseTurnField;
	private JPanel upperBottomPanel;

	private JTextField guess;
	private JPanel guessPanel;

	public ControlPanel() {
		points = new ArrayList<Point>();
		dns = new DetectiveNotesState(board);
		setTitle("Clue Game");
		//seems like a good size
		setSize(750, 750);
		setBackground(Color.GRAY);
		//exit on close so that the program ends when the window closes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(board);
		createLayout();
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
		
        mainPanel = new JPanel(new BorderLayout());
        //file & exit
        JMenuBar fileMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem exit = new JMenuItem("Exit");

        JMenuItem detectiveNotes = new JMenuItem("Detective Notes");
        
        //board
        JPanel boardPanel = new JPanel(new BorderLayout());

        //cards
        JPanel cardPanel = new JPanel(new GridLayout(3, 0));

        JPanel peopleCardPanel = new JPanel();
        JPanel weaponCardPanel = new JPanel();
        JPanel roomCardPanel = new JPanel();

        //contains upperBottomPanel and lowerBottomPanel
        JPanel bottomPanel = new JPanel(new GridLayout(2, 0));

        //contains buttons and whoseturn
        upperBottomPanel = new JPanel(new GridLayout(1, 3)); //2rows 3columns

        //whose turn
        JPanel whoseTurnPanel = new JPanel();

			//this contains the actual text
        whoseTurnField = new JTextField();
        whoseTurnField.setEditable(false);
        whoseTurnField.setText(whoseTurnString);
        //dimensions have to be set or it will cut off the names
        whoseTurnField.setPreferredSize(new Dimension(200, 50));


        //buttons
        JButton nextPlayerButton = new JButton("Next Player");
        JButton makeAccusationButton = new JButton("Make Accusation");

        nextPlayerButton.addActionListener(this);
        makeAccusationButton.addActionListener(this);

        //contains dice roll and guess
        JPanel lowerBottomPanel = new JPanel(new GridLayout(1, 3));

        //dice
        diePanel = new JPanel(new GridLayout(1,2));
        JLabel diceLabel = new JLabel("Dice");
        diceRoll = new JTextField();
        diceRoll.setEditable(false);
        diceRoll.setText(diceValue);
        
        //guess's
        guessPanel = new JPanel(new GridLayout(1,2));
        guess = new JTextField(pastGuess);
        guess.setEditable(false);

        JPanel guessResultPanel = new JPanel(new GridLayout(1,2));
        JLabel guessResultLabel = new JLabel("Respone");
        JTextField guessResult = new JTextField();
        guessResult.setEditable(false);
        guessResultPanel.add(guessResultLabel);
        guessResultPanel.add(guessResult);

        
        
        //set panel sizes
        boardPanel.setMaximumSize(new Dimension(525,520));
        boardPanel.setMinimumSize(new Dimension(525,520));
        boardPanel.setPreferredSize(new Dimension(600, 600));
        cardPanel.setPreferredSize(new Dimension(150, 580));

        //set borders for each panel
        cardPanel.setBorder(new TitledBorder("Card Panel"));
        weaponCardPanel.setBorder(new TitledBorder("Weapons"));
        peopleCardPanel.setBorder(new TitledBorder("People"));
        roomCardPanel.setBorder(new TitledBorder("Rooms"));
        diePanel.setBorder(new TitledBorder("Dice"));
        guessPanel.setBorder(new TitledBorder("Guess"));
        guessResultPanel.setBorder(new TitledBorder("Guess Result"));
        whoseTurnPanel.setBorder(new TitledBorder("Whose Turn"));

        /*
         * Add components to each panel
         */

        //add the board to the board panel

        boardPanel.add(board, BorderLayout.CENTER);

        //adding cards to the card panels
        for(Card c : humanCards) {
        	switch (c.getType()) {
        	case PERSON :
        		JTextField peopleText = new JTextField();
        		peopleText.setText(c.toString());
        		peopleText.setEditable(false);
        		peopleCardPanel.add(peopleText);
        		break;
        	case WEAPON:
        		JTextField weaponText = new JTextField();
        		weaponText.setText(c.toString());
        		weaponText.setEditable(false);
        		weaponCardPanel.add(weaponText);
        		break;
        	case ROOM:
        		JTextField roomText = new JTextField();
        		roomText.setText(c.toString());
        		roomText.setEditable(false);
        		roomCardPanel.add(roomText);
        		break;
        	}
        	
        }

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
        
        //menu bar
        exit.addActionListener(this);
        detectiveNotes.addActionListener(this);
        fileMenu.add(detectiveNotes);
        fileMenu.add(exit);

        fileMenuBar.add(fileMenu);

        //add all of the panels to the main panel
        mainPanel.add(fileMenuBar, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(cardPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        add(mainPanel);
        setVisible(true);

        JOptionPane.showMessageDialog(mainPanel, "You are " + humanPlayer.getName()
        + " and your color is " + humanPlayer.getColorString() + ", press Next Player to begin play");
        
	}
	
	
	//this handles what all of the buttons do
	public void actionPerformed(ActionEvent ae) { 
		String choice = ae.getActionCommand(); 
		switch (choice) {
			case "Exit" :
				System.exit(0);
				break;
			case "Detective Notes" :
				DetectiveNotes dn = new DetectiveNotes(dns);
				dn.setResizable(false);
				dn.setVisible(true);
				break;
			case "Next Player" :
//				if(true) {
				if(humanPlayer.canChangeTurn()) {
					advanceTurn();
					if(!humanPlayer.canChangeTurn()) {
						humanPlayer.setIsTurn(true);
					}
				} else {
					JOptionPane.showMessageDialog(mainPanel,
							"You cannot change your turn yet. You must move first.");
				}
				break;
			case "Make Accusation" :
				if(humanPlayer.isTurn()) {
					System.out.println("Make accusation button causes errors");
					MakeAGuess mag = new MakeAGuess(board);
					mag.setResizable(false);
					mag.setVisible(true);
				}
				break;
		}
	} 	
	
	public void advanceTurn() {
		// update who is playing
		board.setCurrentPlayerIndex((board.getCurrentPlayerIndex() + 1) % board.getPlayers().size());
		Player currentPlayer = board.getPlayers().get(board.getCurrentPlayerIndex());
		// update targets
		board.setUpMove();
		// if computer, move
		if (currentPlayer != humanPlayer) {
			doComputerTurn((ComputerPlayer) currentPlayer);
		} else {
			// if human, flag that we haven't moved yet
			humanPlayer.setIsTurn(true);
			humanPlayer.setHasMoved(false);
		}
		// repaint board--showing targets if player is human, showing computer move if computer
		board.repaint();
		// if human, wait for move here
		// update display of dice roll
		diceValue = Integer.toString(board.getPlayers().get(board.getCurrentPlayerIndex()).getDieRoll());
		diceRoll.setText(diceValue);
		diePanel.repaint();
		// update display of who is playing
		whoseTurnString = board.getPlayers().get(board.getCurrentPlayerIndex()).getName();
		whoseTurnField.setText(whoseTurnString);
		upperBottomPanel.repaint();
	}
	
	public void doComputerTurn(ComputerPlayer currentPlayer) {
		// TODO: accusation
		board.doMoveComputer();
		// if in a room, make a suggestion
		if (currentPlayer.location.isDoorway()) {
			// create suggestion
			Solution suggestion = currentPlayer.createSuggestion();
			// have board handle suggestion
			Player disprovingPlayer = board.handleSuggestion(suggestion);
			guess.setText(suggestion.toString());
		} else {
			// if not in a room, reset guess to default value
			guess.setText("No suggestion right now");
		}
	}
	
	//dice getters and setters
	public void setDiceValue(String dice) {
		diceValue = dice;
	}
	public String getDiceValue() {
		return diceValue;
	}
	
	//guess getters and setters
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

		//set the human player
		humanPlayer = board.getHumanPlayer();
		humanCards = humanPlayer.getMyCards();
		humanPlayer.setHasMoved(true);
		
		whoseTurnString = board.getHumanPlayer().getName();

		board.setCurrentPlayerIndex(board.getCurrentPlayerIndex()-1);
		ControlPanel cp = new ControlPanel();
		cp.setVisible(true);
	}

}
