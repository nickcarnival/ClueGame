/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

public class Solution {
	private String weapon;
	private String person;
	private String room;

	public Solution(String weapon, String person, String room) {
		this.weapon = weapon;
		this.person = person;
		this.room = room;
	}
	

	public Solution() {
		this.weapon = "solution has no values";
		this.room = "solution has no values";
		this.person = "solution has no values";
	}


	@Override 
	public String toString() {
		return (person + " " + weapon + " " + room); 
	}
}
