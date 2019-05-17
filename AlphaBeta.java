import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import lenz.htw.sawhian.Move;

public class AlphaBeta {

	IntegrateMove ig;

	public AlphaBeta(int playerNumber, IntegrateMove ig, int calculationDepth, boolean useLearning) {
		myPlayerNumber = playerNumber;
		this.ig = ig;
		topLayer = calculationDepth;
		this.useLearning = useLearning;
	}

	Move savedMoveMax = null;
	int myPlayerNumber;
	int maxDepth = 0;
	int topLayer;
	boolean useLearning;

	// TODO: implement logic if player gets kicked ???
	// TODO: alpha beta cut only after max and 3x min

	WeightFunction wf = new WeightFunction(useLearning);
	CalculateMoves cv = new CalculateMoves();
	FitnessFunction ff = new FitnessFunction();

	public double max(int[][] board, int depth, IntegrateMove ig, int playerNumber, Move last_move)
			throws IOException {

		int[][] boardToEvaluate = copyBoard(board);
		double maxValue = -10000;
		int possibleMoves = 0;

		List<PossibleMove> pm = cv.getPossibleMoves(playerNumber % 4, board, ig);

		if (depth == maxDepth || pm.size() == 0) {
			if (last_move == null) {
				// no moves left, we either won or are blocked
				if (myPlayerNumber == 0) {
					int evaluation = ff.evalutateGame(ig, myPlayerNumber);
					String text = String.valueOf(evaluation) + " " + wf.c1 + " " + wf.c2 + " " + wf.c3 + " " + wf.c4
							+ " " + wf.c5;
					System.out.println(text);
					
				    FileWriter fw = new FileWriter("src/results.txt", true);
				    fw.write(text);
				    fw.write("\n");
				    fw.close();
					
					return 0;
				}
			}
			if (last_move != null) {
				return wf.evaluateMove(last_move, boardToEvaluate, ig);
			}

		}

		if (last_move != null) {
			ig.integrateMove(last_move, board, false);
		}

		while (possibleMoves < pm.size()) {
			int[][] board_temp = copyBoard(board);

			Move temp_move = new Move(playerNumber % 4, pm.get(possibleMoves).x, pm.get(possibleMoves).y);
			int newPlayerNumber = (playerNumber + 1) % 4;

			double value = min(board_temp, depth - 1, ig, newPlayerNumber, temp_move);

			possibleMoves++;

			if (value > maxValue) {
				maxValue = value;
				if (depth == topLayer)
					savedMoveMax = temp_move;
			}
		}
		return maxValue;
	}

	public double min(int[][] board, int depth, IntegrateMove ig, int playerNumber, Move last_move)
			throws IOException {

		int[][] boardToEvaluate = copyBoard(board);
		int possibleMoves = 0;
		double minValue = 10000;
		double maxValue = -10000;

		List<PossibleMove> pm = cv.getPossibleMoves(playerNumber % 4, board, ig);

		if (depth == maxDepth || pm.size() == 0) {
			return wf.evaluateMove(last_move, boardToEvaluate, ig);
		}

		ig.integrateMove(last_move, board, false);

		while (possibleMoves < pm.size()) {
			int[][] board_temp = copyBoard(board);

			Move temp_move = new Move(playerNumber % 4, pm.get(possibleMoves).x, pm.get(possibleMoves).y);

			int newPlayerNumber = (playerNumber + 1) % 4;

			double value;

			if (newPlayerNumber == myPlayerNumber) {
				value = max(board_temp, depth - 1, ig, newPlayerNumber, temp_move);

				if (value < minValue)
					minValue = value;

			} else {
				value = min(board_temp, depth - 1, ig, newPlayerNumber, temp_move);

				if (value > maxValue)
					minValue = value;
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