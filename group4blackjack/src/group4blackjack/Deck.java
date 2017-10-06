package group4blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	//private ArrayList<Card> PulledCards;

	public enum Suits {
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}

	public enum Ranks {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}
	public Deck()
	{
		//create new deck
		this.cards=new ArrayList<Card>();
	}
	//52 cards
	public void FillDeckWithCards() {
		//loop through the suits
	for (Suits cardSuits : Suits.values()) {
		//loop through the ranks
		for (Ranks cardRank : Ranks.values()) {
			//add new card
			this.cards.add(new Card(cardSuits,cardRank));
			
		}
		
	}
	
	}
	//shuffle the deck, obviously
	public void shuffleDeck() {
	   //new temporary arrayList for shuffled cards
		ArrayList<Card> tempoDeck= new ArrayList<Card>();
		Random random =new Random();
		int indexrandom=0;
		int originalSizeofDeck=this.cards.size();
		for (int i = 0; i <originalSizeofDeck ; i++) {
			indexrandom=random.nextInt((this.cards.size()-1-0)+1)+0;
			tempoDeck.add(this.cards.get(indexrandom));
			this.cards.remove(indexrandom);
		}
		this.cards=tempoDeck;
	}
	
	


	
}
