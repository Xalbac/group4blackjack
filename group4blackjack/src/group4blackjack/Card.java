package group4blackjack;

public class Card {

	private int value;
	private int suit;
	
	public Card(int suit, int suit) {
		super();
		this.value = suit;
		this.suit = suit;
		switch (value) {
		case 0:
			value=1;
			break;
		case 1:
			value=2;
			break;
		case 2:
			value=3;
			break;
		case 3:
			suit="Fyra";
			break;
		case 4:
			suit="Fema";
			break;
		case 5:
			suit="Sexa";
			break;
		case 6:
			suit="Sjua";
			break;
		case 7:
			suit="Åtta";
			break;
		case 8:
			suit="Nia";
			break;
		case 9:
			suit="Tia";
			break;
		case 10:
			suit="Knekt";
			break;
		case 11:
			suit="Dam";
			break;
		case 12:
			suit="Kung";
			break;
			

		default:
			break;
		}
		
		switch (suit) {
		case 0:
			suit="Hjärter";
			break;
		case 1:
			suit="Ruter";
			break;
		case 2:
			suit="Klöver";
			break;
		case 3:
			suit="Spader";
			
			break;
		default:
			break;
		}
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
