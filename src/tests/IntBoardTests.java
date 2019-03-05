
package tests;


import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {

	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	public IntBoard board ;

	@Before
	public void beforeAll() {
		board = new IntBoard();		// constructor should call calcAdjacencies() so you can test them
		
	}
	
	//test matrix example to visualilze adjacencies
//	|0,0|0,1|0,2|0,3|
//	|1,0|1,1|1,2|1,3|
//	|2,0|2,1|2,2|2,3|
//	|3,0|3,1|3,2|3,3|

	@Test
	//tests the adjacency list is the correct size and has exactly the right elements
	//A sample test for creating targets
	//top left corner
	public void testAdjacency0_0() {
		BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = board.getAdjList(cell);

		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}

	//test 2
	//bottom right corner
	@Test
	public void testAdjacency3_3() {
		BoardCell cell = board.getCell(3, 3);
		Set<BoardCell> testList = board.getAdjList(cell);

		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		
		assertEquals(2, testList.size());
	}

	//test 3
	//right edge
	@Test
	public void testAdjacency1_3() {
		BoardCell cell = board.getCell(1, 3);
		Set<BoardCell> testList = board.getAdjList(cell);

		assertTrue(testList.contains(board.getCell(0, 3)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		
		assertEquals(3, testList.size());
	}
	
	//test 4
	@Test
	public void testAdjacency3_0() {
		BoardCell cell = board.getCell(3, 0);
		Set<BoardCell> testList = board.getAdjList(cell);

		assertTrue(testList.contains(board.getCell(3, 1)));
		assertTrue(testList.contains(board.getCell(2, 0)));
		
		assertEquals(2, testList.size());
	}

	
	//test 5
	@Test
	public void testAdjacency1_1() {
		BoardCell cell = board.getCell(1, 1);
		Set<BoardCell> testList = board.getAdjList(cell);

		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		
		assertEquals(4, testList.size());
	}
	
	
	//test 6
	@Test
	public void testAdjacency2_2() {
		BoardCell cell = board.getCell(2, 2);
		Set<BoardCell> testList = board.getAdjList(cell);

		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		
		assertEquals(4, testList.size());
	}



	// Testing Targets

	//test 7
	//tests what is 2 away from cell 0,0
	@Test
	public void testTargets0_0() {
		BoardCell cell = board.getCell(0, 0);

		board.calcTargets(cell, 2);

		Set targets = board.getTargets();

		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 0)));

	}

	//test 8
	//tests what is 2 away from cell 1,0
	@Test
	public void testTargets1_0() {
		BoardCell cell = board.getCell(1, 0);

		board.calcTargets(cell, 2);

		Set targets = board.getTargets();

		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(3, 0)));
	}
	
	
	//test 9
	//tests what is 4 away from cell 3,0
	@Test
	public void testTargets3_0() {
		BoardCell cell = board.getCell(3, 0);

		board.calcTargets(cell, 3);

		Set targets = board.getTargets();

		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
	
}
//end of my paste
