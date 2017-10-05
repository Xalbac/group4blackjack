package group4blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> Cards;
	private ArrayList<Card> PulledCards;
	private Random Random;

	public enum Suits {
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}

	public enum Ranks {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}
	
	public void CardsDeck() {
	    Random = new Random();
	    PulledCards = new ArrayList<Card>();
	    Cards = new ArrayList<Card>(Suits.values().length * Ranks.values().length);
	    reset();
	}
	
	public void reset() {
	    PulledCards.clear();
	    Cards.clear();
	    /* Creating all possible cards... */
	    for (Suits s : Suits.values()) {
	        for (Ranks r : Ranks.values()) {
	            Card c = new Card(r, s);
	            Cards.add(c);
	        }
	    }
	}
	
	public Card pullRandom() {
	    if (Cards.isEmpty())
	        return null;

	    Card res = Cards.remove(randInt(0, Cards.size() - 1));
	    if (res != null)
	        PulledCards.add(res);
	    return res;
	}
	
	public int randInt(int min, int max) {
	    int randomNum = Random.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public boolean isEmpty(){
	    return Cards.isEmpty();

	
	}

	@Override
	public String toString() {
		return "Deck [Cards=" + Cards + ", PulledCards=" + PulledCards + ", Random=" + Random + "]";
	}
	
}
