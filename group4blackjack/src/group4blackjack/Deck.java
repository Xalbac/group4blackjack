package group4blackjack;

import java.util.Random;

public class Deck{
	

		Card[][] Kortlekgenerator()
		{
			Card kort[][]=new Card[4][13];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 13; j++) {
					kort[i][j]=new Card(j,i);
					
					
				}
				
			}
			return kort;
		}
	
	
	
	
	

}

	
