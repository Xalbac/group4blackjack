package group4blackjack;

import java.util.Scanner;

public class MainGame
{
	static Deck playDeck = new Deck();
	static Deck cardsPlayer = new Deck();
	static Deck cardsDealer = new Deck();
	static Player player = new Player();
	static Player dealer = new Player();
	
	static boolean playerTurn = false;
	static boolean dealerTurn = false;
	static boolean GameOver = false;
	
	public static void main(String[] args)
	{
		Scanner ui = new Scanner(System.in);
		System.out.println("Welcome to the worst blackjack ever!\n");
		System.out.println("What is your name?");
		
		player.setName(ui.next());
		
		System.out.println("Name your opponent.");
		
		dealer.setName(ui.next());
		
		System.out.println("Welcome " + player.getName()+ "!\n You start off with " + player.playerMoney() + ".");
		
		player.playerMoney();
		
		while (player.playerMoney() > 0 && !GameOver)
		{
			dealerTurn = false;
			playerTurn = false;
			
			System.out.println("Would you like to [P] Play or [Q] Quit?");
			String answer = ui.next();
			
			if (answer.compareToIgnoreCase("P") == 0)
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
			else if (answer.compareToIgnoreCase("Q") == 0)
			{
				GameOver = true;
				gameQuit();
			}
			else
			{
				System.out.println("Please use either P or Q.");
			}
		}
		if (player.playerMoney() <= 0)
		{
			System.out.println("You have no money to play.\n Bye bye!");
			System.exit(1);
			ui.close();
		}
		ui.close();
	}
	
	private static void gameStart()
	{
		Scanner uiGS = new Scanner(System.in);
		System.out.println("How much would you like to bet?");
		try 
		{
			player.playerBet(uiGS.nextInt());
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.getMessage();
		}
		
		if (player.showBet() > player.playerMoney())
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
			
			cardsDealer.cardDraw(playDeck);
			cardsDealer.cardDraw(playDeck);
			
			checkIfWinAtStart();
			
			System.out.println("Your cards: " + cardsPlayer.cardGet(0).toString() + cardsPlayer.cardGet(1).toString() + "\nYour deck is valued at: " + cardsPlayer.cardsValue());
			System.out.println("Dealer hand: " + cardsDealer.cardGet(0).toString() + " and 1 hidden.");
			
			playerTurn = true;
			playerTurn();
		}
		uiGS.close();
	}
	
	private static void checkIfWinAtStart()
	{
		if (cardsPlayer.cardsValue() == 21)
		{
			if (cardsPlayer.cardsValue() > cardsDealer.cardsValue())
			{
				player.Winner();
			}
			else if (cardsPlayer.cardsValue() >= cardsDealer.cardsValue())
			{
				dealer.Winner();
			}
		}
	}
	
	private static void playerTurn()
	{
		player.whoTurn();
		
		Scanner uiPT = new Scanner(System.in);
		
		System.out.println("Would you like to [H] Hit or [S] Stand?");
		
		String answer = uiPT.next();
		
		if (answer.compareToIgnoreCase("H") == 0)
		{
			playerHit();
			if (cardsPlayer.cardsValue() > 21)
			{
				System.out.println("Busted!");
				playerTurn = false;
				GameOver = true;
			}
		}
		else if (answer.compareToIgnoreCase("S") == 0)
		{
			playerStay();
			dealerTurn = true;
		}
		else
		{
			System.out.println("Please use either S or H.");
		}
		uiPT.close();
	}
	
	private static void playerHit()
	{
		cardsPlayer.cardDraw(playDeck);
		System.out.println("You draw: " + cardsPlayer.cardGet(cardsPlayer.deckSize()-1).toString());
	}
	
	private static void playerStay()
	{
		System.out.println("Player stands. Dealer's turn.");
		playerTurn = false;
	}
	
	private static void dealerTurn()
	{
		while (dealerTurn = true)
		{
			dealer.whoTurn();
			while (cardsDealer.cardsValue() < 17)
			{
				System.out.println("");
			}
		}
	}
	
	private static void gameQuit()
	{
		System.out.println("Chicken...");
		GameOver = true;
		System.exit(1);
	}
}