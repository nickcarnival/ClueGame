package tests;

/*
 * This JUnit test, tests the the people are loaded properly,
 * that the deck of cards is both loaded and created properly,
 * and that dealing the cards works
 *
*/

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

public class gameSetupTests {

	
	private static Board board;
	// This runs before every test 
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/CTest_ClueLayout.csv", "data/CTest_ClueLegend.txt");		
		board.initialize();
		
	}
	
	//Tests that the people exist
	@Test
	public void testPeopleExistence() {
		
	}
    Load/create the deck of cards
    Dealing the cards
	
	
}