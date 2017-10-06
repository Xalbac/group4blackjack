package group4blackjack;

import group4blackjack.Deck.Ranks;
import group4blackjack.Deck.Suits;

public class Card {

	private Ranks rank;
	private Suits suit;

	public Card(Ranks rank, Suits suit)
	{
		this.rank = rank;
		this.suit = suit;
	}
	
	public String toString()
	{
		return "Card rank= " + rank + " of, suit= " + suit;
	}


	public Suits getSuit()
	{
		return this.suit;
	}

	public Ranks getRank()
	{
		return this.rank;
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
