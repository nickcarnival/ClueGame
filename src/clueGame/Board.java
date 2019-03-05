package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Board {


	private String LayoutFile;
	private String LegendFile;

	private Board() {
		
	}
	
	public void setConfigFiles(String layout, String legend) {
		// TODO Auto-generated method stub
		//test if the layout file is the correct length
		this.LayoutFile = layout;
		this.LegendFile = legend;
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

	public BoardCell getCellAt(int i, int j) {
		// TODO Auto-generated method stub
		return new BoardCell(i, j);
	}

}
