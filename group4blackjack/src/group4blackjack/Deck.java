package group4blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck
{
	private ArrayList<Card> cards;
	//private ArrayList<Card> PulledCards;

	// Enum table. 
	public enum Suits
	{
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}

	// Enum table.
	public enum Ranks
	{
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}
	
	// Create a deck, 
	public Deck()
	{
		// Create new deck.
		this.cards=new ArrayList<Card>();
	}
	
	// 52 cards.
	public void FillDeckWithCards()
	{
		// Loop through the suits. 
	 	for (Suits cardSuits : Suits.values())
		{
			// Loop through the ranks.
			for (Ranks cardRank : Ranks.values())
			{
				// Add new card.
				this.cards.add(new Card(cardSuits,cardRank));
				
			}
		}
	}
	
	// Shuffle the deck, obviously.
	public void shuffleDeck()
	{
		// New temporary arrayList for shuffled cards.
		ArrayList<Card> tempoDeck= new ArrayList<Card>();
		Random random =new Random();
		int indexrandom=0;
		int originalSizeofDeck=this.cards.size();
		for (int i = 0; i <originalSizeofDeck ; i++) 
		{
			indexrandom=random.nextInt((this.cards.size()-1-0)+1)+0;
			tempoDeck.add(this.cards.get(indexrandom));
			this.cards.remove(indexrandom);
		}
		this.cards=tempoDeck;
	}
	

	// Gets 1 card. 
	public Card cardGet(int i)
	{
		return this.cards.get(i);
	}
	
	// Removes a card from the deck.
	public void cardRemove(int i)
	{
		this.cards.remove(i);
	}
	
	
	public void cardAdd(Deck comingFrom)
	{
		this.cards.add(comingFrom.cardGet(0));
		comingFrom.cardRemove(0);
	}
}
