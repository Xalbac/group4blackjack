package group4blackjack;

public class Player {
	
	// Initialise the values.
	private int bet;
	private int money;
	private int winnerMoney;
	private String name;
	
	// Show the bet.
	public int betGet()
	{
		return this.bet;
	}
	
	// Get the player bet from Main.
	public void betSet(int iBet)
	{
		this.bet = iBet;
	}
	
	// Shows player money. 
	public int moneyGet()
	{
		return this.money;
	}
	
	// Give the player money.
	public void moneyGive()
	{
		this.money = this.money + this.bet + this.bet;
	}
	
	// Set the money.
	public void moneySet(int moneyIn)
	{
		this.money = moneyIn;
	}
	
	// Take player's money.
	private void moneyTake()
	{
		this.money = this.money - this.bet;
	}
	
	// Returns the player name. 
	public String nameGet()
	{
		return this.name;
	}
	
	// Sets the name. 
	public void nameSet(String name)
	{
		this.name = name;
	}

	// Get the name of the buster and output the message.
	public void whoBusted()
	{
		System.out.println(this.name + " busted!\n");
	}

	// Get the name of the drawer and output the message.
	public void whoDraws()
	{
		System.out.println(this.name + " draws...");
	}
	
	// When the player hits.
	public void whoHits()
	{
		System.out.println(this.name+ " hits.\n");
	}
	
	// Get the name of the stander and output the message.
	public void whoStands()
	{
		System.out.println(this.name + " stands.\n");
	}
	
	// Get the name of the stander and the opponent and output the message.
	public void whoStands(String sName)
	{
		System.out.println(this.name + " stands." + sName + "'s turn.\n");
	}
	
	// Get the name of the turner and output the message.
	public void whoTurn()
	{
		System.out.println(this.name + "'s turn.\n");
	}
	
	// Get the name of the winner and output the message.
	public void whoWinner(String oppname)
	{
		System.out.println(this.name + " is the winner and " + oppname + " is the loser!");
	}
	
	// Count how much the player wins.
	private void whoWinnerAmount()
	{
		this.winnerMoney = this.bet + this.bet;
	}
	
	// Display how much money they have now. 
	public void whoMoneyWinner()
	{
		System.out.println("Your new money balance is: " + this.money + "$.");
		whoWinnerAmount();
		System.out.println("You won: " + winnerMoney + "$.\n");
	}
	
	// Display the money the loser is left with. 
	public void whoMoneyLoser()
	{
		System.out.println("Your new money balance is: " + this.money + "$.");
	}
	
	// Display how much the player is betting and how much money they have left. 
	public void playerBet()
	{
		System.out.println("You're betting: " + this.bet + "$, from your: " + this.money + "$.");
		moneyTake();
		System.out.println("You now have: " + money + "$.\n");
	}
}