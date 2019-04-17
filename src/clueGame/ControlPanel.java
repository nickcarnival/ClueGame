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
	private ArrayList<BoardCell> targets;
	//these are all set to temporary values, but will later be updated by the board
	private static String whoseTurnString = "Miss Scarlet";
	private String pastGuess = "Miss Scarlet Lounge Candlestick";
	private String diceValue = "No dice roll yet";

	private JPanel mainPanel;
	
	private static HumanPlayer humanPlayer;
	private static ArrayList<Card> humanCards;

	//mouse stuff
	private ArrayList<Point> points; 

	private static DetectiveNotesState dns;

	public ControlPanel() {
		points = new ArrayList<Point>();
		dns = new DetectiveNotesState(board);
		setTitle("Clue Game");
		//seems like a good size
		setSize(750, 750);
		//exit on close so that the program ends when the window closes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(new dotsListener());
		createLayout();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.PINK);
		for(Point p : points) {
			g.fillOval(p.x, p.y, 100, 100);
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
		
        mainPanel = new JPanel(new BorderLayout());
        //file & exit
        JMenuBar fileMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem exit = new JMenuItem("Exit");

        JMenuItem detectiveNotes = new JMenuItem("Detective Notes");

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

        nextPlayerButton.addActionListener(this);
        makeAccusationButton.addActionListener(this);

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
        boardPanel.setMaximumSize(new Dimension(600,600));
        boardPanel.setMinimumSize(new Dimension(600,600));
        boardPanel.setPreferredSize(new Dimension(600, 600));
        cardPanel.setPreferredSize(new Dimension(150, 680));

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

        whoseTurnField.setText(whoseTurnString );

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

        JOptionPane.showMessageDialog(mainPanel, "You are " + humanPlayer.getName() + ", press Next Player to begin play");
        
	}
	
	private class dotsListener implements MouseListener {
		//when the mouse is clicked, 
		public void mouseClicked(MouseEvent event) {
			points.add(event.getPoint());
			repaint();
		}

		//these must all be implemented
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
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
//				if(humanPlayer.canChangeTurn()) {
				if(true) {
					advanceTurn();
				} else {
					JOptionPane.showMessageDialog(mainPanel,
							"You cannot change your turn yet. You must move first.");
				}
				break;
			case "Make Accusation" :
				System.out.println("Make Accusation is not yet implemented");
				break;
		}
	} 	
	
	public void advanceTurn() {
		board.setCurrentPlayerIndex((board.getCurrentPlayerIndex() + 1) % board.getPlayers().size());
		board.setUpMove();
		diceValue = Integer.toString(board.getPlayers().get(board.getCurrentPlayerIndex()).getDieRoll());
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
		
		whoseTurnString = board.getHumanPlayer().getName();

		ControlPanel cp = new ControlPanel();
		cp.setVisible(true);
		cp.advanceTurn();
	}

}
