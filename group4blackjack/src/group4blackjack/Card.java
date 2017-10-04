package group4blackjack;

/****
 * 
 * The best version
 * @param <Suits>
 * 
 */

public class Card<Suits> {
	private Suits cardSuit;
	private int cardNum;
	private int[] numInt = {2,3,4, 5, 6, 7, 8, 9, 10,11};

	public Card (Suits stype, int snum) {
		this.cardSuit = stype;
		if (snum>=1 && snum <=13)
			this.cardNum = snum;
		else {
			System.err.println(snum+" is not a valid card number\n");
			System.exit(1);
		}
	}
 
public int getCardNumber(){
	
	return this.cardNum;
}

public String toString(){
	
	return this.numInt[this.cardNum - 1]+" of "+this.cardSuit.toString();
}



}
