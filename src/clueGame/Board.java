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

public class Board {


	private int MAX_BOARD_SIZE = 50;
	private String LayoutFile;
	private String LegendFile;
	private String COMMA = "'";

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

	//we only want to parse the file once and should do that here:
	public void initialize() throws FileNotFoundException {

		//Get scanner instance
        Scanner scanner = new Scanner(new File(LegendFile));
         
        //Set the delimiter used in file
        scanner.useDelimiter(",");

        //finds the length of the file
        int count = 0;
        while (scanner.hasNextLine()) {
            count++;
            scanner.nextLine();
        }

        String[] valueArray = new String[count];
        scanner = new Scanner(new File(LegendFile));
        for(int i = 0; i < count; i ++) {
        	valueArray[i] = scanner.nextLine();
        	System.out.println(valueArray[i]);
        	System.out.println(i);
        }
         
        //Do not forget to close the scanner 
        scanner.close();	

		//counts the number of lines in the text file
		Path path = Paths.get(LegendFile);
		try {
			long lineCount = Files.lines(path).count();
			System.out.println("File length: " + lineCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

	public static Board getInstance() {
		// TODO Auto-generated method stub
		return new Board();
	}

	public Map<Character, String> getLegend() {
		// TODO Auto-generated method stub
		return new HashMap<Character, String>();
	}

	public int getNumRows() {
		return NumRows;
	}

	public int getNumColumns() {
		return NumColumns;
	}

	public BoardCell getCellAt(int i, int j) {
		// TODO Auto-generated method stub
		return new BoardCell(i, j);
	}
	
	public static void main(String[] args) {
		
		Board board = new Board();
		board.getInstance();
		board.setConfigFiles("data/map.csv",  "data/rooms.txt");
		try {
			board.initialize();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
