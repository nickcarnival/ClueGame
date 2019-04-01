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
	
	@Override 
	public String toString() {
		return (person + " " + weapon + " " + room); 
	}
}
