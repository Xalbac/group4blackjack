package group4blackjack;

// Imports. 
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainGame
{
	// Initialise the classes.
	static Deck playDeck = new Deck();
	static Deck cardsPlayer = new Deck();
	static Deck cardsOpponent = new Deck();
	static Player player = new Player();
	static Player opponent = new Player();
	
	// Initialise standard booleans. 
	static boolean playerTurn = false;
	static boolean opponentTurn = false;
	static boolean GameOver = false;
	
	// High impact violence. 
	public static void main(String[] args)
	{
		// Initialise the scanner and display a welcome message. 
		Scanner ui = new Scanner(System.in);
		System.out.println("Welcome to the worst blackjack ever!\n");
		System.out.println("What is your name?");
		
		// User input. 
		player.setName(ui.next());
		
		// Give the player the ability to choose the opponent's name. 
		System.out.println("Name your opponent.");
		
		// User input. 
		opponent.setName(ui.next());
		
		// Display the message and player's name and opponent's name. 
		System.out.println("Welcome " + player.getName()+ "!\n You start off with " + player.getMoney() + ".\nYou're playing against: " + opponent.getName());
		
		// As long as the player has money and the game is not over...
		while (player.getMoney() > 0 && !GameOver)
		{
			// Disable the booleans. 
			opponentTurn = false;
			playerTurn = false;
			
			// Ask the player if they want to quit or play. 
			System.out.println("Would you like to [P] Play or [Q] Quit?");
			String answer = ui.next();
			
			// Check if the player wants to play. 
			if (answer.equalsIgnoreCase("P"))
			{
				System.out.println("Game starts...");
				gameStart();
			}
			
			// If the player decides to quit like a little bitch...
			else if (answer.equalsIgnoreCase("Q"))
			{
				GameOver = true;
				gameQuit();
			}
			
			// Tell the player they need to go to elementary school again, because they can't read. 
			else
			{
				System.out.println("Please use either P or Q.");
			}
		}
		
		// Display this message to the player. 
		if (player.getMoney() <= 0)
		{
			System.out.println("You have no money to play.");
			ui.close();
			gameQuit();
		}
		ui.close();
	}
	
	// Where the game starts after initial "impact". 
	private static void gameStart()
	{
		// Create a new scanner for player input. 
		Scanner uiGS = new Scanner(System.in);
		
		// Ask the player how much they want to bet. 
		System.out.println("How much would you like to bet?");
		
		// Try to get the bet and money. 
		try 
		{
			player.setBet(uiGS.nextInt());
		}
		catch (InputMismatchException e)
		{
			e.printStackTrace();
		}
		
		// If the player bet's too much, take their wallet. 
		if (player.getBet() > player.getMoney())
		{
			System.out.println("You cannot bet more than what you have!");
		}
		
		// Otherwise, don't take their wallet. 
		else
		{
			// But take their money instead!
			player.takeMoney();
			
			// Create a deck of cards to play with. 
			playDeck.FillDeckWithCards();
			playDeck.shuffleDeck();	// Shuffle the cards. 
			
			// Display the message. 
			System.out.println("Dealing cards...");
			
			// Now draw 2 cards for the player and the opponent from the deck. 
			// Draw 2 cards for the player. 
			cardsPlayer.cardDraw(playDeck);
			cardsPlayer.cardDraw(playDeck);
			
			// Draw 2 cards for the opponent. 
			cardsOpponent.cardDraw(playDeck);
			cardsOpponent.cardDraw(playDeck);
			
			// Check to see if the player won at start. 
			checkIfWinAtStart();
			
			// Display player cards and the opponent's cards and display your value of cards. 
			System.out.println("Your cards: " + cardsPlayer.cardGet(0).toString() + cardsPlayer.cardGet(1).toString() + "\nYour deck is valued at: " + cardsPlayer.cardsValue());
			System.out.println("Dealer hand: " + cardsOpponent.cardGet(0).toString() + " and 1 hidden.");
			
			// Start the player's turn. 
			playerTurn = true;
			playerTurn();
		}
		uiGS.close();
	}
	
	// Checks if you win at start. 
	private static void checkIfWinAtStart()
	{
		if (cardsPlayer.cardsValue() == 21)
		{
			player.whoWinner();
			playerTurn = false;
			opponentTurn = false;
			player.giveMoney();
		}
		else if (cardsPlayer.cardsValue() > cardsOpponent.cardsValue())
		{
			player.whoWinner();
			playerTurn = false;
			opponentTurn = false;
			player.giveMoney();
		}
		else if (cardsPlayer.cardsValue() >= cardsOpponent.cardsValue())
		{
			opponent.whoWinner();
			playerTurn = false;
			opponentTurn = false;
		}
	}
	
	// Player's turn. 
	private static void playerTurn()
	{
		// Display player's turn. 
		player.whoTurn();
		
		// While it's player's turn...
		while (playerTurn == true)
		{
			// Start a new scanner for user input. 
			Scanner uiPT = new Scanner(System.in);
			
			System.out.println("Would you like to [H] Hit or [S] Stand?");
			
			String answer = uiPT.next();
			
			if (answer.equalsIgnoreCase("H"))
			{
				playerHit();
				
			}
			else if (answer.equalsIgnoreCase("S"))
			{
				playerStand();
				playerTurn = false;
				opponentTurn = true;
				opponentTurn();
			}
			else
			{
				System.out.println("Please use either S or H.");
			}
			
			uiPT.close();
		}
	}
	
	// Player chooses hit. 
	private static void playerHit()
	{
		cardsPlayer.cardDraw(playDeck);
		player.whoDraws();
		System.out.println("You draw: " + cardsPlayer.cardGet(cardsPlayer.deckSize()-1).toString());
		
		// If the value of the cards exceeds 21. 
		if (cardsPlayer.cardsValue() > 21)
		{
			player.whoBusted();
			playerTurn = false;
			opponent.whoWinner();
			opponentTurn = false;
		}
	}
	
	// Player chooses stay. 
	private static void playerStand()
	{
		System.out.println(player.getName() + " stands." + opponent.getName() +"'s turn.");
		playerTurn = false;
		opponentTurn = true;
	}
	
	// Opponent's turn.
	private static void opponentTurn()
	{
		while (opponentTurn == true)
		{
			opponent.whoTurn();
	
			System.out.println(opponent.getName() + " reveals hidden card." + cardsOpponent.cardGet(1).toString());
			System.out.println("His deck is valued at: " + cardsOpponent.cardsValue());
				
			while (cardsOpponent.cardsValue() < 17)
			{
				opponent.whoDraws();
				cardsOpponent.cardDraw(playDeck);
				System.out.println(cardsOpponent.cardGet(cardsOpponent.deckSize()-1).toString());
			}
			if (cardsOpponent.cardsValue() > 21)
			{
				opponent.whoBusted();
				opponentTurn = false;
				player.whoWinner();
				playerTurn = false;
				player.giveMoney();
			}
			if (cardsPlayer.cardsValue() > cardsOpponent.cardsValue())
			{
				player.whoWinner();
				opponentTurn = false;
				playerTurn = false;
				player.giveMoney();
			}
			else if (cardsPlayer.cardsValue() >= cardsOpponent.cardsValue())
			{
				opponent.whoWinner();
				opponentTurn = false;
				playerTurn = false;
			}
			else if (cardsPlayer.cardsValue() == cardsOpponent.cardsValue())
			{
				opponent.whoWinner();
				opponentTurn = false;
				playerTurn = false;
			}
			else if (cardsPlayer.cardsValue() <= cardsOpponent.cardsValue())
			{
				opponent.whoWinner();
				opponentTurn = false;
				playerTurn = false;
			}
		}
	}
	
	// Display the message and quit the game when the player chooses Quit.
	private static void gameQuit()
	{
		System.out.println("Chicken...");
		GameOver = true;
		System.exit(1);
	}
}