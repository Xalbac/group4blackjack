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
		int moneyIn = 1000;
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
		
		// Create a deck of cards to play with and shuffle the cards.
		playDeck.FillDeckWithCards();
		playDeck.shuffleDeck();
		
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
		// Display player's turn and enable double down at first. 
		player.whoTurn();		// Display whose turn. 
		doubleDown = true;		// Enable double down. 
		
		// While it's player's turn...
		while (playerTurn == true)
		{
			// If double down is allowed. 
			if (doubleDown == true)
			{
				// Ask the player what they want to do. 
				System.out.println("Would you like to [H] Hit or [S] Stand or [D] Double Down? Double down ends your turn.");	// Display the message. 
				String answer = ui.next();																						// User input.
				
				// If the player chooses hit.
				if (answer.equalsIgnoreCase("H"))
				{
					// Disable double down and the player hits. 
					doubleDown = false;		// Disable double down. 
					playerHit();			// Player hits. 
				}
				
				// If the player chooses stand. 
				else if (answer.equalsIgnoreCase("S"))
				{
					// Disable double down, stop player's turn and go to opponent's turn. 
					doubleDown = false;		// Disable double down. 
					playerStand();			// Player stands.
					opponentTurn();			// Opponent's turn. 
				}
				
				// If the player chooses Double Down.
				else if (answer.equalsIgnoreCase("D"))
				{
					// Player doubles down and it is opponent's turn right after. 
					playerDoubleDown();		// Player's double down. 
					opponentTurn();
				}
				else
				{
					System.out.println("Please use either [P] or [S] or [D].");
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
		cardsPlayer.cardDraw(playDeck);		// Draw from this deck. 
		player.whoDraws();					// Who draws. 
		System.out.println("You draw: " + cardsPlayer.cardGet(cardsPlayer.deckSize()-1).toString());	// Display the card.
		System.out.println("Your total : " + cardsPlayer.cardsValue());									// Display the new value. 
		
		// If the value of the cards exceeds 21. 
		if (cardsPlayer.cardsValue() > 21)
		{
			// Display who busted, who won and who lost and the new money. 
			player.whoBusted();						// Who busted.
			opponent.whoWinner(player.nameGet());	// Who won and lost. 
			player.whoMoneyLoser();					// The new money. 
			
			// Disable the turns. 
			playerTurn = false;		// Disable player's turn. 
			opponentTurn = false;	// Disable opponent's turn. 
			
			// Move cards back to the deck. 
			cardsPlayer.moveCardsToDeck(playDeck);		// Move player's cards back to the deck. 
			cardsOpponent.moveCardsToDeck(playDeck);	// Move opponent's cards back to the deck. 
		}
		
		// If the player gets blackjack.
		else if (cardsPlayer.cardsValue() == 21)
		{
			// Display who won, calculate the money and display the new money.
			player.whoWinner(opponent.nameGet());	// Display the winner. 
			player.moneyGive();						// Calculate the money. 
			player.whoMoneyWinner();				// Display the new money. 

			// Disable the turns. 
			playerTurn = false;		// Disable player's turn. 
			opponentTurn = false;	// Disable opponent's turn. 
			
			// Move cards back to the deck. 
			cardsPlayer.moveCardsToDeck(playDeck);		// Move player's cards back to the deck. 
			cardsOpponent.moveCardsToDeck(playDeck);	// Move opponent's cards back to the deck. 
		}
	}
	
	// Player chooses double down. 
	private void playerDoubleDown()
	{
		// Calculate the double down bet. 
		player.betDoubleDown();
		
		// Draw a card. Display who is drawing. Display the card and value.
		cardsPlayer.cardDraw(playDeck);		// Draw from this deck.
		player.whoDraws();					// Display who draws. 
		System.out.println("You draw: " + cardsPlayer.cardGet(cardsPlayer.deckSize() - 1).toString());	// Display the card.
		System.out.println("Your total : " + cardsPlayer.cardsValue());									// Display the deck value.

		// If the value of the cards exceeds 21.
		if (cardsPlayer.cardsValue() > 21)
		{
			// Display who busted and display who won and who lost and display the money. 
			player.whoBusted();						// Who busted.
			opponent.whoWinner(player.nameGet());	// Who won and who lost.
			player.whoMoneyLoser();					// The new money.
			
			// Disable the turns.
			opponentTurn = true;	// Enable the opponent's turn. 
			playerTurn = false;		// Disable the player's turn. 
			
			// Put the cards back into the deck. 
			cardsPlayer.moveCardsToDeck(playDeck);		// Put the player cards back into the deck.
			cardsOpponent.moveCardsToDeck(playDeck);	// Put the opponent's cards back into the deck. 
		}

		// If the player gets blackjack.
		else if (cardsPlayer.cardsValue() == 21)
		{
			// Display the winner, the money we get. 
			player.whoWinner(opponent.nameGet());	// Who won and who lost. 
			player.moneyGiveDoubleDown();			// Give the money. 
			player.whoMoneyWinner();				// Display the money. 
			
			// Disable the turns.
			opponentTurn = true;	// Enable the opponent's turn. 
			playerTurn = false;		// Disable the player's turn. 
			
			// Put the cards back into the deck. 
			cardsPlayer.moveCardsToDeck(playDeck);		// Put the player cards back into the deck.
			cardsOpponent.moveCardsToDeck(playDeck);	// Put the opponent's cards back into the deck. 
		}
		else 
		{
			// Disable player turn and enable opponent turn.
			playerTurn = false;		// Disable player's turn. 
			opponentTurn = true;	// Enable opponent's turn. 
		}
	}
	
	// Player chooses stay. 
	private void playerStand()
	{
		// Display who is standing and disable player's turn and enable opponent's turn. 
		player.whoStands();		// Who is standing. 
		playerTurn = false;		// Disable player's turn. 
		opponentTurn = true;	// Enable opponent's turn. 
	}
	
	// Opponent's turn.
	private void opponentTurn()
	{
		// While it's the opponent's turn...
		while (opponentTurn == true)
		{
			// Display whose turn it is and show the hidden card and the deck value of the opponent. 
			opponent.whoTurn();		// Whose turn.
			System.out.println(opponent.nameGet() + " reveals hidden card: " + cardsOpponent.cardGet(1).toString());	// Display the hidden card.
			System.out.println(opponent.nameGet() + "'s deck is valued at: " + cardsOpponent.cardsValue());				// Display the value of the deck. 
				
			// As long as the opponent's cards are not exceeding 17...
			while (cardsOpponent.cardsValue() < 17)
			{
				// Display who draws. 
				opponent.whoDraws();				// Who draws.
				cardsOpponent.cardDraw(playDeck);	// Draw from this deck. 
				System.out.println(cardsOpponent.cardGet(cardsOpponent.deckSize()-1).toString());				// Display the card.
				System.out.println(opponent.nameGet() + "'s deck is valued at: " + cardsOpponent.cardsValue());	// Display the value of the deck. 
			}
			
			// If the cards exceed value of 21. 
			if (cardsOpponent.cardsValue() > 21)
			{
				// The opponent busted and display who won and who lost. 
				opponent.whoBusted();					// Who busted.
				player.whoWinner(opponent.nameGet());	// Who won and lost. 
					
				// If the double down is enabled give double money.
				if (doubleDown == true)
				{
					player.moneyGiveDoubleDown();
				}
				
				// If the double down is disabled give normal money.
				else
				{
					player.moneyGive();
				}
				
				// Display the new money balance. 
				player.whoMoneyWinner();
			}
			
			// If the player has higher value of cards. 
			else if (cardsPlayer.cardsValue() > cardsOpponent.cardsValue())
			{
				// Display who won and lost. 
				player.whoWinner(opponent.nameGet());

				// If the double down is enabled give double money.
				if (doubleDown == true)
				{
					player.moneyGiveDoubleDown();
				}
				
				// If the double down is disabled give normal money.
				else
				{
					player.moneyGive();
				}
				
				// Display the new money balance. 
				player.whoMoneyWinner();
			}
			
			// If it's a draw or the opponent has more value of cards. 
			else if (cardsPlayer.cardsValue() <= cardsOpponent.cardsValue())
			{
				// Display the winner and loser and the money.
				opponent.whoWinner(player.nameGet());	// Display winner and loser.
				player.whoMoneyLoser();					// Display the money.
			}
			else if (cardsOpponent.cardsValue() == 21)
			{
				// Display the winner and loser and the money.
				opponent.whoWinner(player.nameGet());	// Display winner and loser.
				player.whoMoneyLoser();					// Display the money.
			}
			
			// Disable the turns.
			opponentTurn = false;	// Disable opponent turn.
			playerTurn = false;		// Disable player turn.
			
			// Put the cards back into the deck. 
			cardsPlayer.moveCardsToDeck(playDeck);		// Put player cards in the deck.
			cardsOpponent.moveCardsToDeck(playDeck);	// Put opponent's cards back in the deck. 
		}
	}
	
	// Display the message and quit the game when the player chooses Quit.
	private void gameQuit()
	{
		// Quit the game, POLITELY.
		System.out.println("See you next time!\n");		// Display the message.
		GameOver = true;								// Game is over. 
		System.exit(1);									// Exit the system. 
	}
	
	// High impact violence. 
	public static void main(String[] args)
	{
		// Start a scanner SPECIFICALLy for this and close it. 
		Scanner scanner = new Scanner(System.in);
		
		// Initialise the scanner and display a welcome message. 
		System.out.println("Welcome to the worst blackjack ever!\n");	// Display welcome message. 
		System.out.println("What is your name?");						// Ask for player's name.
		String playerName = scanner.next();								// User input. 
		
		// Give the player the ability to choose the opponent's name. 
		System.out.println("\nName your opponent.");	// Ask for opponent's name.
		String opponentName = scanner.next();			// User input. 
		
		// Start the game with the given names. 
		new MainGame(playerName, opponentName);
		
		// Close the scanner. 
		scanner.close();
	}
}