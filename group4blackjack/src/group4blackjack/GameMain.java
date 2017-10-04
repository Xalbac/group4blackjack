package group4blackjack;

import java.util.Scanner;

public class GameMain
{

	private static int balance;
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
}