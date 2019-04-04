/*
 * Jordan Newport
 * Nicholas Carnival
 */
package clueGame;
import java.awt.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

//we use the same scanner method for every file, 
//we should probably make a method called "loadFile()"
public class Board {


	public static final int MAX_BOARD_SIZE = 50;
	private String layoutFile;
	private String legendFile;

	private static final String COMMA = ",";

	private int numRows = 0;
	private int numColumns = 0;
	
	private Map<BoardCell, Set<BoardCell>> adjacencyMatrix;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;

	private HashMap<Character, String> legendMap = new HashMap<Character, String>();
	
	private BoardCell[][] boardCellArray;

	ArrayList<Card> weaponCardArray;
	ArrayList<Card> roomCardArray;
	ArrayList<Card> peopleCardArray;
	
	private ArrayList<Card> cardArray; //TODO: could this be replaced by one of the below?

	ArrayList<Card> allCards; //cards remaining to be dealt/cards in the "deck"
	ArrayList<Card> dealtCards; //cards that have been dealt

	//array of all the players
	ArrayList<Player> allPlayers ;

	Solution solution = new Solution();

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	//sets the config file variables
	public void setConfigFiles(String layout, String legend ) {
		//test if the layout file is the correct length
		this.layoutFile = layout;
		this.legendFile = legend;
	}


	/*************************************************************
	 * Config Methods
	 *************************************************************/
	public void initialize() {

		try {
			setNumRows();
			setNumColumns();

			loadRoomConfig();
			loadBoardConfig();

			//loads the players from the 'playerConfig.txt' file
			loadPlayers();
			//loads the cards from the 'cards.csv' file
			loadCards();
			
			//sets the card solution
			setSolution();

			//deal the cards after loading the cards
			dealCards();

			//now that we have all our board cells, we can calculate each of their AdjacencyLists
			calcAdjacencies();
		} catch (BadConfigFormatException e) {
			System.out.println("Unable to initialize the board");
		} catch (FileNotFoundException e) {
			System.out.println("A specified file was not found");
		}

	}
	
	/*************************************************************
	 * Player Methods
	 *************************************************************/

	public void loadPlayers() throws BadConfigFormatException, FileNotFoundException{
		String playerConfigFile = "data/playerConfig.txt";
		Scanner scanner = new Scanner(new File(playerConfigFile));
		allPlayers = new ArrayList<Player>();
		
		scanner.useDelimiter(COMMA);

        while (scanner.hasNextLine()) {
        	String currentLine = scanner.nextLine();
        	String[] splitLine = currentLine.split(COMMA);
        	
        	String playerName = splitLine[0].trim();
        	String playerColor = splitLine[1].trim();

        	Player player = new ComputerPlayer(playerColor, playerName); 
        	allPlayers.add(player);
        }

		scanner.close();
	}

	//checks if the accusation is equal to the solution
	public boolean validateAccusation(Solution accusation) {
		return (accusation.getWeapon() == getSolution().getWeapon()
				&& accusation.getRoom() == getSolution().getRoom()
				&& accusation.getPerson() == getSolution().getPerson()
				);
	}

	/*************************************************************
	 * Card Methods
	 *************************************************************/

	/*
	deals cards to all players
	MUST BE CALLED AFTER SETSOLUTION
	generates all the numbers from 0 to 17 randomly without repeats (with a set),
	deals the card at each one to the next player in line, and keeps track
	of cards that have been dealt and cards that remain to be dealt (none)
	
	Also makes sure that each player knows what board it's on
	TODO: move that
	*/
	public void dealCards() {
		dealtCards= new ArrayList<Card>();
		HashSet<Integer> ints = new HashSet<Integer>();
		Random random = new Random();
		int currentPlayer = 0;
		for (int i = 0; i < allCards.size(); i++) {
			int myint = random.nextInt(allCards.size());
			while(ints.contains(myint)) {
				myint = random.nextInt(allCards.size());
			}
			allPlayers.get(currentPlayer).addCard(allCards.get(myint));
			ints.add(myint);
			dealtCards.add(allCards.get(myint));
			currentPlayer = (currentPlayer + 1) % allPlayers.size();
		}
		allCards = new ArrayList<Card>();
		for (Player p : allPlayers) {
			p.setBoard(this);
		}
	}

	//This creates a new solution for each game
	public void setSolution() {
		
		Card solPerson;
		Card solWeapon;
		Card solRoom;
		
		//generate the random solution
		Random random = new Random();
		int randomPerson = random.nextInt((peopleCardArray.size()) ) ;
		int randomWeapon = random.nextInt((weaponCardArray.size()) ) ;
		int randomRoom = random.nextInt((roomCardArray.size()) ) ;

		solWeapon = weaponCardArray.get(randomWeapon);
		solPerson = peopleCardArray.get(randomPerson);
		solRoom = roomCardArray.get(randomRoom);

		solution = new Solution(solWeapon, solPerson, solRoom);

		// add all cards that are not in the solution to the list of all cards that must be dealt
		allCards = new ArrayList<Card>();
		for (int i = 0; i < peopleCardArray.size(); i++) {
			if (i != randomPerson) {
				allCards.add(peopleCardArray.get(i));
			}
		}
		for (int i = 0; i < weaponCardArray.size(); i++) {
			if (i != randomWeapon) {
				allCards.add(weaponCardArray.get(i));
			}
		}
		for (int i = 0; i < roomCardArray.size(); i++) {
			if (i != randomRoom) {
				allCards.add(roomCardArray.get(i));
			}
		}
	}
	
	public Solution getSolution() {
		return solution;
	}

	public ArrayList<Card> getAllCards() {
		return cardArray;
	}

	public ArrayList<Card> getWeaponCards() {
		return weaponCardArray;
	}

	public ArrayList<Card> getPeopleCards() {
		return peopleCardArray;
	}

	public ArrayList<Card> getRoomCards() {
		return roomCardArray;
	}

	public void loadCards() throws BadConfigFormatException, FileNotFoundException{ 
		String cardConfigFile = "data/cards.csv";
		Scanner scanner = new Scanner(new File(cardConfigFile));

		String cardTypeString;
		CardType cardType;
		String cardName;
		
		int lineCount = 0;

		while(scanner.hasNextLine()) {
			//this is necessary
			String line = scanner.nextLine();
			lineCount ++;
		}

		//this stores every line of the file
		String[] arrayOfLines = new String[lineCount];

		//this will hold all of the cards and their types
		peopleCardArray = new ArrayList<Card>();
		weaponCardArray = new ArrayList<Card>();
		roomCardArray = new ArrayList<Card>();
		
		cardArray = new ArrayList<Card>();


		scanner = new Scanner(new File(cardConfigFile));
		while(scanner.hasNextLine()) {

			String currentLine = scanner.nextLine();
			String[] cleanedLine = currentLine.split(COMMA);

			cardName = cleanedLine[0].trim();
			cardTypeString = cleanedLine[1].trim();

			switch (cardTypeString) {
				case "weapon":
					cardType = CardType.WEAPON;
					Card weaponCard = new Card(cardName, cardType); 
					weaponCardArray.add(weaponCard);
					cardArray.add(weaponCard);
					break;
				case "room":
					cardType = CardType.ROOM;
					Card roomCard = new Card(cardName, cardType); 
					roomCardArray.add(roomCard);
					cardArray.add(roomCard);
					break;
				case "person":
					cardType = CardType.PERSON;
					Card personCard = new Card(cardName, cardType); 
					peopleCardArray.add(personCard);
					cardArray.add(personCard);
					break;
				default:
					break;
			}
		}
		scanner.close();

	}
	
	
	/*************************************************************
	 * Room Config Methods 
	 *************************************************************/
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {
		//these two functions write to NumRows and NumColumns variables
		setNumRows();
		setNumColumns();
		//-1 is an error state for NumColumns
		if(numColumns == -1) {
			System.out.println("The Bad Format Has Been Thrown");
			throw new BadConfigFormatException("Bad Columns");
		}
		boardCellArray = new BoardCell[numRows][numColumns];
		
		//Get scanner instance
		Scanner scanner = new Scanner(new File(legendFile));
         
        //Set the delimiter used in file
        scanner.useDelimiter(COMMA);

        //finds the length of the file
        int count = 0;
        while (scanner.hasNextLine()) {
            count++;
            scanner.nextLine();
        }

        String[] valueArray = new String[count];
        char legendLetter ;
        String legendRoom = "";
        String legendCardType = "";

        //this is size three because of how the legend must be formatted
        String[] splitArray = new String[3];

        //opens the legend file
		scanner = new Scanner(new File(legendFile));
        //iterates through each line of the legend file and adds it to the legendMap
        for(int i = 0; i < count; i ++) {
        	valueArray[i] = scanner.nextLine();
        	splitArray = valueArray[i].split(COMMA);

        	legendLetter = splitArray[0].charAt(0);
        	legendRoom = splitArray[1];
        	legendCardType = splitArray[2];
        	
        	legendRoom = legendRoom.trim();
        	legendCardType = legendCardType.trim();
        	
        	//if the legend has something that is neither a regular room nor a walkway/closet
        	if(!legendCardType.contentEquals("Card") && !legendCardType.contentEquals("Other")) {
        		scanner.close();
        		throw new BadConfigFormatException("The Cards are Not in your favor");
        	}

        	//places the legend values into a hash map
        	legendMap.put(legendLetter, legendRoom);
        	
        }
         
        scanner.close();	
	}
	

	//loads the map csv file and throws if badly formatted
	public void loadBoardConfig() throws BadConfigFormatException{
		Scanner scanner = null;
		setNumColumns();
		setNumRows();

		if(numColumns > MAX_BOARD_SIZE || numRows > MAX_BOARD_SIZE) {
			throw new BadConfigFormatException("Board size exceeds max board size of "
				+ MAX_BOARD_SIZE + " in at least one dimension");
		}

		//numColumns will be -1 if the columns are formatted improperly
		if(numColumns == -1) {
			System.out.println("threw bad column format");
			throw new BadConfigFormatException("Bad column format");

		//loads the csv if the columns are proper
		} else {
			try {
				scanner = new Scanner(new File(layoutFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			//gridLine should store every line in the csv file
			String[] gridLine = new String[numRows];

			//stores a line of the file without commas
			String[] cleanedGridLine = new String[numColumns];

			//goes through every column in the csv file and scans the entire line
			for(int row = 0; row < numRows; row++) {
				gridLine[row] = scanner.nextLine();
				cleanedGridLine = gridLine[row].split(COMMA);

				//scans each string and removes the commas
				for(int column = 0; column < numColumns; column++) {
					//create a new board cell at a certain location with its char
					boardCellArray[row][column] = new BoardCell(row,column);

					//checks that the initial string is not a door
					//i.e. if there is not more than one char or the second char is N
					if(cleanedGridLine[column].length() == 1 || cleanedGridLine[column].charAt(1) == 'N'){
						//load in initial character for board cell
						if(cleanedGridLine[column].charAt(0) == 'W') {
							boardCellArray[row][column].setWalkway(true);
						}
						boardCellArray[row][column].setInitial(cleanedGridLine[column].charAt(0));

						//checks that the character is actually in the map
						if(!legendMap.containsKey(boardCellArray[row][column].getInitial())) {
							throw new BadConfigFormatException("Error: This Character is not in the Legend: "
									+ boardCellArray[row][column].getInitial());
						}
						boardCellArray[row][column].setDoorway(false);
						//everything other than a walkway or closet is a room
						if(cleanedGridLine[column].equals("X") && cleanedGridLine[column].contentEquals("W")) {
							boardCellArray[row][column].setRoom(true);
						}
					//this runs if the initial string is a door
					} else {
						//load in initial character for board cell
						boardCellArray[row][column].setInitial(cleanedGridLine[column].charAt(0));

						//set up which direction the door is facing
						char doorDirectionLetter = cleanedGridLine[column].charAt(1);
						boardCellArray[row][column].setDoorway(true);

						switch(doorDirectionLetter) {
						case 'L':
							boardCellArray[row][column].setDoorDirection(DoorDirection.LEFT);
							break;
						case 'R':
							boardCellArray[row][column].setDoorDirection(DoorDirection.RIGHT);
							break;
						case 'U':
							boardCellArray[row][column].setDoorDirection(DoorDirection.UP);
							break;
						case 'D':
							boardCellArray[row][column].setDoorDirection(DoorDirection.DOWN);
							break;
						default:
							throw new BadConfigFormatException("There is a cell whose second letter is "
									+ doorDirectionLetter);
						}
					}
				}
			}		
		}
	}

	//this contains the legend e.g. 'K, Kitchen'
	public Map<Character, String> getLegend() {
		return legendMap;
	}


	/*************************************************************
	 * Set Board Dimension 
	 *************************************************************/

	//gets the number of columns in the specified csv file
	private int setNumColumns() {

		Scanner scanner = null;
		List<String> csvList = Arrays.asList();
		try {
			scanner = new Scanner(new File(layoutFile));

			ArrayList<Integer> countArray = new ArrayList<Integer>();
			//go through the layout file, taking note of how long each line is
			while(scanner.hasNextLine()) {
				
				String nextLine = scanner.nextLine();
				csvList = Arrays.asList(nextLine.split(COMMA));
				//each line, keep track of how many columns are in it
				countArray.add(csvList.size());
			}
			/*
			if there is ever a different number of columns in two lines,
			then we should know and give back -1 as a failure state

			this is not handled as an exception because
			this method is called in places where it may not throw
			*/
			for(int i = 1; i < countArray.size(); i++) {
				if(countArray.get(i) != countArray.get(i-1)) {
					numColumns = -1;
					return numColumns;
				}
			}
		} catch (FileNotFoundException e) {
			//not really anything better to do with this
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		numColumns = csvList.size();
		return numColumns;
	}
	
	public int getNumColumns() {
		return numColumns;
	}

	//counts the number of lines in the text file
	private int setNumRows() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(layoutFile));
			numRows = 0;
			String line = "";
			while(scanner.hasNextLine()) {
				numRows++;
				line = scanner.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return numRows;
	}

	public int getNumRows() {
		return numRows;
	}
	
	/*************************************************************
	 * Board Adjacency  
	 *************************************************************/

	//returns the board cell at (row, column)
	public BoardCell getCellAt(int row, int column) {
		return boardCellArray[row][column];
	}

	//this returns the set of cells which are directly next to a specific cell as referenced by its coordinates
	public Set<BoardCell> getAdjList(int x, int y) {

		BoardCell currentCell = new BoardCell(x, y);
		Set<BoardCell> adjList ;
		adjList = adjacencyMatrix.get(currentCell);
		return adjList;
	}

	//calculates the boards cell adjacencies
	public void calcAdjacencies() {
		//creates a hash map to store each cells set of adjacencies
		adjacencyMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		//for every row
		for (int row = 0; row < numRows; row++) {
			//for every column
			for (int column = 0; column < numColumns; column++) {
				//put the current cell into the key of the hash map
				adjacencyMatrix.put(boardCellArray[row][column], new HashSet<BoardCell>());
				//if this cell is a walkway we will check its adjacencies this way:
				//all adjacent walkways, or any doors that are facing the correct direction
				//it does check if a cell is in bounds first--to avoid null pointer
				if (boardCellArray[row][column].getInitial() == 'W') {
					//if the board cell is a walkway, setWalkway to true
					if (row > 0) {
						if (boardCellArray[row-1][column].isWalkway() ||
								boardCellArray[row-1][column].getDoorDirection() == DoorDirection.DOWN) {
							adjacencyMatrix.get(boardCellArray[row][column]).add(boardCellArray[row-1][column]);
						}
					}
					if (column > 0) {
						if (boardCellArray[row][column-1].isWalkway() ||
								boardCellArray[row][column-1].getDoorDirection() == DoorDirection.RIGHT) {
							adjacencyMatrix.get(boardCellArray[row][column]).add(boardCellArray[row][column-1]);
						}
					}
					if (row < numRows - 1) {
						if (boardCellArray[row+1][column].isWalkway() ||
								boardCellArray[row+1][column].getDoorDirection() == DoorDirection.UP) {
							adjacencyMatrix.get(boardCellArray[row][column]).add(boardCellArray[row+1][column]);
						}
					}
					if (column < numColumns - 1) {
						if (boardCellArray[row][column+1].isWalkway() ||
								boardCellArray[row][column+1].getDoorDirection() == DoorDirection.LEFT) {
							adjacencyMatrix.get(boardCellArray[row][column]).add(boardCellArray[row][column+1]);
						}
					}
				//otherwise if this cell is a doorway, then the only adjacency will be the walkway right outside
				} else if (boardCellArray[row][column].isDoorway()) {
					switch (boardCellArray[row][column].getDoorDirection()) {
					case LEFT:
						if (column > 0) {
							if(boardCellArray[row][column-1].isWalkway()) {
								adjacencyMatrix.get(boardCellArray[row][column]).add(boardCellArray[row][column-1]);
							}
						}
						break;
					case UP:
						if (row > 0) {
							if(boardCellArray[row-1][column].isWalkway()) {
								adjacencyMatrix.get(boardCellArray[row][column]).add(boardCellArray[row-1][column]);
							}
						}
						break;
					case RIGHT:
						if (column < numColumns - 1) {
							if(boardCellArray[row][column+1].isWalkway()) {
								adjacencyMatrix.get(boardCellArray[row][column]).add(boardCellArray[row][column+1]);
							}
						}
						break;
					case DOWN:
						if (row < numRows - 1) {
							if(boardCellArray[row+1][column].isWalkway()) {
								adjacencyMatrix.get(boardCellArray[row][column]).add(boardCellArray[row+1][column]);
							}
						}
						break;
					default:
						break;
					}
				} else if (boardCellArray[row][column].isRoom()) {
					//rooms have empty adjacencies
					continue;
				}
			}
		}
	}

	// calculates what cells are what distance away
	//this is based directly on the pseudocode in the presentation
	private void findAllTargets(BoardCell thisCell, int numSteps) {
		Set<BoardCell> adjList = adjacencyMatrix.get(thisCell);
		for (BoardCell adjCell : adjList) {
			//if has already visited this cell
			if (visited.contains(adjCell)) {
				continue;
			} else {
				visited.add(adjCell);
				//adjacencies can only be walkways or doors
				if(numSteps == 1 || adjCell.isDoorway() ) {
					targets.add(adjCell);
				}
				else {
					findAllTargets(adjCell, numSteps - 1);
				}
				visited.remove(adjCell);
			}
		}
	}

	//sets up call to recursive findAllTargets function
	public void calcTargets(int x, int y, int pathLength) {
		BoardCell startCell = boardCellArray[x][y];
		calcTargets(startCell, pathLength);
	}
	
	public void calcTargets(BoardCell b, int pathLength) {
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		visited.add(b);
		findAllTargets(b, pathLength);
	}


	public BoardCell[][] getBoardCellArray() {
		return boardCellArray;
	}
	//getter for targets list. MUST BE CALLED AFTER calcTargets, otherwise will be a null pointer
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public ArrayList<Player> getPlayers() {
		return allPlayers;
	}

	public ArrayList<Card> getDealtCards() {
		return dealtCards;
	}
	
//	public static void main(String[] args) {
//		Board board = new Board();
//		board.setConfigFiles("data/CTest_ClueLayout.csv", "data/CTest_ClueLegend.txt");		
//		board.initialize();
//	}

}
