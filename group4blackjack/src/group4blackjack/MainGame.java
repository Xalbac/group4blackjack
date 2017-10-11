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
		Scanner ui = new Scanner(System.in);
		System.out.println("Welcome to the worst blackjack ever!\n");
		System.out.println("What is your name?");
		
		player.setName(ui.next());
		
		System.out.println("Name your opponent.");
		
		opponent.setName(ui.next());
		
		System.out.println("Welcome " + player.getName()+ "!\n You start off with " + player.getMoney() + ".");
		
		while (player.getMoney() > 0 && !GameOver)
		{
			opponentTurn = false;
			playerTurn = false;
			
			System.out.println("Would you like to [P] Play or [Q] Quit?");
			String answer = ui.next();
			
			if (answer.equalsIgnoreCase("P"))
			{
				try
				{
					System.out.println("Game starts...");
					gameStart();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else if (answer.equalsIgnoreCase("Q"))
			{
				GameOver = true;
				gameQuit();
			}
			else
			{
				System.out.println("Please use either P or Q.");
			}
		}
		if (player.getMoney() <= 0)
		{
			System.out.println("You have no money to play.\n Bye bye!");
			System.exit(1);
			ui.close();
		}
		ui.close();
	}
	
	// Where the game starts after initial "impact". 
	private static void gameStart()
	{
		Scanner uiGS = new Scanner(System.in);
		System.out.println("How much would you like to bet?");
		try 
		{
			player.setBet(uiGS.nextInt());
		}
		catch (InputMismatchException e)
		{
			e.getMessage();
		}
		
		if (player.getBet() > player.getMoney())
		{
			System.out.println("You cannot bet more than what you have!");
		}
		else
		{
			player.takeMoney();
			
			playDeck.FillDeckWithCards();
			playDeck.shuffleDeck();
			
			System.out.println("Dealing cards...");
			
			cardsPlayer.cardDraw(playDeck);
			cardsPlayer.cardDraw(playDeck);
			
			cardsOpponent.cardDraw(playDeck);
			cardsOpponent.cardDraw(playDeck);
			
			checkIfWinAtStart();
			
			System.out.println("Your cards: " + cardsPlayer.cardGet(0).toString() + cardsPlayer.cardGet(1).toString() + "\nYour deck is valued at: " + cardsPlayer.cardsValue());
			System.out.println("Dealer hand: " + cardsOpponent.cardGet(0).toString() + " and 1 hidden.");
			
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
		player.whoTurn();
		
		while (playerTurn == true)
		{
			Scanner uiPT = new Scanner(System.in);
			
			System.out.println("Would you like to [H] Hit or [S] Stand?");
			
			String answer = uiPT.next();
			
			if (answer.equalsIgnoreCase("H"))
			{
				playerHit();
				
			}
			else if (answer.equalsIgnoreCase("S"))
			{
				player.whoStands(opponent.getName());
				playerStay();
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
		if (cardsPlayer.cardsValue() > 21)
		{
			player.whoBusted();
			playerTurn = false;
			opponent.whoWinner();
			opponentTurn = false;
		}
	}
	
	// Player chooses stay. 
	private static void playerStay()
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