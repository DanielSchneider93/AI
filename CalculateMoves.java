import java.util.ArrayList;
import java.util.List;

public class CalculateMoves {

	public List<PossibleMove> getPossibleMoves(int playerNumber, int[][] board, IntegrateMove ig) {

		// make new list of possible Moves
		List<PossibleMove> possibleMoves = new ArrayList<PossibleMove>();

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
								if(board[5][y] == playerNumber && board[6][y] != -1 && board[6][y] != playerNumber) {
									//we can jump off 
									possibleMoves.add(new PossibleMove(5, y));
								}
							}
						}
					}
				}
			}
		}
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
								if(board[5][y] == playerNumber && board[6][y] != -1 && board[6][y] != playerNumber) {
									//we can jump off 
									possibleMoves.add(new PossibleMove(5, y));
								}

							}
						}
					}
				}
			}
		}
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
								if(board[x][1] == playerNumber && board[x][0] != -1 && board[x][0] != playerNumber) {
									//we can jump off 
									possibleMoves.add(new PossibleMove(x, 1));
								}
							}
						}
					}
				}
			}
		}
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
								if(board[1][y] == playerNumber && board[0][y] != -1 && board[0][y] != playerNumber) {
									//we can jump off 
									possibleMoves.add(new PossibleMove(1, y));
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
