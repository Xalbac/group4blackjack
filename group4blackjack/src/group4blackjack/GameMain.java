package group4blackjack;

import java.util.Scanner;

public class GameMain
{
	// Initialise everything we need.
	private static float balance;								// User's money
	private static float bet;									// User's bet
	private static Scanner scanner = new Scanner(System.in);	// Define the input
	
	
	// Where the magic happens
	public static void main(String[] args)
	{
		balance = 100;
		boolean gameOver = false;
		
		while (balance > 0 && !gameOver)
		{
			System.out.println("Do you want to play? [P] Or quit? [Q]");
			String gameStart = scanner.next();
			
			if (gameStart.compareToIgnoreCase("P") == 0)
			{
				System.out.println("Game starts");
			}
			else if(gameStart.compareToIgnoreCase("Q") == 0)
			{
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
		
	}
	
	// What to do when the winner is announced.
	private void winner()
	{
		int sumPlayer = 10;
		int sumDealer = 11;
		
		if (sumPlayer>sumDealer && sumPlayer<=21 || sumDealer >21)
		{
			System.out.println("You win!");
			
			this.balance = this.balance + this.bet + this.bet;
		}
		else if (sumPlayer == sumDealer)
		{
			System.out.println("Draw. You keep your money this time. ");
			this.balance = this.balance + this.bet;
		}
		else
		{
			System.out.println("You lose!");
			
		}
	}
}