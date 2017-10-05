package group4blackjack;

import java.util.Scanner;

public class GameMain
{
	// Initialise everything we need.
	private static float balance;								// User's money
	private static float bet;									// User's bet
	private boolean playerTurnEnd = false;
	
	
	// Where the magic happens
	public static void main(String[] args)
	{
		balance = 100;
		boolean gameOver = false;
		
		Scanner scanner = new Scanner(System.in);
		String playerName;
		
		System.out.println("Welcome to group 4 blackjack!");
		
		while (balance > 0 && !gameOver)
		{
			System.out.println("Do you want to play? [P] Or quit? [Q]");
			String gameStart = scanner.next();
			
			if (gameStart.compareToIgnoreCase("P") == 0)
			{
				System.out.println("What is your name?");
				playerName = scanner.nextLine();
			}
			else if(gameStart.compareToIgnoreCase("Q") == 0)
			{
				System.out.println("Goodbye!");
				gameOver = true;
			}
			else
			{
				System.out.println("Please use either 1 or 2");
			}
		}
	}
	 
	private static void gameStart()
	{
		private String playerName;
	}
	
	private void playerTurn()
	{
		String answer;
		System.out.println("Hit  [H] or stay [S]?");
		
		answer = scanner.next();
		
		if (answer.compareToIgnoreCase("H") == 0)
		{
			playerHit();
		}
		else
		{
			playerStay();
		}
	}
	
	private void playerHit()
	{
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
		System.out.println("Player stays. Dealer's turn");
		playerTurnEnd = true;
	}
	
	private void dealerTurn()
	{

	}
	
	
	// What to do when the winner is announced.
	private void winner()
	{
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
}