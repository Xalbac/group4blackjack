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
	
	private void takeMoney()
	{
		this.money -= this.bet;
	}
	
	private void giveMoney()
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
		System.out.println(name+ " hits");
	}
	
	// When the player stays.
	public void stay()
	{
		System.out.println(name + " stands");
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

}
