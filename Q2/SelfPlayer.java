//name: Talia Yarin BarZohar
//Id: 318257060
//name: Stav Sagi
//Id: 316584622
package Q2;
import java.util.ArrayList;
import java.util.Random;


	public class SelfPlayer extends Player {
	    Random rand = new Random();

	    //Parameter constructor
	    public SelfPlayer(PlayerType sign) {
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
	            try {
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	            }

	            synchronized (game) {
	            	//If the cells are null
	                if (!game.isDraw()) {
	                	//We will insert all the free cells into the arrayList
	                    ArrayList<CellCoordinates> CellCoordinates = game.getFreeCells();
	                    //Random cell selection
	                    int index = rand.nextInt(CellCoordinates.size());
	                    game.setCell(CellCoordinates.get(index).getRow(), CellCoordinates.get(index).getCol(),super.getSign());
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

	    

