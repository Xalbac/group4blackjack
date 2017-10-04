package group4blackjack;

import java.util.Random;

public class Deck{
	

		Card[][] Kortlekgenerator()
		{
			Card deckOfCards[][]=new Card[4][13];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 13; j++) {
					deckOfCards[i][j]=new Card(j,i);
					
					
				}
				
			}
			return deckOfCards;
		}
		
		Card RandomCard(Card[][]card)
		{
			Card randomCard;
			int tempRandomCard=0;
			int tempRandomSuit=0;
			Random random=new Random();
			tempRandomCard=random.nextInt(12);
			tempRandomSuit=random.nextInt(3);
			randomCard=card[tempRandomSuit][tempRandomCard];
			
			
			
			return randomCard;
		}
		
	
	
	
	
	

}

	
