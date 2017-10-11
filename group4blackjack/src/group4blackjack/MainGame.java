package group4blackjack;

// Imports. 
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainGame
{
	static Scanner ui = new Scanner(System.in);
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
		
		
		// Ask the player how much they want to bet. 
		System.out.println("How much would you like to bet?");
		System.out.println("You have: " + player.getMoney());
		
		// Try to get the bet and money. 
		/*try (Scanner uiGS = new Scanner(System.in);)
		{
			player.setBet(uiGS.nextInt());
		}
		catch (InputMismatchException e)
		{
			e.printStackTrace();
		}*/
		
		int answer = 0;
		
		answer = ui.nextInt();
		System.out.println("Please write a valid bet number");
		
		
		player.setBet(answer);
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
			
			// Display player cards and the opponent's cards and display your value of cards. 
			System.out.println("Your cards: " + cardsPlayer.cardGet(0).toString() + " and " + cardsPlayer.cardGet(1).toString() + "\nYour deck is valued at: " + cardsPlayer.cardsValue());
			System.out.println("Dealer hand: " + cardsOpponent.cardGet(0).toString() + " and 1 hidden.");
			
			// Check if the player wins at strart. 
			if (cardsPlayer.cardsValue() == 21)
			{
				player.whoWinner();
				playerTurn = false;
				opponentTurn = false;
				player.giveMoney();
				cardsPlayer.moveCardsToDeck(playDeck);
				cardsOpponent.moveCardsToDeck(playDeck);
			}
			else if (cardsOpponent.cardsValue() == 21)
			{
				opponent.whoWinner();
				playerTurn = false;
				opponentTurn = false;
				cardsPlayer.moveCardsToDeck(playDeck);
				cardsOpponent.moveCardsToDeck(playDeck);
			}
			else 
			{
				// Start the player's turn. 
				playerTurn = true;
				playerTurn();
			}
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
			
			// Ask the player what they want to do. 
			System.out.println("Would you like to [H] Hit or [S] Stand?");
			
			String answer = ui.next();
			// If the player chooses hit.
			if (answer.equalsIgnoreCase("H"))
			{
				playerHit();
			}
			
			// If the player chooses stand. 
			else if (answer.equalsIgnoreCase("S"))
			{
				playerStand();
				opponentTurn();
			}
			else
			{
				System.out.println("Go back to elementary school");
			}
		}
	}
	
	// Player chooses hit. 
	private static void playerHit()
	{
		// Draw a card. Display who is drawing. Display the card and value. 
		cardsPlayer.cardDraw(playDeck);
		player.whoDraws();
		System.out.println("You draw: " + cardsPlayer.cardGet(cardsPlayer.deckSize()-1).toString());
		System.out.println("Your total : " + cardsPlayer.cardsValue());
		
		// If the value of the cards exceeds 21. 
		if (cardsPlayer.cardsValue() > 21)
		{
			player.whoBusted();
			playerTurn = false;
			opponent.whoWinner();
			opponentTurn = false;
			cardsPlayer.moveCardsToDeck(playDeck);
			cardsOpponent.moveCardsToDeck(playDeck);
		}
	}
	
	// Player chooses stay. 
	private static void playerStand()
	{
		// Display that you are standing, opponent's turn is now.
		System.out.println(player.getName() + " stands.");
		playerTurn = false;
		opponentTurn = true;
	}
	
	// Opponent's turn.
	private static void opponentTurn()
	{
		// While it's the opponent's turn...
		while (opponentTurn == true)
		{
			// Display whose turn it is. 
			opponent.whoTurn();
	
			// Show the hidden card and the deck value of the opponent. 
			System.out.println(opponent.getName() + " reveals hidden card: " + cardsOpponent.cardGet(1).toString());
			System.out.println("His deck is valued at: " + cardsOpponent.cardsValue());
				
			// As long as the opponent's cards are not exceeding 17...
			while (cardsOpponent.cardsValue() < 17)
			{
				opponent.whoDraws();
				cardsOpponent.cardDraw(playDeck);
				System.out.println(cardsOpponent.cardGet(cardsOpponent.deckSize()-1).toString());
			}
			
			// If the cards exceed value of 21. 
			if (cardsOpponent.cardsValue() > 21)
			{
				opponent.whoBusted();
				opponentTurn = false;
				player.whoWinner();
				playerTurn = false;
				player.giveMoney();
				cardsPlayer.moveCardsToDeck(playDeck);
				cardsOpponent.moveCardsToDeck(playDeck);
			}
			
			// If the player has higher value of cards. 
			if (cardsPlayer.cardsValue() > cardsOpponent.cardsValue())
			{
				player.whoWinner();
				opponentTurn = false;
				playerTurn = false;
				player.giveMoney();
				cardsPlayer.moveCardsToDeck(playDeck);
				cardsOpponent.moveCardsToDeck(playDeck);
			}
			
			// If it's a draw or the opponent has more value of cards. 
			else if (cardsPlayer.cardsValue() <= cardsOpponent.cardsValue())
			{
				opponent.whoWinner();
				opponentTurn = false;
				playerTurn = false;
				cardsPlayer.moveCardsToDeck(playDeck);
				cardsOpponent.moveCardsToDeck(playDeck);
			}
		}
	}
	
	// Display the message and quit the game when the player chooses Quit.
	private static void gameQuit()
	{
		System.out.println("See you next time!");
		GameOver = true;
		System.exit(1);
	}
}