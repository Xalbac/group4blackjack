package group4blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;

	// Enum table.
	public enum Suits {
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}

	// Enum table.
	public enum Ranks {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}

	// Create a deck,
	public Deck() {
		// Create new deck.
		this.cards = new ArrayList<Card>();
	}

	// 52 cards.
	public void FillDeckWithCards() {
		// Loop through the suits.
		for (Suits cardSuits : Suits.values()) {
			// Loop through the ranks.
			for (Ranks cardRank : Ranks.values()) {
				// Add new card.
				this.cards.add(new Card(cardSuits, cardRank));

			}
		}
	}

	// Shuffle the deck, obviously.
	public void shuffleDeck() {
		// New temporary arrayList for shuffled cards.
		ArrayList<Card> tempoDeck = new ArrayList<Card>();
		Random random = new Random();
		int indexrandom = 0;
		int originalSizeofDeck = this.cards.size();
		for (int i = 0; i < originalSizeofDeck; i++) {
			indexrandom = random.nextInt((this.cards.size() - 1 - 0) + 1) + 0;
			tempoDeck.add(this.cards.get(indexrandom));
			this.cards.remove(indexrandom);
		}
		this.cards = tempoDeck;
	}

	// Gets 1 card.
	public Card cardGet(int i) {
		return this.cards.get(i);
	}

	// Removes a card from the deck.
	public void cardRemove(int i) {
		this.cards.remove(i);
	}

	// Add cards to the deck.
	public void cardAdd(Card cardAdd) {
		this.cards.add(cardAdd);
	}

	// Draws a card from the deck.
	public void cardDraw(Deck comingFrom) {
		this.cards.add(comingFrom.cardGet(0));
		comingFrom.cardRemove(0);
	}

	// Moves created cards to deck.
	public void moveCardsToDeck(Deck moveTo) {
		int deckSize = this.cards.size(); // Initialise a new deck size.
		// Put the cards in the moveTo deck.
		for (int i = 0; i < deckSize; i++) {
			moveTo.cardAdd(this.cardGet(i)); // Move the card.
		}

		// Empty the deck.
		for (int i = 0; i < deckSize; i++) {
			this.cardRemove(0);
		}
	}

	// Calculate the deck size.
	public int deckSize() {
		return this.cards.size();
	}

	// Calculate value of the deck
	public int cardsValue() {
		int entireValue = 0;	// At
		int aces = 0;
		// Value of every card in the deck
		for (Card oneCard : this.cards) {
			// All values for cards
			switch (oneCard.getValue()) {
			case TWO:
				entireValue += 2;
				break;
			case THREE:
				entireValue += 3;
				break;
			case FOUR:
				entireValue += 4;
				break;
			case FIVE:
				entireValue += 5;
				break;
			case SIX:
				entireValue += 6;
				break;
			case SEVEN:
				entireValue += 7;
				break;
			case EIGHT:
				entireValue += 8;
				break;
			case NINE:
				entireValue += 9;
				break;
			case TEN:
				entireValue += 10;
				break;
			case JACK:
				entireValue += 10;
				break;
			case QUEEN:
				entireValue += 10;
				break;
			case KING:
				entireValue += 10;
				break;
			case ACE:
				aces += 1;
				break;
			}
		}

		// Determine entire value with aces.
		// Aces can be worth 1 or 11, if 11 would make the value above 21.
		// The if statement sets the value of aces to 1.
		for (int i = 0; i < aces; i++)
		{
			// If the value of cards exceeds 10, make the aces worth 1.
			if (entireValue > 10)
				entireValue += 1;

			else
				entireValue += 11;
		}
		// Returns the value of cards. 
		return entireValue;
	}

}
