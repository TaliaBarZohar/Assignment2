//name: Talia Yarin BarZohar
//Id: 318257060
//name: Stav Sagi
//Id: 316584622
package Q2;

public abstract class Player implements Runnable {
	private PlayerType sign;
	protected Game game;

	//setGame function
	public void setGame(Game game) {
		this.game = game;
	}

	//Player function
	public Player(PlayerType sign) {
		this.sign = sign;
	}

	//getSign function 
	public PlayerType getSign() {
		return sign;
	}
}
