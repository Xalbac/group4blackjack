package group4blackjack;

public class Card {

	int value;
	String valueShown;

	public Card(int value) {
		super();
		this.value = value;
		switch (value) {
		case 2:
			valueShown="2";
			break;
		case 3:
			valueShown="3";
			break;
		case 4:
			valueShown="4";
			break;
		case 5:
			valueShown="5";
			break;
		case 6:
			valueShown="6";
			break;
		case 7:
			valueShown="7";
			break;
		case 8:
			valueShown="8";
			break;
		case 9:
			valueShown="9";
			break;
		case 10:
			valueShown="10";
			break;
		case 11:
			valueShown="11";
			break;

		default:
			break;
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
