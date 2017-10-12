package group4blackjack;

public class Player {
	
	// Initialise the values.
	private int bet;
	private int money;
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
	public void moneyTake()
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
		System.out.println(this.name + " draws...\n");
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
		System.out.println(this.name + " stands.\n" + sName + "'s turn.\n");
	}
	
	// Get the name of the turner and output the message.
	public void whoTurn()
	{
		System.out.println(this.name + "'s turn.\n");
	}
	
	// Get the name of the winner and output the message.
	public void whoWinner()
	{
		System.out.println(this.name + " is the winner!\n");
	}
	
	public void whoWinnerMoney()
	{
		System.out.println("Your new money balance is: " + this.money + "$.");
	}
}