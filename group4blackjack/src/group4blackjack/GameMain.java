package group4blackjack;


import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMain
{
	public static void main(String[] args)
	{
		// Initialise everything. 
		Deck deckPlay = new Deck();
		Deck cardsPlayer = new Deck();
		Deck cardsDealer = new Deck();
		Player player = new Player();
		Player dealer = new Player();
		
		// Initialise scanner. 
		Scanner ui = new Scanner(System.in);
		
		// Initialise the default values. 
		int money = 100;
		boolean playerTurn = true;
		boolean dealerTurn = false;
		int bet = 0;
		
		// Initialise and shuffle deck. 
		deckPlay.FillDeckWithCards();
		deckPlay.shuffleDeck();
		
		// Display the welcome message and ask the player for the name.
		System.out.println("Welcome to the best blackjack ever!\n");
		System.out.println("What is your name?");
		String answer = ui.next();
		player.setName(answer);
		
		// Print out his name and his starting money.
		System.out.println("Welcome " + player.getName());
		
		// As long as the player has money, allow them to play.
		while (money > 0)
		{
			// Ask the player about the bet. 
			System.out.println("How much would you like to bet?");
			System.out.println("You have: " + money);
			int betAmount = ui.nextInt();
			
			while (ui.hasNextInt(betAmount))
			{
				// TODO: handle exception
				System.out.println("please enter a valid bet number");
				ui.nextInt(betAmount);
			}
		
			// Check if the bet is more or less than the money they currently have. 
			if (bet > money)
			{
				System.out.println("You can't bet more than you have.");
				break;
			}
			// If the bet is ok, proceed with the game. 
			else
			{
				// Start the game. 
				System.out.println("Dealing cards.");
				
				// Give cards to the player. 
				cardsPlayer.cardDraw(deckPlay);
				cardsPlayer.cardDraw(deckPlay);
				
				// Give cards to the dealer. 
				cardsDealer.cardDraw(deckPlay);
				cardsDealer.cardDraw(deckPlay);
			}
				
				// Displayer the cards.
				System.out.println("Your cards: " + cardsPlayer.cardGet(0).toString() + " and " + cardsPlayer.cardGet(1).toString());
				System.out.println("Your cards are valued at: " + cardsPlayer.cardsValue());
				System.out.println("Dealer cards: " + cardsDealer.cardGet(0).toString() + " and 1 hidden.");
				
				// As long as it's the player's turn...
				while (playerTurn = true)
				{
					// Ask the player if they want to hit or stand.
					System.out.println("Would you like to [H] hit or [S] stand?");
					String answer2 = ui.next();
					
					// If the answer is hit, do the following. 
					if (answer2.compareToIgnoreCase("h") == 0)
					{
						cardsPlayer.cardDraw(deckPlay);
						System.out.println("You draw: " + cardsPlayer.cardGet(cardsPlayer.deckSize()-1).toString());
						System.out.println("Your total is: " + cardsPlayer.cardsValue());
						
						// If the player draws a card and the value exceed 21.
						if (cardsPlayer.cardsValue() > 21)
						{
							System.out.println("Busted! Dealer wins!");
							money -= bet;
							playerTurn = false;
							dealerTurn = false;
							break;
						}
						
					// If the player stands end the player's turn. 
					}
					else if (answer2.compareToIgnoreCase("s") == 0)
					{
						System.out.println("Player stands.");
						playerTurn = false;
						dealerTurn = true;
						break;
					}
					// Else tell them to use H or S. 
					else
					{
						System.out.println("Please use either [H] or [S]");
					}
				}
				
				// 
				if ((cardsDealer.cardsValue() > cardsPlayer.cardsValue()) && playerTurn == false && dealerTurn == true)
				{
					System.out.println("Dealer wins!" + cardsDealer.cardsValue() + " against yours: " + cardsPlayer.cardsValue());
					money -= bet;
				}
				
				while ((cardsDealer.cardsValue() < 17) && playerTurn == false && dealerTurn == true)
				{
					cardsDealer.cardDraw(deckPlay);
					System.out.println("Dealer draws: " + cardsDealer.cardGet(cardsDealer.deckSize()-1).toString());
				}
				
				if ((cardsDealer.cardsValue()>21) && playerTurn == false && dealerTurn == true)
				{
					System.out.println("Dealer bust at " + cardsDealer.cardsValue() + "! You win!");
					money += bet;
				}
				
				if ((cardsDealer.cardsValue() == cardsPlayer.cardsValue() && playerTurn == false && dealerTurn == true))
				{
					System.out.println("Draw! Dealer wins!");
					money -= bet;
				}
				
				if ((cardsPlayer.cardsValue() > cardsDealer.cardsValue()) && playerTurn == false && dealerTurn == true)
				{
					System.out.println("You win!" + "Your total: " + cardsPlayer.cardsValue());
					money += bet;
				}
				
				
				cardsPlayer.moveCardsToDeck(deckPlay);
				cardsDealer.moveCardsToDeck(deckPlay);
				System.out.println("End of hand");
			}
		System.out.println("Game over!");
		
		ui.close();		
		}
}