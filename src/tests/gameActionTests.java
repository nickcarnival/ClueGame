package tests;
/*
    Selecting a target location - ComputerPlayer
    Checking an accusation - Board
    Disproving a suggestion - Player
    Handling a suggestion - Board
    Creating a suggestion - ComputerPlayer

 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;

import clueGame.Board;
import clueGame.ComputerPlayer;
import clueGame.Player;

public class gameActionTests {
	
	private static Board board;
	
	@Before
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/map.csv", "data/rooms.txt");		
		board.initialize();
	}
	public void testSelectTarget() {
		ComputerPlayer NPC = new ComputerPlayer("red", "Jimothy Jenkins");

		
	}
	/*
		if no rooms in list, select randomly
		if room in list that was not just visited, must select it
		if room just visited is in list, each target (including room) selected randomly
	 */
}





