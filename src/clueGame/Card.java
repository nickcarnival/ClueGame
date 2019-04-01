package clueGame;

public class Card {
	private String cardName;
	private CardType type;
	public Card() {
		this.cardName = "";
		this.type = CardType.ROOM;
	}
	public Card(String cardName, CardType type) {
		super();
		this.cardName = cardName;
		this.type = type;
	}
	public boolean equals() {
		return false;
	}
	public CardType getType() {
		return type;
	}

	public boolean equals(Object o) {
		if (o instanceof Card) {
			Card c = (Card) o;
			if (this.cardName.equals(c.cardName)) return true;
		}
		return false;

	}

	public void setCard(String card) {
		this.cardName = card;
	}
	public String getName() {
		return cardName;
	}

	@Override
	public String toString() {
		return cardName;
	}
}
 
