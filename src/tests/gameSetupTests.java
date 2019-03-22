package tests;

import static org.junit.Assert.assertEquals;

/*
 * This JUnit test, tests the the people are loaded properly,
 * that the deck of cards is both loaded and created properly,
 * and that dealing the cards works
 *
*/


import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.HumanPlayer;

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
	
	/*************************************************************
	 *  Testing the Player's Existence
	 *************************************************************/

	//Tests that the player has the correct name
	@Test
	public void playerName() {
		//The first player name is Kernel Mustard
		HumanPlayer player = new HumanPlayer();
		String playerName = HumanPlayer.getName();
		assertEquals("Kernel Mustard", playerName);
		
	}
	
	//Tests that the player is in the correct location
	@Test
	public void humanExistence() {
		
	}
	
	//Tests that the NPC at location () exists
	@Test
	public void testNPC1() {
		
	}
	
	
	//Tests that the NPC at location () exists
	@Test
	public void testNPC2() {
		
	}
	
	
	//Tests that the NPC at location () exists
	@Test
	public void testNPC3() {
		
	}

	/*************************************************************
	 * Testing on the Deck of Cards 
	 *************************************************************/
	
	
//    Load/create the deck of cards
//    Dealing the cards
	
	
}