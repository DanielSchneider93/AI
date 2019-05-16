import java.util.List;

import lenz.htw.sawhian.Move;

public class AlphaBeta {

	// TODO: implement logic if player gets kicked

	// TODO: alpha beta cut only after max and 3x min


	CalculateMoves cv = new CalculateMoves();

	Move savedMoveMax = null;
	Move savedMoveMin = null;
	int myPlayerNumber;
	int maxDepth = 0;

	public AlphaBeta(int playerNumber) {
		myPlayerNumber = playerNumber;
	}

	public double max(int[][] board, int depth, IntegrateMove ig, int playerNumber, Move last_move) {

		int[][] boardToEvaluate = copyBoard(board);

		if (depth == maxDepth) {
			return ig.evaluateMove(last_move, boardToEvaluate);
		}

		double maxValue = -10000;
		List<PossibleMove> pm = cv.getPossibleMoves(playerNumber % 4, board, ig);

		int possibleMoves = pm.size();

		if (pm.size() == 0) {
			return ig.evaluateMove(last_move, boardToEvaluate);
		}

		System.out.println("maximizing, possible moves: " + pm.size());

		if (last_move != null) {
			ig.integrateMove(last_move, board, false);
		}

		while (possibleMoves > 0) {
			int[][] board_temp = copyBoard(board);

			Move temp_move = new Move(playerNumber % 4, pm.get(possibleMoves - 1).x, pm.get(possibleMoves - 1).y);

			int newPlayerNumber = (playerNumber + 1) % 4;

			double value = min(board_temp, depth - 1, ig, newPlayerNumber, temp_move);
			System.out.println("value from minimizing: " + value + " move: " + temp_move);

			possibleMoves--;

			if (value > maxValue) {
				maxValue = value;

				if (depth == maxDepth + depth)
					savedMoveMax = temp_move;
			}
		}
		return maxValue;
	}

	public double min(int[][] board, int depth, IntegrateMove ig, int playerNumber, Move last_move) {

		int[][] boardToEvaluate = copyBoard(board);

		if (depth == maxDepth) {
			return ig.evaluateMove(last_move, boardToEvaluate);
		}

		double minValue = 10000;
		List<PossibleMove> pm = cv.getPossibleMoves(playerNumber % 4, board, ig);

		int possibleMoves = pm.size();

		if (pm.size() == 0) {
			return ig.evaluateMove(last_move, boardToEvaluate);
		}

		System.out.println("minimizing, possible moves: " + pm.size());

		ig.integrateMove(last_move, board, false);

		while (possibleMoves > 0) {
			int[][] board_temp = copyBoard(board);

			Move temp_move = new Move(playerNumber % 4, pm.get(possibleMoves - 1).x, pm.get(possibleMoves - 1).y);

			int newPlayerNumber = (playerNumber + 1) % 4;

			double value;

			if (newPlayerNumber == myPlayerNumber) {
				value = max(board_temp, depth - 1, ig, newPlayerNumber, temp_move);
				System.out.println("value from maximizing: " + value);
			} else

			{
				value = min(board_temp, depth - 1, ig, newPlayerNumber, temp_move);
				System.out.println("value from minimizing: " + value);
			}

			possibleMoves--;

			if (value < minValue) {
				minValue = value;

				if (depth == maxDepth + 1)
					savedMoveMin = temp_move;
			}
		}
		return minValue;
	}

	public Move getSavedMoveMax() {
		return savedMoveMax;
	}

	public Move getSavedMoveMin() {
		return savedMoveMin;
	}

	public int[][] copyBoard(int[][] board) {
		// create duplicate of actual board state
		int[][] temp = new int[board.length][];
		for (int i = 0; i < board.length; i++) {
			temp[i] = board[i].clone();
		}
		return temp;
	}

}