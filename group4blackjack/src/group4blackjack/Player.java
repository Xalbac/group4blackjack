package group4blackjack;

/****
 * 
 * The best version
 * 
 */


public class Player {
	
	String name;

	
	public void hit() {
		
		System.out.println("Player hits");
		
	}
	
	void stay() {
		System.out.println(name + "stands");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
