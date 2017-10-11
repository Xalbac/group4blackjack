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
		
		System.out.println("Welcome " + player.getName()+ "!\n You start off with " + player.playerMoney() + ".");
		
		player.playerMoney();
		
		while (player.playerMoney() > 0 && !GameOver)
		{
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
		}
	}
	
	private static void gameQuit()
	{
		System.out.println("Chicken...");
		System.exit(1);
	}
}