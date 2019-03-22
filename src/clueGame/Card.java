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

	public String getName() {
		return cardName;
	}
}
