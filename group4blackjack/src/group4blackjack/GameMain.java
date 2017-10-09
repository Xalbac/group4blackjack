package group4blackjack;


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
		System.out.println("Welcome " + player.getName() + "\nYou have: " + money);
		
		// As long as the player has money, allow them to play.
		while (money > 0)
		{
			// Ask the player about the bet. 
			System.out.println("How much would you like to bet?");
			bet = ui.nextInt();
			
			// Check if the bet is more or less than the money they currently have. 
			if (bet > money)
			{
				System.out.println("You can't bet more than you have.");
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
				
				// Displayer the cards.
				System.out.println("Your cards: " + cardsPlayer.cardGet(0).toString() + " " + cardsPlayer.cardGet(1).toString());
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
						
						// If the player draws a card and the value exceed 21.
						if (cardsPlayer.cardsValue() > 21)
						{
							System.out.println("Busted!");
							money -= bet;
							playerTurn = false;
							break;
						}
					// If the player stands end the player's turn. 
					}
					else if (answer2.compareToIgnoreCase("s") == 0)
					{
						System.out.println("Player stands.");
						playerTurn = false;
						break;
					}
					// Else tell htem to use H or S. 
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
				
				while ((cardsDealer.cardsValue() > 17) && playerTurn == false & dealerTurn == true)
				{
					cardsDealer.cardDraw(deckPlay);
					System.out.println("Dealer draws: " + cardsDealer.cardGet(cardsDealer.deckSize()-1).toString());
				}
			}
		}
	}
	
	/*
	// Initialise everything we need.
	private static int money;									// User's money.
	private static int bet;										// User's bet.
	private boolean playerTurnEnd = false;						// Set players end turn to false, so player can play.
	private static Scanner scanner = new Scanner(System.in);	// Start scanner. 
	private static boolean GameOver = false;					// Set gameOver to false. 
	private static boolean GameStarted = false;
	

	// Initialise other classes. 
	private static Player player = new Player();	// Define the player. 
	private static Player dealer = new Player();	// Define the dealer.

	// Where the magic happens. 
	public static void main(String[] args)
	{
		money = 100;	// Set user's balance. 

		
		
		// Welcome message. 
		System.out.println("Welcome to group 4 blackjack!\nYou have 100 credits to start with.\n");
		
		// If the player monies are enough AND the game is not over, play.
		while (money > 0 && !GameOver && !GameStarted)
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
					GameStarted = true;
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
	 
	// This is where the game plays. 
	private static void gameStart()
	{
		System.out.println("Your name? ");
		String answer = scanner.next();
		player.setName(answer);
		System.out.println(player.getName());
		System.out.println("Welcome " + player.getName() + ". How much would you liek to bet?");
		bet = scanner.nextInt();
		if (bet > money)
		{
			System.out.println("You cannot bet more than what you have.");
		}
		else
		{
			money = money - bet;
			Deck deckPlay = new Deck();
			Deck cardsPlayer = new Deck();	// Player's hand
			Deck cardsDealer = new Deck();	// Delaer's hand
			deckPlay.FillDeckWithCards();
			deckPlay.shuffleDeck();
			
			System.out.println("Dealing cards...");
			cardsPlayer.cardDraw(deckPlay);
			cardsPlayer.cardDraw(deckPlay);
			
			cardsDealer.cardDraw(deckPlay);
			cardsDealer.cardDraw(deckPlay);
			
			System.out.println("Your cards: "+cardsPlayer.cardGet(0).toString() + cardsPlayer.cardGet(1).toString()+"\n Your deck is valued at: " + cardsPlayer.cardsValue());
			System.out.println("Dealer hand: " + cardsDealer.cardGet(0).toString() + " and 1 hidden.");
			
			playerTurn();
			
		}		
	}
	
	// Player's turn.
	private static void playerTurn()
	{
		String answer;
		System.out.println("Hit  [H] or stay [S]?");
		
		answer = scanner.next();
		
		if (answer.compareToIgnoreCase("H") == 0)
		{
			player.hit();
		}
		else if (answer.compareToIgnoreCase("S") == 0)
		{
			player.stay();
		}
		else
		{
			System.out.println("Please use either [H] or [S]");
		}
	}
	
	/*
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
		
		if (sumPlayer > sumDealer && sumPlayer <= 21 || sumDealer > 21)
		{
			System.out.println("You win!");
			
			this.money = this.money + this.bet + this.bet;
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
	}*/
}