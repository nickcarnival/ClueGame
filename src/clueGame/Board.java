package clueGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.Math.toIntExact;

public class Board {


	private int MAX_BOARD_SIZE = 50;
	private String LayoutFile;
	private String LegendFile;
	private String COMMA = ",";

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	public void setConfigFiles(String layout, String legend) {
		// TODO Auto-generated method stub
		//test if the layout file is the correct length
		this.LayoutFile = layout;
		this.LegendFile = legend;
	}

	//2D array for storing boardcells
	private char[][] gridArray; 

	public void initialize() throws BadConfigFormatException   {
		gridArray = new char[getNumRows()][getNumColumns()];

		loadRoomConfig();
		loadBoardConfig();

	}
	
	//this method will parse both config files 
	//and get the row and column lengths
	private int NumRows = 0;
	private int NumColumns = 0;

	private HashMap<Character, String> legendMap = new HashMap<Character, String>();
	
	//THE ALMIGHTY 2D ARRAY OF BOARD CELLS!!!!!!!!!!!!!!!!!!!
	private BoardCell[][] boardCellArray;
	//THE END OF THE ALMIGHTY 2D ARRAY OF BOARD CELLS :(

	//this needs to create the legend
	public void loadRoomConfig() throws BadConfigFormatException{
		boardCellArray = new BoardCell[getNumRows()][getNumColumns()];
		
		//Get scanner instance
        Scanner scanner = null;
		try {
			scanner = new Scanner(new File(LegendFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         
        //Set the delimiter used in file
        scanner.useDelimiter(",");

        //finds the length of the file
        int count = 0;
        while (scanner.hasNextLine()) {
            count++;
            scanner.nextLine();
        }

        String[] valueArray = new String[count];
        char legendLetter ;
        String legendRoom = "";
        String legendCardStuff = "";
        String temp2 = "";
        //this is size three because of how the legend must be formatted
        String[] splitArray = new String[3];

        try {
			scanner = new Scanner(new File(LegendFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for(int i = 0; i < count; i ++) {
        	valueArray[i] = scanner.nextLine();
        	splitArray = valueArray[i].split(COMMA);

        	legendLetter = splitArray[0].charAt(0);
        	legendRoom = splitArray[1];
        	legendCardStuff = splitArray[2];
        	
        	legendRoom = legendRoom.trim();
        	legendCardStuff = legendCardStuff.trim();

        	legendMap.put(legendLetter, legendRoom);
        	
        }
         
        //Do not forget to close the scanner 
        scanner.close();	
	}

	//has to initialize the map layout stufffff
	public void loadBoardConfig() {
		Scanner scanner = null;

			try {
				scanner = new Scanner(new File(LayoutFile));

				//gridLine should store every line in the csv file
				String[] gridLine = new String[getNumColumns()];
				
				//stores the same thing without commas
				String[] cleanedGridLine = new String[getNumRows()];
				
				for(int row = 0; row < getNumRows(); row++) {
					gridLine[row] = scanner.nextLine();
					cleanedGridLine = gridLine[row].split(COMMA);

					for(int column = 0; column < getNumColumns(); column++) {
						//create a new board cell at a certain location with its char
						//tests if it is a door
						boardCellArray[row][column] = new BoardCell(row,column);

						if(cleanedGridLine[column].length() == 1 || cleanedGridLine[column].charAt(1) == 'N'){
							boardCellArray[row][column].initial = cleanedGridLine[column].charAt(0);
						} else {

							char doorDirectionLetter = cleanedGridLine[column].charAt(1);
							boardCellArray[row][column].isDoorway = true;

							if(doorDirectionLetter == 'L') {
								boardCellArray[row][column].doorDirection = DoorDirection.LEFT;
								boardCellArray[row][column].initial = cleanedGridLine[column].charAt(0);
							}

							if(doorDirectionLetter == 'R') {
								boardCellArray[row][column].doorDirection = DoorDirection.RIGHT;
								boardCellArray[row][column].initial = cleanedGridLine[column].charAt(0);
							}

							if(doorDirectionLetter == 'U') {
								boardCellArray[row][column].doorDirection = DoorDirection.UP;
								boardCellArray[row][column].initial = cleanedGridLine[column].charAt(0);
							}

							if(doorDirectionLetter == 'D') {
								boardCellArray[row][column].doorDirection = DoorDirection.DOWN;
								boardCellArray[row][column].initial = cleanedGridLine[column].charAt(0);
							}
						}
					}
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//log file not found
				e.printStackTrace();
			}
		
		
	}

	public Map<Character, String> getLegend() {
		// TODO Auto-generated method stub
		return legendMap;
	}


	//should return 21
	public int getNumRows() {

		Scanner scanner = null;
		int count = 0;
		try {
			scanner = new Scanner(new File(LayoutFile));

			while(scanner.hasNext()) {
				scanner.useDelimiter(",");
				count++;
				scanner.nextLine();
					
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
		}
		NumRows = count;
		//System.out.println("Num Rows: " + NumRows);
		return NumRows;
	}

	//should return 20
	public int getNumColumns() {
        //counts file length s
		//counts the number of lines in the text file
		Path path = Paths.get(LayoutFile);
		try {
			long lineCount = Files.lines(path).count();
			NumColumns = toIntExact(lineCount) + 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}		
		//end of file length counter

		return NumColumns;
	}

	public BoardCell getCellAt(int row, int column) {
		// TODO Auto-generated method stub
		return boardCellArray[row][column];
	}

	public static void main(String[] args) throws BadConfigFormatException {
		
		Board board = new Board();
		board.getInstance();
		board.setConfigFiles("data/CTest_ClueLayout.csv",  "data/CTest_ClueLegend.txt");
		board.initialize();
		board.getLegend();
		board.getCellAt(0, 0).getInitial();
		
		int numDoor = 0;
		for(int i = 0; i < board.getNumRows(); i++) {
			for(int j = 0; j < board.getNumColumns(); j++) {
				BoardCell cell = board.getCellAt(i, j);
				if(cell.isDoorway()) {
					numDoor++;
				}
				
			}
			System.out.println(numDoor);
		}
		System.out.println("R: " + board.getCellAt(4, 8).getInitial());
	
	}

}
