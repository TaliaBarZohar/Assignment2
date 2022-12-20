//name: Talia Yarin BarZohar
//Id: 318257060
//name: Stav Sagi
//Id: 316584622
package Q2;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public abstract class Game {
	// Variables
	private PlayerType[][] gameBoard = new PlayerType[3][3];
	private PlayerType currentPlayer;

	// Default constructor
	public Game() {
		currentPlayer = PlayerType.X;
	}

	// printBoard function
	public void printBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == null) {
					System.out.print(" ");
				} else {
					System.out.print(gameBoard[i][j]);
				}
				if (j < 2) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i < 2) {
				System.out.println("-+-+-");
			}
		}
	}

	// getTurn function
	public PlayerType getTurn() {
		return currentPlayer;
	}

	// A function to insert the null cells into the ArrayList
	public ArrayList<CellCoordinates> getFreeCells() {
		ArrayList<CellCoordinates> freeCells = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == null) {
					freeCells.add(new CellCoordinates(i, j));
				}
			}
		}
		return freeCells;
	}

	// A function to insert values into cells in an array
	public void setCell(int row, int col, PlayerType type) {
		if (row >= 0 && row < 3 && col >= 0 && col < 3) {
			if (gameBoard[row][col] == null) {
				gameBoard[row][col] = type;
				if (type == PlayerType.X)
					currentPlayer = PlayerType.O;
				else
					currentPlayer = PlayerType.X;
			} else
				throw new InvalidParameterException("tried to set occupied cell");
		} else {
			throw new InvalidParameterException("invalid index");
		}
	}

	// Function to check if someone is winner
	public boolean win() {
		if (((gameBoard[0][0] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][2]))
				|| (gameBoard[0][2] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][0]))
			return true;
		// Check rows
		if ((gameBoard[0][0] == gameBoard[0][1]) && (gameBoard[0][1] == gameBoard[0][2]))
			return true;
		if ((gameBoard[1][0] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[1][2]))
			return true;
		if ((gameBoard[2][0] == gameBoard[2][1]) && (gameBoard[2][1] == gameBoard[2][2]))
			return true;
		// Check columns
		if ((gameBoard[0][0] == gameBoard[1][0]) && (gameBoard[1][0] == gameBoard[2][0]))
			return true;
		if ((gameBoard[0][1] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][1]))
			return true;
		if ((gameBoard[0][2] == gameBoard[1][2]) && (gameBoard[1][2] == gameBoard[2][2]))
			return true;
		return false;
	}

	// A function that checks if a cell is free or not
	public boolean isDraw() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}
}
