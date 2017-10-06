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
		
		//either pull one random card and remove it from the pack....
		
	   /* if (Cards.isEmpty()) 
	        return null;*/
		
		/*Card randomcard = Cards.remove(randInt(0, Cards.size() - 1));  
	    if (randomcard != null)
	        PulledCards.add(randomcard);
	    return randomcard;*/
		
		//...or take one card and leave it in the pack
	    
	    if (Cards.isEmpty()) {
	    	
	    }
	   

	    Card res = Cards.get(randInt(0, Cards.size() - 1));
	    return res;

	   
	}
	
	public int randInt(int min, int max) {
	    int randomNum = Random.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public boolean isEmpty(){
	    return Cards.isEmpty();

	
	}


	
}
