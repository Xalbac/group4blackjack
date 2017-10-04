package group4blackjack;

/****
 * 
 * The best version
 * 
 */

public class Deck {
	

	void Deckgenerator()
	{
	       Deck cards[][]=new Deck[4][13];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				cards[i][j]=new Deck(i,j);
				
			}
		}
	
	}

}
