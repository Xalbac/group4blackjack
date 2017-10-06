package group4blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	//private ArrayList<Card> PulledCards;
	private Random Random;

	public enum Suits {
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}

	public enum Ranks {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}
	
	public HandMeADeck()
	{
		this.cards=new ArrayList<Card>();
	}
	
	
	


	
}
