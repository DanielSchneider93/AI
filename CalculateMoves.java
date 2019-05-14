import java.util.ArrayList;
import java.util.List;

import lenz.htw.sawhian.Move;

public class CalculateMoves {

	public CalculationResult calculateBestMove(int playerNumber, int[][] board, IntegrateMove ig) {
		List<PossibleMove> moves = getPossibleMoves(playerNumber, board, ig);

		if (moves.size() == 0) {
			System.out.println("No Moves Possible anymore!");
		}

		ArrayList<Integer> values = new ArrayList<Integer>();

		// Calculate the value of each turn and save it into values List
		for (PossibleMove pm : moves) {

			// create duplicate of actual board state
			int[][] board_temp = new int[board.length][];
			
			for (int i = 0; i < board.length; i++) {
				board_temp[i] = board[i].clone();
			}
			
			Move m = new Move(playerNumber, pm.x, pm.y);
			int value = ig.integrateMove(m, board_temp, false);
			values.add(value);
			
			System.out.println("Possible Move: x: " + m.x + " y: " + m.y + " value: " + value);
			 //TODO: return values and put it into alpha beta
		}
		
		CalculationResult cs = new CalculationResult(values, moves);
		return cs;
	}

	public List<PossibleMove> getPossibleMoves(int playerNumber, int[][] board, IntegrateMove ig) {

		// make new list of possible Moves
		List<PossibleMove> possibleMoves = new ArrayList<PossibleMove>();

		// ------------------------------------------------------------------------------
		// Player 1 -----------------------------------------------------------------
		if (playerNumber == 0) {
			// check if free slots to place stones
			for (int z = 0; z < 7; z++) {
				if (board[z][0] == -1) {
					if (ig.unusedStonesP1 > 0) {
						// save slot in possible turns
						possibleMoves.add(new PossibleMove(z, 0));
					}
				}
			}
			// check if i have stones to finish
			for (int x = 0; x < 7; x++) {
				if (board[x][6] == playerNumber) {
					possibleMoves.add(new PossibleMove(x, 6));
				}
			}
			// check if i have stones on the playfield
			for (int x = 0; x < 7; x++) {
				for (int y = 0; y < 7; y++) {
					if (board[x][y] == playerNumber) {
						// my stone is at that field
						if (y + 1 <= 6) {
							if (board[x][y + 1] == -1) {
								// next field is free, save possible turn
								possibleMoves.add(new PossibleMove(x, y));
							} else if (board[x][y + 1] >= 0 && board[x][y + 1] != playerNumber) {
								// next field is not free and it is not occupied by my own stone
								if (y + 2 <= 6) {
									if (board[x][y + 2] == -1) {
										// jump possible
										possibleMoves.add(new PossibleMove(x, y));
									}
								}
							}
						}
					}
				}
			}
		}
		// ------------------------------------------------------------------------------
		// Player 2 -----------------------------------------------------------------
		if (playerNumber == 1) {
			// check if free slots to place stones
			for (int z = 0; z < 7; z++) {
				if (board[0][z] == -1) {
					if (ig.unusedStonesP2 > 0) {
						// save slot in possible turns
						possibleMoves.add(new PossibleMove(0, z));
					}
				}
			}
			// check if i have stones to finish
			for (int x = 0; x < 7; x++) {
				if (board[6][x] == playerNumber) {
					possibleMoves.add(new PossibleMove(6, x));
				}
			}
			// check if i have stones on the playfield
			for (int x = 0; x < 7; x++) {
				for (int y = 0; y < 7; y++) {
					if (board[x][y] == playerNumber) {
						// my stone is at that field
						if (x + 1 <= 6) {
							if (board[x + 1][y] == -1) {
								// next field is free, save possible turn
								possibleMoves.add(new PossibleMove(x, y));
							} else if (board[x + 1][y] >= 0 && board[x + 1][y] != playerNumber) {
								// next field is not free and it is not occupied by my own stone

								if (x + 2 <= 6) {
									if (board[x + 2][y] == -1) {
										// jump possible
										possibleMoves.add(new PossibleMove(x, y));
									}
								}

							}
						}
					}
				}
			}
		}
		// ------------------------------------------------------------------------------
		// Player 3 -----------------------------------------------------------------
		if (playerNumber == 2) {
			// check if free slots to place stones
			for (int z = 0; z < 7; z++) {
				if (board[z][6] == -1) {
					if (ig.unusedStonesP3 > 0) {
						// save slot in possible turns
						possibleMoves.add(new PossibleMove(z, 6));
					}
				}
			}
			// check if i have stones to finish
			for (int x = 0; x < 7; x++) {
				if (board[x][0] == playerNumber) {
					possibleMoves.add(new PossibleMove(x, 0));
				}
			}
			// check if i have stones on the playfield
			for (int x = 0; x < 7; x++) {
				for (int y = 0; y < 7; y++) {
					if (board[x][y] == playerNumber) {
						// my stone is at that field
						if (y - 1 >= 0) {
							if (board[x][y - 1] == -1) {
								// next field is free, save possible turn
								possibleMoves.add(new PossibleMove(x, y));
							} else if (board[x][y - 1] >= 0 && board[x][y - 1] != playerNumber) {
								// next field is not free and it is not occupied by my own stone
								if (y - 2 >= 0) {
									if (board[x][y - 2] == -1) {
										// jump possible
										possibleMoves.add(new PossibleMove(x, y));
									}
								}
							}
						}
					}
				}
			}
		}
		// ------------------------------------------------------------------------------
		// Player 4 -----------------------------------------------------------------
		if (playerNumber == 3) {
			// check if free slots to place stones
			for (int z = 0; z < 7; z++) {
				if (board[6][z] == -1) {
					if (ig.unusedStonesP4 > 0) {
						// save slot in possible turns
						possibleMoves.add(new PossibleMove(6, z));
					}
				}
			}
			// check if i have stones to finish
			for (int x = 0; x < 7; x++) {
				if (board[0][x] == playerNumber) {
					possibleMoves.add(new PossibleMove(0, x));
				}
			}
			// check if i have stones on the playfield
			for (int x = 0; x < 7; x++) {
				for (int y = 0; y < 7; y++) {
					if (board[x][y] == playerNumber) {
						// my stone is at that field
						if (x - 1 >= 0) {
							if (board[x - 1][y] == -1) {
								// next field is free, save possible turn
								possibleMoves.add(new PossibleMove(x, y));
							} else if (board[x - 1][y] >= 0 && board[x - 1][y] != playerNumber) {
								// next field is not free and it is not occupied by my own stone
								if (x - 2 >= 0) {
									if (board[x - 2][y] == -1) {
										// jump possible
										possibleMoves.add(new PossibleMove(x, y));
									}
								}
							}
						}
					}
				}
			}
		}
		return possibleMoves;
	}

}
