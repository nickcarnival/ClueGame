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

	private Board() {
		
	}
	
	public void setConfigFiles(String layout, String legend) {
		// TODO Auto-generated method stub
		//test if the layout file is the correct length
		this.LayoutFile = layout;
		this.LegendFile = legend;
	}

	//this method will parse both config files 
	//and get the row and column lengths
	private int NumRows = 0;
	private int NumColumns = 0;

	private HashMap<Character, String> legendMap = new HashMap<Character, String>();
	//this needs to create the legend
	public void initialize() throws BadConfigFormatException {

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
        	
        	System.out.println(legendLetter);
        	System.out.println(legendRoom);
        	System.out.println(legendCardStuff);
        }
         
        //Do not forget to close the scanner 
        scanner.close();	

	}

	public static Board getInstance() {
		// TODO Auto-generated method stub
		return new Board();
	}

	public Map<Character, String> getLegend() {
		// TODO Auto-generated method stub
		System.out.println("Children's Room: " + legendMap.get('C'));
		return legendMap;
	}


	//should return 21
	public int getNumRows() {

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(LayoutFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
		}

		int count = 0;
		while(scanner.hasNext()) {
			scanner.useDelimiter(",");
			count++;
			scanner.nextLine();
				
		}
		NumRows = count;
		//System.out.println("Num Rows: " + NumRows);
		return NumRows;
	}

	//shoudl return 20
	public int getNumColumns() {
		System.out.println(NumColumns);

        //counts file lenghts
		//counts the number of lines in the text file
		Path path = Paths.get(LayoutFile);
		try {
			long lineCount = Files.lines(path).count();
			NumColumns = toIntExact(lineCount) - 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}		
		//end of file length counter

		//System.out.println("num columns: " + NumColumns);
		return NumColumns;
	}

	public BoardCell getCellAt(int i, int j) {
		// TODO Auto-generated method stub
		return new BoardCell(i, j);
	}

	public static void main(String[] args) throws BadConfigFormatException {
		
		Board board = new Board();
		board.getInstance();
		board.setConfigFiles("data/map.csv",  "data/rooms.txt");
		board.initialize();
		board.getLegend();
		board.getNumColumns();
		board.getNumRows();
	
	}
	

}
