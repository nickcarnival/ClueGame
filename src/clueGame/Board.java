package clueGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

	public void initialize() {
		gridArray = new char[getNumRows()][getNumColumns()];

		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (BadConfigFormatException e) {
			
		}

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
		getNumRows();
		getNumColumns();
		if(NumColumns == -1) {
			System.out.println("The Bad Format Has Been Thrown");
			throw new BadConfigFormatException("Bad Columns");
		}
		boardCellArray = new BoardCell[NumRows][NumColumns];
		
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
	public void loadBoardConfig() throws BadConfigFormatException{
		Scanner scanner = null;
		getNumColumns();
		getNumRows();

		System.out.println(NumColumns);
		if(NumColumns != -1) {

		
				try {
					scanner = new Scanner(new File(LayoutFile));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//gridLine should store every line in the csv file
				//22 rows
				String[] gridLine = new String[NumRows];
				
				//23 columns
				//stores the same thing without commas
				String[] cleanedGridLine = new String[NumColumns];
				
				for(int column = 0; column < NumColumns -1 ; column++) {
					gridLine[column] = scanner.nextLine();
					cleanedGridLine = gridLine[column].split(COMMA);


					for(int row = 0; row < NumRows - 1; row++) {
						//create a new board cell at a certain location with its char
						//tests if it is a door
						boardCellArray[column][row] = new BoardCell(column,row);
						//check if the map has the key
						if(!legendMap.containsKey(boardCellArray[column][row].getInitial())) {
							throw new BadConfigFormatException("Error: This Character is not in the Legend: " 
						+ boardCellArray[column][row].getInitial());
									
						}

						if(cleanedGridLine[row].length() == 1 || cleanedGridLine[row].charAt(1) == 'N'){
							boardCellArray[column][row].initial = cleanedGridLine[row].charAt(0);
						} else {

							char doorDirectionLetter = cleanedGridLine[row].charAt(1);
							boardCellArray[column][row].isDoorway = true;

							if(doorDirectionLetter == 'L') {
								boardCellArray[column][row].doorDirection = DoorDirection.LEFT;
								boardCellArray[column][row].initial = cleanedGridLine[row].charAt(0);
							}

							if(doorDirectionLetter == 'R') {
								boardCellArray[column][row].doorDirection = DoorDirection.RIGHT;
								boardCellArray[column][row].initial = cleanedGridLine[row].charAt(0);
							}

							if(doorDirectionLetter == 'U') {
								boardCellArray[column][row].doorDirection = DoorDirection.UP;
								boardCellArray[column][row].initial = cleanedGridLine[row].charAt(0);
							}

							if(doorDirectionLetter == 'D') {
								boardCellArray[column][row].doorDirection = DoorDirection.DOWN;
								boardCellArray[column][row].initial = cleanedGridLine[row].charAt(0);
							}
						}
					}
					
				}		
			
			
		} else {
			throw new BadConfigFormatException();
		}
		
		
	}

	public Map<Character, String> getLegend() {
		// TODO Auto-generated method stub
		return legendMap;
	}


	//should return 21
	public int getNumColumns() {

		Scanner scanner = null;
		int count = 0;
		List<String> csvList = Arrays.asList();
		try {
			scanner = new Scanner(new File(LayoutFile));

			ArrayList<Integer> countArray = new ArrayList<Integer>();
			int index = 0;
			while(scanner.hasNextLine()) {
				
				String nextLine = scanner.nextLine();
				
				csvList = Arrays.asList(nextLine.split(COMMA));
				
				countArray.add(csvList.size());
				index++;
			}
			for(int i = 1; i < countArray.size(); i++) {

				System.out.println("how many times? ..." + i);
				if(countArray.get(i) != countArray.get(i-1)) {
					System.out.println("count arry or sometingsa dsfljas ");
					NumColumns = -1;
					return NumColumns;
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
		}
		NumColumns = csvList.size();
		//System.out.println("Num Rows: " + NumRows);
		return NumColumns;
	}

	//should return 20
	public int getNumRows() {
        //counts file length s
		//counts the number of lines in the text file

		long longArray = new Long(1);
		Path path = Paths.get(LayoutFile);
		try {
			longArray = Files.lines(path).count();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}		
		NumRows = toIntExact(longArray);
		//end of file length counter

		return NumRows;
	}

	public BoardCell getCellAt(int row, int column) {
		// TODO Auto-generated method stub
		return boardCellArray[row][column];
	}

	public static void main(String[] args) throws BadConfigFormatException {
		
		Board board = new Board();
		board.getInstance();
		board.setConfigFiles("data/CTest_ClueLayoutBadColumns.csv",  "data/CTest_ClueLegend.txt");
		board.initialize();
		board.getLegend();
		board.loadBoardConfig();
		board.loadRoomConfig();
	}

}
