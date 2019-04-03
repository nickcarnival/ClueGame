package tests;
/*
    Selecting a target location - ComputerPlayer
    Checking an accusation - Board
    Disproving a suggestion - Player
    Handling a suggestion - Board
    Creating a suggestion - ComputerPlayer

 */
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;
import clueGame.Player;
import clueGame.Solution;

public class gameActionTests {
	
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/testsMap.csv", "data/rooms.txt");		
		board.initialize();
	}
	//this tests that the Computer Player chooses to enter a room given other options
	@Test
	public void testPickRoom() {
		//cell1 and cell2 are walkways
		BoardCell cell1 = board.getCellAt(0, 0);
		BoardCell cell2 = board.getCellAt(20, 18);
		//cell3 is a door
		BoardCell cell3 = board.getCellAt(2, 4);

		Set<BoardCell> targets = new HashSet<BoardCell>();  

		targets.add(cell1);
		targets.add(cell2);
		targets.add(cell3);

		ComputerPlayer NPC = new ComputerPlayer("red", "Jimothy Jenkins");
		NPC.pickLocation(targets);
		assertEquals(NPC.getLocation(), cell3);
	}
	@Test
	public void testPickRandom() {
		BoardCell cell1 = board.getCellAt(5, 2);
		BoardCell cell2 = board.getCellAt(8, 4);
		BoardCell cell3 = board.getCellAt(7, 4);

		Set<BoardCell> targets = new HashSet<BoardCell>();  
		Set<BoardCell> randomCells = new HashSet<BoardCell>();

		targets.add(cell1);
		targets.add(cell2);
		targets.add(cell3);

		ComputerPlayer NPC = new ComputerPlayer("red", "Jimothy Jenkins");
		
		BoardCell temp;
		//Have the Computer pick a location 10000 times and then check that it picked each cell at least once
		for(int i = 0; i < 10000; i++) {
			NPC.pickLocation(targets);
			temp = NPC.getLocation();
			randomCells.add(temp);
		}

		//This checks that all three cells were chosen
		assertEquals(true,((randomCells.contains(cell1) && randomCells.contains(cell2) && randomCells.contains(cell3))));

	}
	
	@Test
	public void testLastVisited() {
		BoardCell cell1 = board.getCellAt(5, 2);
		BoardCell cell2 = board.getCellAt(8, 4);
		//these are both doors
		BoardCell cell3 = board.getCellAt(7, 4);

		ComputerPlayer NPC = new ComputerPlayer("red", "Jimothy Jenkins");
		
		Set<BoardCell> targets = new HashSet<BoardCell>();  
		Set<BoardCell> randomCells = new HashSet<BoardCell>();

		targets.add(cell1);
		targets.add(cell2);
		targets.add(cell3);
		
		NPC.setLastVisited(cell3);

		BoardCell temp;
		//Have the Computer pick a location 10000 times and then check that it picked each cell at least once
		for(int i = 0; i < 10000; i++) {
			NPC.pickLocation(targets);
			temp = NPC.getLocation();
			randomCells.add(temp);
		}
		NPC.pickLocation(targets);
		

		//This checks that all three cells were chosen
		assertEquals(true,((randomCells.contains(cell1) && randomCells.contains(cell2) && randomCells.contains(cell3))));
	}

	/*
	 * (15pts) Make an accusation. Tests include:

    solution that is correct
    solution with wrong person
    solution with wrong weapon
    solution with wrong room
	*/
	@Test
	public void testCorrectAccusation() {
		Solution testSolution = board.getSolution();
		
		ComputerPlayer NPC = new ComputerPlayer("red", "Jimothy Jenkins");

		assertEquals(NPC.makeAccusation(), testSolution);

	}
	/*
	(15pts) Create suggestion. Tests include:

    Room matches current location
    If only one weapon not seen, it's selected
    If only one person not seen, it's selected (can be same test as weapon)
    If multiple weapons not seen, one of them is randomly selected
    If multiple persons not seen, one of them is randomly selected

	(15pts) Disprove suggestion - ComputerPlayer. Tests include:

    If player has only one matching card it should be returned
    If players has >1 matching card, returned card should be chosen randomly
    If player has no matching cards, null is returned

	(15pts) Handle suggestion - Board. Tests include:

    Suggestion no one can disprove returns null
    Suggestion only accusing player can disprove returns null
    Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
    Suggestion only human can disprove, but human is accuser, returns null
    Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
    Suggestion that human and another player can disprove, other player is next in list, ensure other player returns answer

	 */
}





