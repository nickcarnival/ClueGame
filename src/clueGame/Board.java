package clueGame;

import java.util.Map;

public class Board {


	private String LayoutFile;
	private String LegendFile;

	public void setConfigFiles(String string, String string2) {
		// TODO Auto-generated method stub
		//test if the layout file is the correct length
		this.LayoutFile = string;
		this.LegendFile = string2;
		
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public static Board getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Character, String> getLegend() {
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

}
