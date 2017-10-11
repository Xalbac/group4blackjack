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
		
		while (playerTurn == true)
		{
		Scanner uiPT = new Scanner(System.in);
		
		System.out.println("Would you like to [H] Hit or [S] Stand?");
		
		String answer = uiPT.next();
		
		if (answer.equalsIgnoreCase("H"))
		{
			playerHit();
			if (cardsPlayer.cardsValue() > 21)
			{
				player.whoBusted();
				playerTurn = false;
				DetermineWinner();
			}
		}
		else if (answer.equalsIgnoreCase("S"))
		{
			player.whoStands(dealer.getName());
			playerStay();
			playerTurn = false;
			dealerTurn = true;
			dealerTurn();
		}
		else
		{
			System.out.println("Please use either S or H.");
		}
		
		uiPT.close();
		}
	}
	
	private static void playerHit()
	{
		cardsPlayer.cardDraw(playDeck);
		player.whoDraws();
		System.out.println("You draw: " + cardsPlayer.cardGet(cardsPlayer.deckSize()-1).toString());
	}
	
	private static void playerStay()
	{
		System.out.println(player.getName() + " stands." + dealer.getName() +"'s turn.");
		playerTurn = false;
	}
	
	private static void dealerTurn()
	{
		while (dealerTurn == true)
		{
		dealer.whoTurn();

		System.out.println(dealer.getName() + " reveals hidden card." + cardsDealer.cardGet(1).toString());
		System.out.println("His deck is valued at: " + cardsDealer.cardsValue());
			
		while (cardsDealer.cardsValue() < 17)
		{
			dealer.whoDraws();
			cardsDealer.cardDraw(playDeck);
			System.out.println(cardsDealer.cardGet(cardsDealer.deckSize()-1).toString());
		}
		if (cardsDealer.cardsValue() > 21)
		{
			dealer.whoBusted();
			dealerTurn = false;
		}
		dealer.whoStands();
		}
	}
	
	private static void DetermineWinner()
	{
		if (cardsPlayer.cardsValue() > cardsDealer.cardsValue())
		{
			player.Winner();
		}
		else if (cardsPlayer.cardsValue() >= cardsDealer.cardsValue())
		{
			dealer.Winner();
		}
		else if (cardsPlayer.cardsValue() == cardsDealer.cardsValue())
		{
			dealer.Winner();
		}
		else if (cardsPlayer.cardsValue() < cardsDealer.cardsValue());
	}
	
	private static void gameQuit()
	{
		System.out.println("Chicken...");
		GameOver = true;
		System.exit(1);
	}
}