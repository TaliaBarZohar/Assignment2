//name: Talia Yarin BarZohar
//Id: 318257060
//name: Stav Sagi
//Id: 316584622
package Q2;

public class UserGame extends Game {
	public void startGame() {
		//Create object
		UserPlayer player1 = new UserPlayer(PlayerType.X);
		SelfPlayer player2 = new SelfPlayer(PlayerType.O);
		player1.setGame(this);
		player2.setGame(this);
		//Create threads
		Thread thread1 = new Thread(player1);
		Thread thread2 = new Thread(player2);
		//Start the threads
		thread2.start();
		thread1.start();
		try {
			thread1.join();
			thread2.join();

			if (isDraw()) {
				System.out.println("The board is full");
			} else {
				System.out.println("The game is over");
			}
		} catch (InterruptedException e) {
		
		}
	}
}

