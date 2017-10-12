package group4blackjack;

// Imports. 
//import java.util.InputMismatchException;
import java.util.Scanner;

public class MainGame
{
	// Initialise scanner. 
	private Scanner ui = new Scanner(System.in);
	
	// Initialise the classes.
	private Deck playDeck = new Deck();
	private Deck cardsPlayer = new Deck();
	private Deck cardsOpponent = new Deck();	
	private Player player = new Player();
	private Player opponent = new Player();
	
	// Initialise standard booleans. 
	private boolean playerTurn = false;
	private boolean opponentTurn = false;
	private boolean GameOver = false;
	private boolean doubleDown = false;
	
	// Where the game actually starts.
	// This is to avoid the STATIC thing.
	MainGame(String pName, String oName)
	{
		// Start player off with money.
		int moneyIn = 1000000;
		player.moneySet(moneyIn);
		
		// Set the player and opponent names.
		player.nameSet(pName);
		opponent.nameSet(oName);
		
		// Display the message and player's name and opponent's name. 
		System.out.println("Welcome " + player.nameGet()+ "!\n You start off with " + player.moneyGet() + "$.\nYou're playing against: " + opponent.nameGet());
		
		// As long as the player has money and the game is not over...
		while (player.moneyGet() > 0 && !GameOver)
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
		if (player.moneyGet() <= 0)
		{
			System.out.println("You have no money to play.");
			ui.close();
			gameQuit();
		}
		ui.close();
	}
	
	// Where the game starts after initial "impact". 
	private void gameStart()
	{
		// Ask the player how much they want to bet. 
		System.out.println("How much would you like to bet?");
		System.out.println("Your money: " + player.moneyGet() + "$.\n");
		
		// Try to get the bet. 
		int answer;
		answer = ui.nextInt();
		player.betSet(answer);
		
		// If the player bet's too much, take their wallet. 
		while (player.betGet() > player.moneyGet())
		{
			System.out.println("You cannot bet more than what you have!");
			answer = ui.nextInt();
			player.betSet(answer);
		}
		
		// Display how much they're betting. 
		player.playerBet();
		
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
		
		// Check if the player wins at start. 
		if (cardsPlayer.cardsValue() == 21)
		{
			player.whoWinner(opponent.nameGet());
			playerTurn = false;
			opponentTurn = false;
			player.moneyGive();
			player.whoMoneyWinner();
			cardsPlayer.moveCardsToDeck(playDeck);
			cardsOpponent.moveCardsToDeck(playDeck);
		}
		
		// Or if the opponent wins.
		else if (cardsOpponent.cardsValue() == 21)
		{
			opponent.whoWinner(player.nameGet());
			playerTurn = false;
			opponentTurn = false;
			cardsPlayer.moveCardsToDeck(playDeck);
			cardsOpponent.moveCardsToDeck(playDeck);
			player.whoMoneyLoser();
		}
		
		// Otherwise let's get the game started. 
		else 
		{
			// Start the player's turn. 
			playerTurn = true;
			playerTurn();
		}
	}

	// Player's turn. 
	private void playerTurn()
	{
		// Display player's turn. 
		player.whoTurn();
		
		// At first, enable double down. 
		doubleDown = true;
		
		// While it's player's turn...
		while (playerTurn == true)
		{
			
			if (doubleDown == true)
			{
				// Ask the player what they want to do. 
				System.out.println("Would you like to [H] Hit or [S] Stand or [D] Double Down? Double down ends your turn.");
				
				String answer = ui.next();
				// If the player chooses hit.
				if (answer.equalsIgnoreCase("H"))
				{
					doubleDown = false;
					playerHit();
				}
				
				// If the player chooses stand. 
				else if (answer.equalsIgnoreCase("S"))
				{
					doubleDown = false;
					playerStand();
					opponentTurn();
				}
				else if (answer.equalsIgnoreCase("D"))
				{
					playerDoubleDown();
					opponentTurn();
				}
				else
				{
					System.out.println("Please use either [P] or [S].");
				}
			}
			else
			{
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
					System.out.println("Please use either [P] or [S].");
				}
			}
		}
	}
	
	// Player chooses hit. 
	private void playerHit()
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
			opponent.whoWinner(player.nameGet());
			opponentTurn = false;
			cardsPlayer.moveCardsToDeck(playDeck);
			cardsOpponent.moveCardsToDeck(playDeck);
			player.whoMoneyLoser();
		}
		
		// If the player gets blackjack.
		else if (cardsPlayer.cardsValue() == 21)
		{
			player.whoWinner(opponent.nameGet());
			playerTurn = false;
			opponentTurn = false;
			cardsPlayer.moveCardsToDeck(playDeck);
			cardsOpponent.moveCardsToDeck(playDeck);
			player.moneyGive();
			player.whoMoneyWinner();
		}
	}
	
	// Player chooses double down. 
	private void playerDoubleDown()
	{
		// Calculate the double down bet. 
		player.betDoubleDown();
		
		// Draw a card. Display who is drawing. Display the card and value.
		cardsPlayer.cardDraw(playDeck);
		player.whoDraws();
		playerTurn = false;
		opponentTurn = true;
		System.out.println("You draw: " + cardsPlayer.cardGet(cardsPlayer.deckSize() - 1).toString());
		System.out.println("Your total : " + cardsPlayer.cardsValue());

		// If the value of the cards exceeds 21.
		if (cardsPlayer.cardsValue() > 21) {
			player.whoBusted();
			playerTurn = false;
			opponent.whoWinner(player.nameGet());
			opponentTurn = false;
			cardsPlayer.moveCardsToDeck(playDeck);
			cardsOpponent.moveCardsToDeck(playDeck);
			player.whoMoneyLoser();
		}

		// If the player gets blackjack.
		else if (cardsPlayer.cardsValue() == 21) {
			player.whoWinner(opponent.nameGet());
			playerTurn = false;
			opponentTurn = false;
			cardsPlayer.moveCardsToDeck(playDeck);
			cardsOpponent.moveCardsToDeck(playDeck);
			player.moneyGiveDoubleDown();
			player.whoMoneyWinner();
		}
	}
	
	// Player chooses stay. 
	private void playerStand()
	{
		// Display that you are standing, opponent's turn is now.
		player.whoStands();
		playerTurn = false;
		opponentTurn = true;
	}
	
	// Opponent's turn.
	private void opponentTurn()
	{
		// While it's the opponent's turn...
		while (opponentTurn == true)
		{
			// Display whose turn it is. 
			opponent.whoTurn();
	
			// Show the hidden card and the deck value of the opponent. 
			System.out.println(opponent.nameGet() + " reveals hidden card: " + cardsOpponent.cardGet(1).toString());
			System.out.println(opponent.nameGet() + "'s deck is valued at: " + cardsOpponent.cardsValue());
				
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
				player.whoWinner(opponent.nameGet());
				playerTurn = false;
				if (doubleDown == true)
				{
					player.moneyGiveDoubleDown();
				}
				else
				{
					player.moneyGive();
				}
				player.whoMoneyWinner();
				cardsPlayer.moveCardsToDeck(playDeck);
				cardsOpponent.moveCardsToDeck(playDeck);
			}
			
			// If the player has higher value of cards. 
			else if (cardsPlayer.cardsValue() > cardsOpponent.cardsValue())
			{
				player.whoWinner(opponent.nameGet());
				opponentTurn = false;
				playerTurn = false;
				if (doubleDown == true)
				{
					player.moneyGiveDoubleDown();
				}
				else
				{
					player.moneyGive();
				}
				player.whoMoneyWinner();
				cardsPlayer.moveCardsToDeck(playDeck);
				cardsOpponent.moveCardsToDeck(playDeck);
			}
			
			// If it's a draw or the opponent has more value of cards. 
			else if (cardsPlayer.cardsValue() <= cardsOpponent.cardsValue())
			{
				opponent.whoWinner(player.nameGet());
				opponentTurn = false;
				playerTurn = false;
				cardsPlayer.moveCardsToDeck(playDeck);
				cardsOpponent.moveCardsToDeck(playDeck);
				player.whoMoneyLoser();
			}
		}
	}
	
	// Display the message and quit the game when the player chooses Quit.
	private void gameQuit()
	{
		System.out.println("See you next time!\n");
		GameOver = true;
		System.exit(1);
	}
	
	// High impact violence. 
	public static void main(String[] args)
	{
		// Start a scanner SPECIFICALLy for this and close it. 
		Scanner scanner = new Scanner(System.in);
		
		// Initialise the scanner and display a welcome message. 
		System.out.println("Welcome to the worst blackjack ever!\n");
		System.out.println("What is your name?");
		String playerName = scanner.next();
		
		// Give the player the ability to choose the opponent's name. 
		System.out.println("\nName your opponent.");
		String opponentName = scanner.next();
		
		new MainGame(playerName, opponentName);
		
		scanner.close();
	}
}