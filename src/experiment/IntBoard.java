package experiment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 4;

	// constructs a default board
	public IntBoard() {
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		grid = new BoardCell[NUM_ROWS][NUM_COLS];
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				grid[i][j] = new BoardCell(i, j);
				adjMtx.put(grid[i][j], new HashSet<BoardCell>());
			}
		}
		calcAdjacencies();
	}

	// this calculates what is adjacent to each cell
	// should be called before calcTargets
	public void calcAdjacencies() {

		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				if (i > 0) {
					adjMtx.get(grid[i][j]).add(grid[i-1][j]);
				}
				if (j > 0) {
					adjMtx.get(grid[i][j]).add(grid[i][j-1]);
				}
				if (i < NUM_ROWS - 1) {
					adjMtx.get(grid[i][j]).add(grid[i+1][j]);
				}
				if (j < NUM_ROWS - 1) {
					adjMtx.get(grid[i][j]).add(grid[i][j+1]);
				}
			}
		}

	}

	// returns the adjacent cells
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMtx.get(cell);
	}

	// calculates what cells are what distance away
	private void findAllTargets(BoardCell thisCell, int numSteps) {
		for (BoardCell adjCell : getAdjList(thisCell)) {
			if (visited.contains(adjCell)) {
				break;
			} else {
				visited.add(adjCell);
				if (numSteps == 1) {
					targets.add(adjCell);
				} else {
					findAllTargets(adjCell, numSteps - 1);
				}
				visited.remove(adjCell);
			}
		}
	}

	public void calcTargets(BoardCell startCell, int pathLength) {
		targets.clear();
		visited.clear();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}

	//
	public Set<BoardCell> getTargets() {
		return targets;
	}

	// returns this boardcell
	public BoardCell getCell(int row, int column) {
		return grid[row][column];
	}
}
