package group4blackjack;

public class Card {

	private int value;
	private int suit;
	
	public Card(int value, int suit) {
		super();
		this.value = value;
		this.suit = suit;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getSuit() {
		return suit;
	}
	public void setSuit(int suit) {
		this.suit = suit;
	}
	@Override
	public String toString() {
		return "Card [value=" + value + ", suit=" + suit + "]";
	}
	
	
	
	
	

	

}
