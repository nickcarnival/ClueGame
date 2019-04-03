/*
 * Jordan Newport
 * Nicholas Carnival
 */
package clueGame;
import java.awt.Color;  
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Set; 

public abstract class Player {
	private String playerName;
	private Color color;
	private ArrayList<Card> myCards;
	private ArrayList<Card> seenCards;
	private BoardCell location;
	private BoardCell lastVisited;

	public Player(String color, String name) {
		this.playerName = name;
		this.color = convertColor(color);
		myCards = new ArrayList<Card>();
		seenCards = new ArrayList<Card>();
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
	public abstract Card disproveSuggestion(Solution suggestion);

	public void addCard(Card card) {
		this.seenCards.add(card);
		this.myCards.add(card);
	}
	public void seeCard(Card card) {
		this.seenCards.add(card);
	}
	public void setLocation(BoardCell target) {
		this.location = target;
	}
	
	public BoardCell getLocation() {
		return this.location;
	}

	public String getName() {
		return playerName;
	}
	
	public Color getColor() {
		return color;
	}
	public String toString() {
		return ("Name: " + getName() + " Color: " + this.color);
	}

	public ArrayList<Card> getMyCards() {
		return myCards;
	}
	
	public ArrayList<Card> getSeenCards() {
		return seenCards;
	}
	
	public void movePlayer(BoardCell target) {
		this.location = target;
	}
	
}
