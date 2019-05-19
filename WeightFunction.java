import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import lenz.htw.sawhian.Move;

public class WeightFunction {

	FitnessFunction ff;
	boolean useLearning = false;
	int valueOfLastGame = 0;

	double c1; // field is empty
	double c2; // goal for that stone is reached
	double c3; // factor for jumping points
	double c4; // jumping of board after jump
	double c5; // multiplicator for too much stones at the line
	
	double c1_new; // field is empty
	double c2_new; // goal for that stone is reached
	double c3_new; // factor for jumping points
	double c4_new; // jumping of board after jump
	double c5_new; // multiplicator for too much stones at the line
	
	public WeightFunction(boolean useLearning, FitnessFunction ff) throws IOException {

		this.ff = ff;
		BufferedReader br = null;
		this.useLearning = useLearning;
		
			// load last best configuration from file
			try {
				br = new BufferedReader(new FileReader("src/results.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			String st;
			st = br.readLine();
			String s[] = st.split(", ");
			double out[] = new double[s.length];
			for (int i = 0; i < s.length; i++) 
				out[i] = Double.parseDouble(s[i]);
			
			c1 = out[1];
			c2 = out[2];
			c3 = out[3];
			c4 = out[4];
			c5 = out[5];
					
	if (useLearning) {		
			//randomize last values
			valueOfLastGame = (int) out[0];
			c1_new = out[1] + getRandomDouble();
			c2_new = out[2] + getRandomDouble();
			c3_new = out[3] + getRandomDouble();
			c4_new = out[4] + getRandomDouble();
			c5_new = out[5] + getRandomDouble();
			
			System.out.println("new randomized cX: " + c1_new + ", " + c2_new + ", " + c3_new + ", " + c4_new + ", " + c5_new);
		}
	}

	public void evaluateBoard(int winner) throws IOException {
		int evaluation = ff.evalutateGame(winner);
		String text;
		System.out.println("old value " + valueOfLastGame + " new value " + evaluation);
		
		//check which generation has besser  result and save it for next run
		if(valueOfLastGame > evaluation) {
			text = String.valueOf(valueOfLastGame) + ", " + c1 + ", " + c2 + ", " + c3 + ", " + c4 + ", " + c5;
		}else {
			text = String.valueOf(evaluation) + ", " + c1_new + ", " + c2_new + ", " + c3_new + ", " + c4_new + ", " + c5_new;
		}
		
		System.out.println("new cX: " + text);
		
		FileWriter fw = new FileWriter("src/results.txt");
		fw.write(text);
		fw.close();
	}
	
	public double getRandomDouble() {
		double randomBetweenZeroAndThree = ThreadLocalRandom.current().nextDouble(0, 3);
		
		if(ThreadLocalRandom.current().nextInt(2) == 0)
			return randomBetweenZeroAndThree;
		else
			return -randomBetweenZeroAndThree;
	}

	public double evaluateMove(Move move, int[][] board, IntegrateMove ig, double x1, double x2, double x3 , double x4, double x5) {
		double valueCount = 0;
		if (move.player == 0) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				valueCount = valueCount + x1;

			} else if (move.y + 1 > 6) {
				// stone is at goal
				valueCount = valueCount + x2;

			} else {
				// next field is occupied and not by our own stone
				int jumpCount = ig.jumpCheckP1(move, board);
				valueCount = valueCount + (jumpCount * x3);

				if (move.y + jumpCount > 6) {
					// jump ends with putting stone off the field
					valueCount = valueCount + x4;
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
				valueCount = valueCount * x5;
			}
		}

		// integrate move for player 2 (1)
		if (move.player == 1) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				valueCount = valueCount + x1;

			} else if (move.x + 1 > 6) {
				// stone is at goal
				valueCount = valueCount + x2;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = ig.jumpCheckP2(move, board);
				valueCount = valueCount + jumpCount * x3;

				if (move.x + jumpCount >= 7) {
					// jump ends with putting stone off the field
					valueCount = valueCount + x4;
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
				valueCount = valueCount * x5;
			}
		}

		// integrate move for player 3 (2)
		if (move.player == 2) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				valueCount = valueCount + x1;

			} else if (move.y - 1 < 0) {
				// stone is at goal
				valueCount = valueCount + x2;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = ig.jumpCheckP3(move, board);
				valueCount = valueCount + jumpCount * x3;

				if (move.y - jumpCount < 0) {
					// jump ends with putting stone off the field
					valueCount = valueCount + x4;
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
				valueCount = valueCount * x5;
			}
		}

		// integrate move for player 4 (3)
		if (move.player == 3) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				valueCount = valueCount + x1;

			} else if (move.x - 1 < 0) {
				// stone is at goal
				valueCount = valueCount + x2;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = ig.jumpCheckP4(move, board);
				valueCount = valueCount + jumpCount * x3;

				if (move.x - jumpCount < 0) {
					// jump ends with putting stone off the field
					valueCount = valueCount + x4;
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
				valueCount = valueCount * x5;
			}
		}

		return valueCount;
	}

}
