package clueGame;
import java.awt.Color;  
import java.lang.reflect.Field;
import java.util.ArrayList; 

public abstract class Player {
	private int row;
	private int column;
	private String playerName;
	private Color color;
	private ArrayList<Card> myCards;
	private ArrayList<Card> seenCards;

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
	public abstract Card disproveSuggestion(Solution suggestion);

	public static String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
