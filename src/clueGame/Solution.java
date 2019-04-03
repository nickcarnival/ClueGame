/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

public class Solution {
	private Card weapon;
	private Card person;
	private Card room;

	public Solution(Card weapon, Card person, Card room) {
		this.weapon = weapon;
		this.person = person;
		this.room = room;
	}
	

	public Solution() {
		this.weapon = new Card();
		this.room = new Card();
		this.person = new Card();
	}

	public Card getWeapon() {
		return this.weapon;
	}

	public Card getPerson() {
		return this.person;
	}

	public Card getRoom() {
		return this.room;
	}

	@Override 
	public String toString() {
		return (person + " " + weapon + " " + room); 
		
	}
	
	
}
