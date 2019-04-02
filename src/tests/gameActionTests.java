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

public class gameActionTests {
	
	private static Board board;
	
	@Before
	public void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/testsMap.csv", "data/rooms.txt");		
		board.initialize();
	}
	//this tests that the Computer Player chooses to enter a room given other options
	@Test
	public void testPickRoom() {
		BoardCell cell1 = board.getCellAt(5, 2);
		BoardCell cell2 = board.getCellAt(5, 4);
		BoardCell cell3 = board.getCellAt(2, 4);

		Set<BoardCell> targets = new HashSet<BoardCell>();  

		targets.add(cell1);
		targets.add(cell2);
		targets.add(cell3);

		System.out.println(cell1);
		System.out.println(cell2);
		System.out.println(cell3);
		System.out.println();

		ComputerPlayer NPC = new ComputerPlayer("red", "Jimothy Jenkins");
		NPC.pickLocation(targets);
		assertEquals(NPC.getLocation(), cell2);
	}
	@Test
	public void testPickRandom() {
		BoardCell cell1 = board.getCellAt(5, 2);
		BoardCell cell2 = board.getCellAt(8, 4);
		BoardCell cell3 = board.getCellAt(7, 4);

		Set<BoardCell> targets = new HashSet<BoardCell>();  

		targets.add(cell1);
		targets.add(cell2);
		targets.add(cell3);

		ComputerPlayer NPC = new ComputerPlayer("red", "Jimothy Jenkins");
		
		BoardCell firstPick = NPC.pickLocation(targets);
		BoardCell secondPick = NPC.pickLocation(targets);
		BoardCell thirdPick = NPC.pickLocation(targets);
		
		System.out.println(firstPick);
		System.out.println(secondPick);
		System.out.println(thirdPick);

		assertEquals(NPC.getLocation(), cell3);
	}
	/*
		if no rooms in list, select randomly
		if room in list that was not just visited, must select it
		if room just visited is in list, each target (including room) selected randomly
	 */
}





