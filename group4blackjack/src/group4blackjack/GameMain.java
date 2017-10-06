package group4blackjack;

import java.util.Scanner;

public class GameMain
{
	// Initialise everything we need.
	private static int balance;								// User's money.
	private static int bet;									// User's bet.
	private boolean playerTurnEnd = false;						// Set players end turn to false, so player can play.
	private static Scanner scanner = new Scanner(System.in);	// Start scanner. 
	private static boolean GameOver = false;	// Set gameOver to false. 
	
	// Initialise other classes. 
	private static Player player = new Player();	// Define the player. 
	private static Player dealer = new Player();	// Define the dealer.
	//private Deck deck = new Deck();			// Define the deck.
	//private Card card = new Card();
	
	// Where the magic happens. 
	public static void main(String[] args)
	{
		balance = 100;				// Set user's balance. 

		
		// Welcome message. 
		System.out.println("Welcome to group 4 blackjack!\nYou have 100 credits to start with.\n");
		
		// If the player monies are enough AND the game is not over, play.
		while (balance > 0 && !GameOver)
		{
			// Ask the player if they are absolutely sure, he/she (we're not transphobic) wants to play this steaming pile of rubber tires. 
			System.out.println("Do you want to play? [P] Or quit? [Q]\n");
			String gameStart = scanner.next();	// Check what he/she wants to play. 
			
			// If the LGBTQ player wants to play this game, check for answers they typed. 
			if (gameStart.compareToIgnoreCase("P") == 0)
			{
				// If it's YES BOSS, then the game starts.
				try
				{
					System.out.println("Game starts\n");
					gameStart();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			else if(gameStart.compareToIgnoreCase("Q") == 0)
			{
				// Over the game
				gameOver();
			}
			else
			{
				// Tell the player they need to go back to elementary school and re-learn numbers. 
				System.out.println("Please use either P or Q");
			}
		}
		
		// IF the player has no monies, tell them they are poor and should get a job. 
		System.out.println("You have no monies to play :( ");
		System.exit(1);	// Run Forrest! RUUUN!
	}
	 
	
	private static void gameStart()
	{
		//Also initialise new deck.
		
		//player's turn first. 
		
		Deck deck = new Deck();
		deck.CardsDeck();
		
		System.out.println(deck.toString());
		//System.out.println(card1);
		
		System.out.println("What is your name?");
		String playerName = scanner.next();
		player.setName(playerName);
		
		System.out.println("How much you want to bet?\n");
		//bet = scanner.nextInt();
		
	}
	
	
	private void playerTurn()
	{
		String answer;
		System.out.println("Hit  [H] or stay [S]?");
		
		answer = scanner.next();
		
		if (answer.compareToIgnoreCase("H") == 0)
		{
			player.hit();
		}
		else
		{
			player.stay();
		}
	}
	
	private void playerHit()
	{
		
		
		//player.hit();
		/*
		 * 
		 * if (player.sum > 21)
		 * {
		 * 		die;
		 * }
		 *
		 */
	}
	
	private void playerStay()
	{
		//player.stay();
		System.out.println("Player stays. Dealer's turn");
		playerTurnEnd = true;
	}
	
	private void dealerTurn()
	{
		if (playerTurnEnd == true)
		{
			//LOL
		}

		
	}
	
	
	// What to do when the winner is announced.
	private void winner()
	{
		//This is justa test
		int sumPlayer = 10;	//This will get the card ref
		int sumDealer = 11;	//This will get the card ref
		
		if (sumPlayer>sumDealer && sumPlayer<=21 || sumDealer >21)
		{
			System.out.println("You win!");
			
			this.balance = this.balance + this.bet + this.bet;
		}
		else if (sumPlayer == sumDealer)
		{
			System.out.println("Draw. You lose.");
		}
		else
		{
			System.out.println("You lose!");
			
		}
	}
	
	private static void gameOver()
	{
		System.out.println("Game over!");
		System.exit(1);
		GameOver = true;
	}
}