package group4blackjack;

import group4blackjack.Deck.Ranks;
import group4blackjack.Deck.Suits;

public class Card {

	
	private Suits suit;
	private Ranks rank;


	
	public Card(Suits suit, Ranks rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}


	public String toString()
	{
		return "Card rank= " + rank + " of, suit= " + suit;
	}


	public Suits getSuit()
	{
		return suit;
	}

	public Ranks getRank()
	{
		return rank;
	}

	public Suits getValue()
	{
		return this.suit;
	}
	
}
