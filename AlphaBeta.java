import java.io.IOException;
import java.util.List;
import lenz.htw.sawhian.Move;

public class AlphaBeta {

	IntegrateMove ig;
	WeightFunction wf;

	public AlphaBeta(int playerNumber, IntegrateMove ig, int calculationDepth, boolean useLearning, WeightFunction wf) {
		myPlayerNumber = playerNumber;
		this.ig = ig;
		topLayer = calculationDepth;
		this.useLearning = useLearning;
		this.wf = wf;
	}

	Move savedMoveMax = null;
	int myPlayerNumber;
	int maxDepth = 0;
	int topLayer;
	boolean useLearning;

	CalculateMoves cv = new CalculateMoves();

	public double max(int[][] board, int depth, IntegrateMove ig, int playerNumber, Move last_move, int alpha, int beta)
			throws IOException {

		int[][] boardToEvaluate = copyBoard(board);
		double maxValue = alpha;
		int possibleMoves = 0;

		if (depth == maxDepth) {
			if (useLearning) {
				return wf.evaluateMove(last_move, boardToEvaluate, ig, wf.c1_new, wf.c2_new, wf.c3_new, wf.c4_new,
						wf.c5_new);
			} else {
				return wf.evaluateMove(last_move, boardToEvaluate, ig, wf.c1, wf.c2, wf.c3, wf.c4,wf.c5);
			}
			
		}

		List<PossibleMove> pm = cv.getPossibleMoves(playerNumber % 4, board, ig);

		if (last_move != null) {
			ig.integrateMove(last_move, board, false);
		}

		while (possibleMoves < pm.size()) {
			int[][] board_temp = copyBoard(board);

			Move temp_move = new Move(playerNumber % 4, pm.get(possibleMoves).x, pm.get(possibleMoves).y);
			int newPlayerNumber = (playerNumber + 1) % 4;

			double value = min(board_temp, depth - 1, ig, newPlayerNumber, temp_move, (int) maxValue, beta);

			possibleMoves++;

			if (value > maxValue) {
				maxValue = value;
			}
			if (depth == topLayer)
				savedMoveMax = temp_move;

			if (maxValue >= beta)
				break;

		}
		return maxValue;
	}

	public double min(int[][] board, int depth, IntegrateMove ig, int playerNumber, Move last_move, int alpha, int beta)
			throws IOException {

		int[][] boardToEvaluate = copyBoard(board);
		int possibleMoves = 0;
		double minValue = beta;
		double maxValue = alpha;

		int minCallCount = 1;

		List<PossibleMove> pm = cv.getPossibleMoves(playerNumber % 4, board, ig);

		if (depth == maxDepth || pm.size() == 0) {
			if (useLearning) {
				return wf.evaluateMove(last_move, boardToEvaluate, ig, wf.c1_new, wf.c2_new, wf.c3_new, wf.c4_new,
						wf.c5_new);
			} else {
				return wf.evaluateMove(last_move, boardToEvaluate, ig, wf.c1, wf.c2, wf.c3, wf.c4,wf.c5);
			}
		}

		ig.integrateMove(last_move, board, false);

		while (possibleMoves < pm.size()) {
			int[][] board_temp = copyBoard(board);

			Move temp_move = new Move(playerNumber % 4, pm.get(possibleMoves).x, pm.get(possibleMoves).y);

			int newPlayerNumber = (playerNumber + 1) % 4;

			double value;

			if (newPlayerNumber == myPlayerNumber) {
				value = max(board_temp, depth - 1, ig, newPlayerNumber, temp_move, alpha, (int) minValue);

				if (value < minValue)
					minValue = value;
				if (minValue <= alpha)
					break;

			} else {
				value = min(board_temp, depth - 1, ig, newPlayerNumber, temp_move, (int) maxValue, beta);
				minCallCount++;
				if (value > maxValue)
					minValue = value;
				if (minCallCount == 3) {
					if (maxValue >= beta)
						break;
				}
			}
			possibleMoves++;
		}

		return minValue;
	}

	public Move getSavedMoveMax() {
		return savedMoveMax;
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