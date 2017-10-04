package group4blackjack;

import java.util.Scanner;

public class GameMain
{

	private static float balance;
	private static float bet;
	private static Scanner scanner = new Scanner(System.in);
	
	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		balance = 100;
		boolean gameOver = false;
		/*If we have money: Do while (money > 0)
		{
		}*/
		
		while (balance > 0 && !gameOver)
		{
			System.out.println("Do you want to play? [1] Or quit? [2]");
			String gameStart = scanner.next();
			
			if (gameStart == "1")
			{
				//proceed to game
			}
			else if(gameStart == "2")
			{
				gameOver = true;
			}
			else
			{
				System.out.println("Please use either 1 or 2");
			}
		}
	}
	
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