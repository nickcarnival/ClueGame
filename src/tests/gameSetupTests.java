package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;

/*
 * This JUnit test, tests the the people are loaded properly,
 * that the deck of cards is both loaded and created properly,
 * and that dealing the cards works
 *
*/


import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.HumanPlayer;

public class gameSetupTests {

	
	private static Board board;
	private HumanPlayer kernel = new HumanPlayer("yellow", "Kernel Mustard");
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

	//tests the size of card arrays
	@Test
	public void testCardArraySize() {
		ArrayList<Card> weaponList = board.getWeaponCards();
		assertEquals(6, weaponList.size());
		
		ArrayList<Card> peopleList = board.getPeopleCards();
		assertEquals(6, peopleList.size());

		ArrayList<Card> roomList = board.getRoomCards();
		assertEquals(9, roomList.size());
	}
	
	@Test
	public void testCardTypes() {
		
		ArrayList<Card> weaponList = board.getWeaponCards();
		assertEquals(CardType.WEAPON, weaponList.get(0).getType());
		
		ArrayList<Card> peopleList = board.getPeopleCards();
		assertEquals(CardType.PERSON, peopleList.get(0).getType());

		ArrayList<Card> roomList = board.getRoomCards();
		assertEquals(CardType.ROOM, roomList.get(0).getType());
	}
	
	@Test
	public void testCardNames() {
		ArrayList<Card> weaponList = board.getWeaponCards();
		assertEquals("Candlestick", weaponList.get(0).getName());
		
		ArrayList<Card> peopleList = board.getPeopleCards();
		assertEquals(CardType.PERSON, peopleList.get(0).getName());

		ArrayList<Card> roomList = board.getRoomCards();
		assertEquals(CardType.ROOM, roomList.get(0).getName());
	}
//    Load/create the deck of cards
//    Dealing the cards
	
	
}