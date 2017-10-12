package group4blackjack;

public class Player {
	
	// Initialise the values.
	private int bet;
	private int money;
	private String name;
	
	// Show the bet.
	public int getBet()
	{
		return this.bet;
	}
	
	// Shows player money. 
	public int getMoney()
	{
		return this.money;
	}
	
	// Returns the player name. 
	public String getName()
	{
		return this.name;
	}
	
	// Give the player money.
	public void giveMoney()
	{
		this.money = this.bet + this.bet;
	}
	
	// When the player hits.
	public void Hit()
	{
		System.out.println(name+ " hits.\n");
	}
	
	// Get the player bet from Main.
	public void setBet(int iBet)
	{
		this.bet = iBet;
	}
	
	// Set the money.
	public void setMoney(int moneyIn)
	{
		this.money = moneyIn;
	}
	
	// Sets the name. 
	public void setName(String name)
	{
		this.name = name;
	}
	
	// When the player stays.
	public void Stay()
	{
		System.out.println(name + " stands.\n");
	}

	// Take player's money.
	public void takeMoney()
	{
		this.money = this.money - this.bet;
	}

	// Get the name of the buster and output the message.
	public void whoBusted()
	{
		System.out.println(name + " busted!\n");
	}
	
	// Get the name of the drawer and output the message.
	public void whoDraws()
	{
		System.out.println(name + " draws...\n");
	}
	
	// Get the name of the stander and output the message.
	public void whoStands()
	{
		System.out.println(name + " stands.\n");
	}
	
	// Get the name of the stander and the opponent and output the message.
	public void whoStands(String sName)
	{
		String sname = sName;
		System.out.println(name + " stands.\n" + sname + "'s turn.\n");
	}
	
	// Get the name of the turner and output the message.
	public void whoTurn()
	{
		System.out.println(name + "'s turn.\n");
	}
	
	// Get the name of the winner and output the message.
	public void whoWinner()
	{
		System.out.println(name + " is the winner!\n");
	}
}