package tests;
//Nicholas Carnival
//Jordan Newport

/*
 * This program tests that adjacencies and targets are calculated correctly.
 */


//Doing a static import allows me to write assertEquals rather than
//assertEquals
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTests {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;

	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/testsMap.csv", "data/rooms.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	//8 of these tests
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		Set<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(3, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(15, 8);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(14, 19);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(1, 18);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(9, 13);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> testList = board.getAdjList(11, 3);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(11, 4)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(18, 5);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(18, 4)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(5, 10);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 10)));
		//TEST DOORWAY UP
		testList = board.getAdjList(7, 19);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 19)));
		//TEST DOORWAY RIGHT, WHERE THERE'S A WALKWAY BELOW
		testList = board.getAdjList(5, 7);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 8)));
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(5, 8);
		assertTrue(testList.contains(board.getCellAt(6, 8)));
		assertTrue(testList.contains(board.getCellAt(4, 8)));
		assertTrue(testList.contains(board.getCellAt(5, 7)));
		assertEquals(3, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(6, 6);
		assertTrue(testList.contains(board.getCellAt(5, 6)));
		assertTrue(testList.contains(board.getCellAt(7, 6)));
		assertTrue(testList.contains(board.getCellAt(6, 5)));
		assertTrue(testList.contains(board.getCellAt(6, 7)));
		assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(7, 17);
		assertTrue(testList.contains(board.getCellAt(6, 17)));
		assertTrue(testList.contains(board.getCellAt(8, 17)));
		assertTrue(testList.contains(board.getCellAt(7, 16)));
		assertTrue(testList.contains(board.getCellAt(7, 18)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(14, 7);
		assertTrue(testList.contains(board.getCellAt(13, 7)));
		assertTrue(testList.contains(board.getCellAt(15, 7)));
		assertTrue(testList.contains(board.getCellAt(14, 6)));
		assertTrue(testList.contains(board.getCellAt(14, 8)));
		assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are LIGHT PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board, just one walkway piece
		Set<BoardCell> testList = board.getAdjList(0, 5);
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		assertEquals(1, testList.size());
		
		// Test on left edge of board, two walkway pieces
		testList = board.getAdjList(5, 0);
		assertTrue(testList.contains(board.getCellAt(4, 0)));
		assertTrue(testList.contains(board.getCellAt(5, 1)));
		assertEquals(2, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(20, 19);
		assertTrue(testList.contains(board.getCellAt(20, 20)));
		assertTrue(testList.contains(board.getCellAt(20, 18)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(17,11);
		assertTrue(testList.contains(board.getCellAt(16, 11)));
		assertTrue(testList.contains(board.getCellAt(18, 11)));
		assertTrue(testList.contains(board.getCellAt(17, 10)));
		assertTrue(testList.contains(board.getCellAt(17, 12)));
		assertEquals(4, testList.size());
		
		// Test on bottom edge of board, next to 1 room piece
		testList = board.getAdjList(20, 10);
		assertTrue(testList.contains(board.getCellAt(19, 10)));
		assertTrue(testList.contains(board.getCellAt(20, 11)));
		assertEquals(2, testList.size());
		
		// Test on right edge of board, next to 1 room piece
		testList = board.getAdjList(6, 21);
		assertTrue(testList.contains(board.getCellAt(5, 21)));
		assertTrue(testList.contains(board.getCellAt(6, 20)));
		assertEquals(2, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(17, 3);
		assertTrue(testList.contains(board.getCellAt(16, 3)));
		assertTrue(testList.contains(board.getCellAt(17, 4)));
		assertEquals(2, testList.size());
	}
	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(20, 4, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(20, 3)));
		assertTrue(targets.contains(board.getCellAt(19, 4)));	
		
		board.calcTargets(4, 4, 1);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 3)));
		assertTrue(targets.contains(board.getCellAt(4, 5)));	
		assertTrue(targets.contains(board.getCellAt(5, 4)));			
	}
	
	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(0, 16, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(2, 16)));
		assertTrue(targets.contains(board.getCellAt(1, 17)));
		assertTrue(targets.contains(board.getCellAt(0, 18)));
		
		board.calcTargets(14, 0, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 1)));
		assertTrue(targets.contains(board.getCellAt(14, 2)));	
	}
	
	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(10, 11, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 10)));
		assertTrue(targets.contains(board.getCellAt(9, 12)));
		assertTrue(targets.contains(board.getCellAt(6, 11)));
		assertTrue(targets.contains(board.getCellAt(14, 11)));
		assertTrue(targets.contains(board.getCellAt(8, 13)));
		assertTrue(targets.contains(board.getCellAt(7, 10)));
		assertTrue(targets.contains(board.getCellAt(7, 12)));
		assertTrue(targets.contains(board.getCellAt(13, 12)));
		
		// Includes a path that doesn't have enough length
		board.calcTargets(4, 1, 4);
		targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 4)));
		assertTrue(targets.contains(board.getCellAt(5, 2)));
		assertTrue(targets.contains(board.getCellAt(4, 3)));
		assertTrue(targets.contains(board.getCellAt(5, 4)));
	}	
	
	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(13, 17, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(19, 17)));
		assertTrue(targets.contains(board.getCellAt(7, 17)));	
		assertTrue(targets.contains(board.getCellAt(8, 16)));	
	}	
	
	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(18, 12, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
		// directly left (can't go right 2 steps)
		assertTrue(targets.contains(board.getCellAt(18, 10)));
		// directly up and down
		assertTrue(targets.contains(board.getCellAt(16, 12)));
		assertTrue(targets.contains(board.getCellAt(20, 12)));
		// one up/down, one left/right
		assertTrue(targets.contains(board.getCellAt(19, 11)));
		assertTrue(targets.contains(board.getCellAt(17, 11)));
		// the door
		assertTrue(targets.contains(board.getCellAt(17, 13)));
	}
	
	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(12, 4, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(12, targets.size());
		// directly up and down
		assertTrue(targets.contains(board.getCellAt(15, 4)));
		assertTrue(targets.contains(board.getCellAt(9, 4)));
		// to the right kinda
		assertTrue(targets.contains(board.getCellAt(13, 6)));
		// right then down
		assertTrue(targets.contains(board.getCellAt(14, 5)));
		assertTrue(targets.contains(board.getCellAt(13, 4)));
		// down then left/right
		assertTrue(targets.contains(board.getCellAt(13, 2)));
		assertTrue(targets.contains(board.getCellAt(14, 3)));
		// right then up
		assertTrue(targets.contains(board.getCellAt(10, 5)));
		// into the rooms
		assertTrue(targets.contains(board.getCellAt(11, 3)));
		assertTrue(targets.contains(board.getCellAt(10, 3)));		
		// close by
		assertTrue(targets.contains(board.getCellAt(11, 4)));		
		assertTrue(targets.contains(board.getCellAt(12, 5)));		
		
	}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(0, 18, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 17)));
		// Take two steps
		board.calcTargets(0, 18, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 17)));
		assertTrue(targets.contains(board.getCellAt(0, 16)));
	}

}
