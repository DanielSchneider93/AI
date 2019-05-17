import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import lenz.htw.sawhian.Move;

public class WeightFunction {
	
	boolean useLearning = false;

	double c1 = 1; 					// field is empty
	double c2 = 2.5; 				// goal for that stone is reached
	double c3 = 2; 					// factor for jumping points
	double c4 = 1.7; 				// jumping of board after jump
	double c5 = 0.2; 				// multiplicator for too much stones at the line
	
	public WeightFunction(boolean useLearning) {
		
		this.useLearning = useLearning;
		if (useLearning) {
			try {
				BufferedReader br = new BufferedReader(new FileReader("src/results.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					//br.readLine()
					
		}
		//TODO: load last best configuration from file
		//TODO: randomize +- 
		//TODO: after evaluation save result and randomized cX to file
	}

	public double evaluateMove(Move move, int[][] board, IntegrateMove ig) {
		double valueCount = 0;
		if (move.player == 0) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				valueCount = valueCount + c1;

			} else if (move.y + 1 > 6) {
				// stone is at goal
				valueCount = valueCount + c2;

			} else {
				// next field is occupied and not by our own stone
				int jumpCount = ig.jumpCheckP1(move, board);
				valueCount = valueCount + (jumpCount * c3);

				if (move.y + jumpCount > 6) {
					// jump ends with putting stone off the field
					valueCount = valueCount + c4;
				}
			}

			int stonesAtThisLine = 0;
			if (move.y == 0) {
				for (int y = 1; y < 7; y++) {
					if (board[move.x][y] != 0 && board[move.x][y] != -1) {
						stonesAtThisLine++;
					}
				}
			}
			if (stonesAtThisLine >= 2) {
				valueCount = valueCount * c5;
			}
		}

		// integrate move for player 2 (1)
		if (move.player == 1) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				valueCount = valueCount + c1;

			} else if (move.x + 1 > 6) {
				// stone is at goal
				valueCount = valueCount + c2;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = ig.jumpCheckP2(move, board);
				valueCount = valueCount + jumpCount * c3;

				if (move.x + jumpCount >= 7) {
					// jump ends with putting stone off the field
					valueCount = valueCount + c4;
				}
			}
			
			int stonesAtThisLine = 0;
			if (move.x == 0) {
				for (int x = 1; x < 7; x++) {
					if (board[x][move.y] != 0 && board[x][move.y] != -1) {
						stonesAtThisLine++;
					}
				}
			}
			if (stonesAtThisLine >= 2) {
				valueCount = valueCount * c5;
			}
		}

		// integrate move for player 3 (2)
		if (move.player == 2) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				valueCount = valueCount + c1;

			} else if (move.y - 1 < 0) {
				// stone is at goal
				valueCount = valueCount + c2;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = ig.jumpCheckP3(move, board);
				valueCount = valueCount + jumpCount * c3;

				if (move.y - jumpCount < 0) {
					// jump ends with putting stone off the field
					valueCount = valueCount + c4;
				}
			}
			
			int stonesAtThisLine = 0;
			if (move.y == 6) {
				for (int y = 0; y < 6; y++) {
					if (board[move.x][y] != 0 && board[move.x][y] != -1) {
						stonesAtThisLine++;
					}
				}
			}
			if (stonesAtThisLine >= 2) {
				valueCount = valueCount * c5;
			}
		}

		// integrate move for player 4 (3)
		if (move.player == 3) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				valueCount = valueCount + c1;

			} else if (move.x - 1 < 0) {
				// stone is at goal
				valueCount = valueCount + c2;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = ig.jumpCheckP4(move, board);
				valueCount = valueCount + jumpCount * c3;

				if (move.x - jumpCount < 0) {
					// jump ends with putting stone off the field
					valueCount = valueCount + c4;
				}
			}
			
			int stonesAtThisLine = 0;
			if (move.x == 6) {
				for (int x = 0; x < 6; x++) {
					if (board[x][move.y] != 0 && board[x][move.y] != -1) {
						stonesAtThisLine++;
					}
				}
			}
			if (stonesAtThisLine >= 2) {
				valueCount = valueCount * c5;
			}
		}

		return valueCount;
	}

}
