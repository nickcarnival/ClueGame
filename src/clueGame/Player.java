package clueGame;
import java.awt.Color;  
import java.lang.reflect.Field;
import java.util.ArrayList; 

public class Player {
	private int row;
	private int column;
	private String playerName;
	private Color color;
	private ArrayList<Card> myCards;
	private ArrayList<Card> seenCards;
	
	public Player(String color, String name) {
		this.playerName = name;
		this.color = convertColor(color);
	}

	// Be sure to trim the color, we don't want spaces around the name 
	public Color convertColor(String strColor) { 
	    Color color;  
	    try {      
	        // We can use reflection to convert the string to a color 
	        Field field = Class.forName("java.awt.Color").getField(strColor.trim()); 
	        color = (Color)field.get(null);  
	  } catch (Exception e) {   
	        color = null; // Not defined   
	  } 
	  return color; 
	} 
	public Card disproveSuggestion(Solution suggestion) {
		return new Card();
	}

	public void setLocation(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return playerName;
	}
	
	public Color getColor() {
		return color;
	}
}
