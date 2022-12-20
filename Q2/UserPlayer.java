//name: Talia Yarin BarZohar
//Id: 318257060
//name: Stav Sagi
//Id: 316584622
package Q2;
import java.util.Scanner;


public class UserPlayer extends Player {
	Scanner scanner = new Scanner(System.in);

	//Parameter constructor
	public UserPlayer(PlayerType sign) {
		super(sign);
	}

	
	public void run() {
		if (game == null) {
			throw new IllegalStateException("Game is not set!");
		}
		//Run the makeMove function
		makeMove();
	}

	public void makeMove() {
		while (true) {
			//If it's not your turn to play, wait
			while (game.getTurn() != getSign()) {
				try {
					synchronized (game) {
						game.wait();
						//If the cell is null, break
						if (game.isDraw()) {
							break;
						}
					}
				} catch (InterruptedException e) {
				}
			}

			synchronized (game) {
				//If the cells are null
				if (!game.isDraw()) {
					//Print the game board
					game.printBoard();
					//Game commands for the user
					System.out.println("Enter the place you want to play on:");
					int i = scanner.nextInt();
					int j = scanner.nextInt();
					game.setCell(i, j, super.getSign());
				} else {
					//The game is over and notify to other player
					game.notifyAll();
					break;
				}
				game.notifyAll();
			}
		}
	}
}
