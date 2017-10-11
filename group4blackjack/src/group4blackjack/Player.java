package group4blackjack;

public class Player {
	
	// Initialise standard values. 
	private int money = 100;
	private int bet = 0;
	private String name;
	
	// Get the player bet from Main.
	public void setBet(int iBet)
	{
		this.bet = iBet;
	}
	
	// Show the bet.
	public int getBet()
	{
		return this.bet;
	}
	
	// Take player's money.
	public void takeMoney()
	{
		this.money -= this.bet;
	}
	
	// Give the player money.
	public void giveMoney()
	{
		this.money = this.bet + this.bet;
	}
	
	// Shows player money. 
	public int getMoney()
	{
		return this.money;
	}
	
	// When the player hits.
	public void Hit()
	{
		System.out.println(name+ " hits.\n");
	}
	
	// When the player stays.
	public void Stay()
	{
		System.out.println(name + " stands.\n");
	}
	
	// Returns the player name. 
	public String getName()
	{
		return this.name;
	}

	// Sets the name. 
	public void setName(String name)
	{
		this.name = name;
	}

	// Get the name of the winner and output the message.
	public void whoWinner()
	{
		System.out.println(name + " is the winner!\n");
	}
	
	// Get the name of the turner and output the message.
	public void whoTurn()
	{
		System.out.println(name + "'s turn.\n");
	}
	
	// Get the name of the drawer and output the message.
	public void whoDraws()
	{
		System.out.println(name + " draws...\n");
	}
	
	// Get the name of the buster and output the message.
	public void whoBusted()
	{
		System.out.println(name + " busted!\n");
	}
	
	// Get the name of the stander and the opponent and output the message.
	public void whoStands(String sName)
	{
		String sname = sName;
		System.out.println(name + " stands.\n" + sname + "'s turn.\n");
	}
	
	// Get the name of the stander and output the message.
	public void whoStands()
	{
		System.out.println(name + " stands.\n");
	}
}