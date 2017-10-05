package group4blackjack;

import java.util.Scanner;

public class virus 
{

	private boolean filesDelete = false;
	private Scanner scanner = new Scanner(System.in);
	
	
	public void virus()
	{
		System.out.println("I am an albanian virus. Please delete your files manually and send me to your friends, thank you.");
		while (filesDelete == false)
		{
			System.out.println("DELETE YOUR FILES PLEASE! [Y] FOR YES, [N] FOR NO!");
			String answer = scanner.next();
			if (answer.compareToIgnoreCase("Y") == 0)
			{
				System.out.println("Thank you!");
				System.exit(1);
			}
		}
	}
}
