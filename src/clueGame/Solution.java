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


	@Override 
	public String toString() {
		return (person + " " + weapon + " " + room); 
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solution other = (Solution) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
	}
	
	
}
