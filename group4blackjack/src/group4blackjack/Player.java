package group4blackjack;

public class Player {
	private int money = 100;
	private int bet = 0;
	private String name;	// Initialise player name. 
	
	public void playerBet(int iBet)
	{
		this.bet = iBet;
	}
	
	public int showBet()
	{
		return this.bet;
	}
	
	public void takeMoney()
	{
		this.money -= this.bet;
	}
	
	public void giveMoney()
	{
		this.money += this.bet;
	}
	
	public int playerMoney()
	{
		return this.money;
	}
	
	// When the player hits.
	public void hit()
	{
		System.out.println(name+ " hits.\n");
	}
	
	// When the player stays.
	public void stay()
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

	public void Winner()
	{
		System.out.println(name + " is the winner!\n");
	}
	
	public void whoTurn()
	{
		System.out.println(name + "'s turn.\n");
	}
	
	public void whoDraws()
	{
		System.out.println(name + " draws...\n");
	}
}
