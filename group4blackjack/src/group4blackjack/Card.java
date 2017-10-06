package group4blackjack;

import group4blackjack.Deck.Ranks;
import group4blackjack.Deck.Suits;

public class Card {

	private Ranks value;	// Initialise rank OR VALUE. Why is it called rank? Who came up with that idea?
	private Suits suit;	// Initialise suit. 

	// Generate card.
	public Card(Ranks value, Suits suit)
	{
		this.value = value;	// Set the Value of the card.
		this.suit = suit;	// Set the suit of the card.
	}
	
	// So we can see what card it is.
	public String toString()
	{
		return "Card rank= " + value + " of, suit= " + suit;
	}

	// Return the suit value.
	public Suits getSuit()
	{
		return this.suit;
	}

	// Return the rank value. 
	public Ranks getValue()
	{
		return this.value;
	}
}
