package group4blackjack;

import group4blackjack.Deck.Ranks;
import group4blackjack.Deck.Suits;

public class Card
{
	private Suits suit;		// Initialise suits.
	private Ranks value;	// Initialise ranks, or value. Why is it called ranks?

	// Constructor for the values. 
	public Card(Suits suit, Ranks value)
	{
		this.suit = suit;
		this.value = value;
	}

	// Output as string. 
	public String toString()
	{
		return "Card rank= " + value + " of, suit= " + suit;
	}
	
	// Getters and setters. 
	// Returns suit.
	public Suits getSuit()
	{
		return this.suit;
	}

	// Returns value. 
	public Ranks getValue()
	{
		return this.value;
	}
}