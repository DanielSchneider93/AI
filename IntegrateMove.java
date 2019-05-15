import lenz.htw.sawhian.Move;

public class IntegrateMove {

	int pointsP1 = 0;
	int pointsP2 = 0;
	int pointsP3 = 0;
	int pointsP4 = 0;

	int unusedStonesP1 = 7;
	int unusedStonesP2 = 7;
	int unusedStonesP3 = 7;
	int unusedStonesP4 = 7;

	public int integrateMove(Move move, int[][] board) {
		// integrate move for player 1 (0)
		if (move.player == 0) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				board[move.x][move.y] = move.player;
				removeStoneFromStack(move.player);

			} else if (move.y + 1 > 6) {
				// stone is at goal
				board[move.x][move.y] = -1;
				addPoint(move.player);

			} else if (board[move.x][move.y + 1] == -1) {
				// next field is free
				board[move.x][move.y] = -1;
				board[move.x][move.y + 1] = move.player;

			} else {
				// next field is occupied and not by our own stone
				int jumpCount = jumpCheckP1(move, board);
				System.out.println("Jump Counter: " + jumpCount);

				if (move.y + jumpCount > 6) {
					// jump ends with putting stone off the field
					board[move.x][move.y] = -1;
					addPoint(move.player);
				} else {
					// stone is on the field after jump
					board[move.x][move.y] = -1;
					board[move.x][move.y + jumpCount] = move.player;
				}
			}
		}

		// integrate move for player 2 (1)
		if (move.player == 1) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				board[move.x][move.y] = move.player;
				removeStoneFromStack(move.player);

			} else if (move.x + 1 > 6) {
				// stone is at goal
				board[move.x][move.y] = -1;
				addPoint(move.player);

			} else if (board[move.x + 1][move.y] == -1) {
				// next field is free
				board[move.x][move.y] = -1;
				board[move.x + 1][move.y] = move.player;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = jumpCheckP2(move, board);
				System.out.println("Jump Counter: " + jumpCount);

				if (move.x + jumpCount >= 7) {
					// jump ends with putting stone off the field
					board[move.x][move.y] = -1;
					addPoint(move.player);
				} else {
					// stone is on the field after jump
					board[move.x][move.y] = -1;
					board[move.x + jumpCount][move.y] = move.player;
				}
			}
		}

		// integrate move for player 3 (2)
		if (move.player == 2) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				board[move.x][move.y] = move.player;
				removeStoneFromStack(move.player);

			} else if (move.y - 1 < 0) {
				// stone is at goal
				board[move.x][move.y] = -1;
				addPoint(move.player);

			} else if (board[move.x][move.y - 1] == -1) {
				// next field is free
				board[move.x][move.y] = -1;
				board[move.x][move.y - 1] = move.player;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = jumpCheckP3(move, board);
				System.out.println("Jump Counter: " + jumpCount);

				if (move.y - jumpCount < 0) {
					// jump ends with putting stone off the field
					board[move.x][move.y] = -1;
					addPoint(move.player);
				} else {
					// stone is on the field after jump
					board[move.x][move.y] = -1;
					board[move.x][move.y - jumpCount] = move.player;
				}
			}
		}

		// integrate move for player 4 (3)
		if (move.player == 3) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				board[move.x][move.y] = move.player;
				removeStoneFromStack(move.player);

			} else if (move.x - 1 < 0) {
				// stone is at goal
				board[move.x][move.y] = -1;
				addPoint(move.player);

			} else if (board[move.x - 1][move.y] == -1) {
				// next field is free
				board[move.x][move.y] = -1;
				board[move.x - 1][move.y] = move.player;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = jumpCheckP4(move, board);
				System.out.println("Jump Counter: " + jumpCount);

				if (move.x - jumpCount < 0) {
					// jump ends with putting stone off the field
					board[move.x][move.y] = -1;
					addPoint(move.player);
				} else {
					// stone is on the field after jump
					board[move.x][move.y] = -1;
					board[move.x - jumpCount][move.y] = move.player;
				}
			}
		}
		return 0;
	}

	public int evaluateMove(Move move, int[][] board) {
		int valueCount = 0;
		if (move.player == 0) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				board[move.x][move.y] = move.player;
				valueCount++;

			} else if (move.y + 1 > 6) {
				// stone is at goal
				board[move.x][move.y] = -1;
				valueCount = valueCount + 4;

			} else if (board[move.x][move.y + 1] == -1) {
				// next field is free
				board[move.x][move.y] = -1;
				board[move.x][move.y + 1] = move.player;
				valueCount++;

			} else {
				// next field is occupied and not by our own stone
				int jumpCount = jumpCheckP1(move, board);
				valueCount = valueCount + jumpCount;
				System.out.println("Jump Counter: " + jumpCount);

				if (move.y + jumpCount > 6) {
					// jump ends with putting stone off the field
					board[move.x][move.y] = -1;
					valueCount++;
				} else {
					// stone is on the field after jump
					board[move.x][move.y] = -1;
					board[move.x][move.y + jumpCount] = move.player;
				}
			}
		}

		// integrate move for player 2 (1)
		if (move.player == 1) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				board[move.x][move.y] = move.player;
				valueCount++;

			} else if (move.x + 1 > 6) {
				// stone is at goal
				board[move.x][move.y] = -1;
				valueCount = valueCount + 4;

			} else if (board[move.x + 1][move.y] == -1) {
				// next field is free
				board[move.x][move.y] = -1;
				board[move.x + 1][move.y] = move.player;
				valueCount++;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = jumpCheckP2(move, board);
				valueCount = valueCount + jumpCount;
				System.out.println("Jump Counter: " + jumpCount);

				if (move.x + jumpCount >= 7) {
					// jump ends with putting stone off the field
					board[move.x][move.y] = -1;
					valueCount++;
				} else {
					// stone is on the field after jump
					board[move.x][move.y] = -1;
					board[move.x + jumpCount][move.y] = move.player;
				}
			}
		}

		// integrate move for player 3 (2)
		if (move.player == 2) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				board[move.x][move.y] = move.player;
				valueCount++;

			} else if (move.y - 1 < 0) {
				// stone is at goal
				board[move.x][move.y] = -1;
				valueCount = valueCount + 4;

			} else if (board[move.x][move.y - 1] == -1) {
				// next field is free
				board[move.x][move.y] = -1;
				board[move.x][move.y - 1] = move.player;
				valueCount++;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = jumpCheckP3(move, board);
				valueCount = valueCount + jumpCount;
				System.out.println("Jump Counter: " + jumpCount);

				if (move.y - jumpCount < 0) {
					// jump ends with putting stone off the field
					board[move.x][move.y] = -1;
					valueCount++;
				} else {
					// stone is on the field after jump
					board[move.x][move.y] = -1;
					board[move.x][move.y - jumpCount] = move.player;
				}
			}
		}

		// integrate move for player 4 (3)
		if (move.player == 3) {

			if (board[move.x][move.y] == -1) {
				// field is empty, place stone on it
				board[move.x][move.y] = move.player;
				valueCount++;

			} else if (move.x - 1 < 0) {
				// stone is at goal
				board[move.x][move.y] = -1;
				valueCount = valueCount + 4;

			} else if (board[move.x - 1][move.y] == -1) {
				// next field is free
				board[move.x][move.y] = -1;
				board[move.x - 1][move.y] = move.player;
				valueCount++;

			} else {
				// next field is occupied, check if we can jump
				int jumpCount = jumpCheckP4(move, board);
				valueCount = valueCount + jumpCount;
				System.out.println("Jump Counter: " + jumpCount);

				if (move.x - jumpCount < 0) {
					// jump ends with putting stone off the field
					board[move.x][move.y] = -1;
					valueCount++;
				} else {
					// stone is on the field after jump
					board[move.x][move.y] = -1;
					board[move.x - jumpCount][move.y] = move.player;
				}
			}
		}

		return valueCount;
	}

	private int jumpCheckP1(Move move, int[][] board) {
		int counter = 0;

		// im on field 6, so we jump off the board
		if (move.y + 2 == 7) {
			counter = 2;
			return counter;
		}

		// im on field 5, check if field 7 is empty
		if (move.y == 4 && board[move.x][move.y + 2] == -1) {
			counter = 2;
			return counter;
		}

		// im on field 4
		if (move.y == 3 && board[move.x][move.y + 2] == -1) {
			// jump off the field (2 jumps)
			if (board[move.x][move.y + 3] != -1 && board[move.x][move.y + 3] != 0) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 3
		if (move.y == 2 && board[move.x][move.y + 2] == -1) {
			if (board[move.x][move.y + 3] != -1 && board[move.x][move.y + 3] != 0 && board[move.x][move.y + 4] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 2
		if (move.y == 1 && board[move.x][move.y + 2] == -1) {
			if (board[move.x][move.y + 3] != -1 && board[move.x][move.y + 3] != 0 && board[move.x][move.y + 4] == -1
					&& board[move.x][move.y + 5] != -1 && board[move.x][move.y + 5] != 0) {
				counter = 6;
				return counter;
			}

			if (board[move.x][move.y + 3] != -1 && board[move.x][move.y + 3] != 0 && board[move.x][move.y + 4] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 1
		if (move.y == 0 && board[move.x][move.y + 2] == -1) {
			if (board[move.x][move.y + 3] != -1 && board[move.x][move.y + 3] != 0 && board[move.x][move.y + 4] == -1
					&& board[move.x][move.y + 5] != -1 && board[move.x][move.y + 5] != 0
					&& board[move.x][move.y + 6] == -1) {
				counter = 6;
				return counter;
			}
			if (board[move.x][move.y + 3] != -1 && board[move.x][move.y + 3] != 0 && board[move.x][move.y + 4] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}

		}

		return counter;
	}

	private int jumpCheckP2(Move move, int[][] board) {
		int counter = 0;

		// im on field 6, so we jump off the board
		if (move.x + 2 == 7) {
			counter = 2;
			return counter;
		}

		// im on field 5, check if field 7 is empty
		if (move.x == 4 && board[move.x + 2][move.y] == -1) {
			counter = 2;
			return counter;
		}

		// im on field 4
		if (move.x == 3 && board[move.x + 2][move.y] == -1) {
			// jump off the field (2 jumps)
			if (board[move.x + 3][move.y] != -1 && board[move.x + 3][move.y] != 1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 3
		if (move.x == 2 && board[move.x + 2][move.y] == -1) {
			if (board[move.x + 3][move.y] != -1 && board[move.x + 3][move.y] != 1 && board[move.x + 4][move.y] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 2
		if (move.x == 1 && board[move.x + 2][move.y] == -1) {
			if (board[move.x + 3][move.y] != -1 && board[move.x + 3][move.y] != 1 && board[move.x + 4][move.y] == -1
					&& board[move.x + 5][move.y] != -1 && board[move.x + 5][move.y] != 1) {
				counter = 6;
				return counter;
			}

			if (board[move.x + 3][move.y] != -1 && board[move.x + 3][move.y] != 1 && board[move.x + 4][move.y] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 1
		if (move.x == 0 && board[move.x + 2][move.y] == -1) {
			if (board[move.x + 3][move.y] != -1 && board[move.x + 3][move.y] != 1 && board[move.x + 4][move.y] == -1
					&& board[move.x + 5][move.y] != -1 && board[move.x + 5][move.y] != 1
					&& board[move.x + 6][move.y] == -1) {
				counter = 6;
				return counter;
			}
			if (board[move.x + 3][move.y] != -1 && board[move.x + 3][move.y] != 1 && board[move.x + 4][move.y] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}

		}

		return counter;
	}

	private int jumpCheckP3(Move move, int[][] board) {
		int counter = 0;

		// im on field 2, so we jump off the board
		if (move.y - 2 == -1) {
			counter = 2;
			return counter;
		}

		// im on field 3, check if field 0 is empty
		if (move.y == 2 && board[move.x][move.y - 2] == -1) {
			counter = 2;
			return counter;
		}

		// im on field 4
		if (move.y == 3 && board[move.x][move.y - 2] == -1) {
			// jump off the field (2 jumps)
			if (board[move.x][move.y - 3] != -1 && board[move.x][move.y - 3] != 2) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 5
		if (move.y == 4 && board[move.x][move.y - 2] == -1) {
			if (board[move.x][move.y - 3] != -1 && board[move.x][move.y - 3] != 2 && board[move.x][move.y - 4] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 6
		if (move.y == 5 && board[move.x][move.y - 2] == -1) {
			if (board[move.x][move.y - 3] != -1 && board[move.x][move.y - 3] != 2 && board[move.x][move.y - 4] == -1
					&& board[move.x][move.y - 5] != -1 && board[move.x][move.y - 5] != 2) {
				counter = 6;
				return counter;
			}

			if (board[move.x][move.y - 3] != -1 && board[move.x][move.y - 3] != 2 && board[move.x][move.y - 4] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 7
		if (move.y == 6 && board[move.x][move.y - 2] == -1) {
			if (board[move.x][move.y - 3] != -1 && board[move.x][move.y - 3] != 2 && board[move.x][move.y - 4] == -1
					&& board[move.x][move.y - 5] != -1 && board[move.x][move.y - 5] != 2
					&& board[move.x][move.y - 6] == -1) {
				counter = 6;
				return counter;
			}
			if (board[move.x][move.y - 3] != -1 && board[move.x][move.y - 3] != 2 && board[move.x][move.y - 4] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}

		}

		return counter;
	}

	private int jumpCheckP4(Move move, int[][] board) {
		int counter = 0;

		// im on field 2, so we jump off the board
		if (move.x - 2 == -1) {
			counter = 2;
			return counter;
		}

		// im on field 3, check if field 0 is empty
		if (move.x == 2 && board[move.x - 2][move.y] == -1) {
			counter = 2;
			return counter;
		}

		// im on field 4
		if (move.x == 3 && board[move.x - 2][move.y] == -1) {
			// jump off the field (2 jumps)
			if (board[move.x - 3][move.y] != -1 && board[move.x - 3][move.y] != 3) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 5
		if (move.x == 4 && board[move.x - 2][move.y] == -1) {
			if (board[move.x - 3][move.y] != -1 && board[move.x - 3][move.y] != 3 && board[move.x - 4][move.y] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 6
		if (move.x == 5 && board[move.x - 2][move.y] == -1) {
			if (board[move.x - 3][move.y] != -1 && board[move.x - 3][move.y] != 3 && board[move.x - 4][move.y] == -1
					&& board[move.x - 5][move.y] != -1 && board[move.x - 5][move.y] != 3) {
				counter = 6;
				return counter;
			}

			if (board[move.x - 3][move.y] != -1 && board[move.x - 3][move.y] != 3 && board[move.x - 4][move.y] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}
		}

		// im on field 7
		if (move.x == 6 && board[move.x - 2][move.y] == -1) {
			if (board[move.x - 3][move.y] != -1 && board[move.x - 3][move.y] != 3 && board[move.x - 4][move.y] == -1
					&& board[move.x - 5][move.y] != -1 && board[move.x - 5][move.y] != 3
					&& board[move.x - 6][move.y] == -1) {
				counter = 6;
				return counter;
			}
			if (board[move.x - 3][move.y] != -1 && board[move.x - 3][move.y] != 3 && board[move.x - 4][move.y] == -1) {
				counter = 4;
				return counter;
			} else {
				counter = 2;
				return counter;
			}

		}

		return counter;
	}

	public void removeStoneFromStack(int player) {
		switch (player) {
		case 0:
			unusedStonesP1--;
			break;
		case 1:
			unusedStonesP2--;
			break;
		case 2:
			unusedStonesP3--;
			break;
		case 3:
			unusedStonesP4--;
			break;
		}
	}

	public void addPoint(int player) {
		switch (player) {
		case 0:
			pointsP1++;
			break;
		case 1:
			pointsP2++;
			break;
		case 2:
			pointsP3++;
			break;
		case 3:
			pointsP4++;
			break;
		}
	}

	public void displayMatrix(int N, int mat[][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + mat[i][j]);

			System.out.print("\n");
		}
		System.out.print("\n");
	}
}
