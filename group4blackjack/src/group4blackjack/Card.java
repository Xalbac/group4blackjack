package group4blackjack;

import group4blackjack.Deck.Ranks;
import group4blackjack.Deck.Suits;

public class Card {

	private Ranks rank;	// Initialise rank OR VALUE. WHy is it called rank?
	private Suits suit;	// Initialise suit. 

	// Generate card.
	public Card(Ranks rank, Suits suit)
	{
		this.rank = rank;
		this.suit = suit;
	}
	
	// So we can see what card it is.
	public String toString()
	{
		return "Card rank= " + rank + " of, suit= " + suit;
	}

	// Return the suit value.
	public Suits getSuit()
	{
		return this.suit;
	}

	// Return the rank value. 
	public Ranks getRank()
	{
		return this.rank;
	}
}
