package group4blackjack;

public class Player {
	
	private String name;	// Initialise player name. 
	
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
		return name;
	}

	// Sets the name. 
	public void setName(String name)
	{
		this.name = name;
	}

}
