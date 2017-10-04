package group4blackjack;

import java.util.Random;

public class Deck{
	
	int deckGenerator()
	{
		
	}
	
	int deckRandomPicker()
	{
		int min = 2;
	    int max = 11;
	    int range = (max - min);

	    int random = new Random().nextInt(range + 1) + min;

	    return random;
	}
	
	
	
	
	

}

	
