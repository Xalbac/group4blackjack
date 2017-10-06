package group4blackjack;

import group4blackjack.Deck.Ranks;
import group4blackjack.Deck.Suits;

public class Card {

	private Ranks rank;
	private Suits suit;

	public Card(Ranks rank, Suits suit) {
		super();
		this.rank = rank;
		this.suit = suit;
	}
	

	@Override
	public String toString() {
		return "Card rank=" + rank + ", suit=" + suit;
	}


	public Suits getSuit() {
		return suit;
	}

	public Ranks getRank() {
		return rank;
	}

	public Suits getValue() {
		return this.suit;
	}
	/*
	 * }
	 * 
	 * public Ranks getRank() { return rank; }
	 * 
	 * public void setRank(Ranks rank) { this.rank = rank; }
	 * 
	 * public Suits getSuit() { return suit; }
	 * 
	 * public void setSuit(Suits suit) { this.suit = suit; }
	 */
	
	//Some stuff we do not need \/

	/*@Override
	public boolean equals(Object o) {
		return (o != null && o instanceof Card && ((Card) o).rank == rank && ((Card) o).suit == suit);
	}*/
	
	
	

}
