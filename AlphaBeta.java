import java.util.List;

import lenz.htw.sawhian.Move;

public class AlphaBeta {
	
	//allow min to call max
	//Variable depth
	//player list for playernumber min max
	
	//alpha beta cut only after max and 3x min

	CalculateMoves cv = new CalculateMoves();

	Move savedMoveMax = null;
	Move saveMoveMin = null;
	
	public int max(int[][] board, int depth, IntegrateMove ig, int playerNumber) {

		if (depth == 0) {
			return ig.evaluateMove(savedMoveMax, board);
		}
		
		int maxValue = -10000;
		List<PossibleMove> pm = cv.getPossibleMoves(playerNumber, board, ig);
		int possibleMoves = pm.size();
		
		if(pm.isEmpty()) {
			return ig.evaluateMove(savedMoveMax, board);
		}

		while (possibleMoves > 0) {
			
			int[][] board_copy = copyBoard(board);
			Move temp_move = new Move(playerNumber, pm.get(possibleMoves).x, pm.get(possibleMoves).y);
			
			ig.integrateMove(temp_move, board_copy);
			
			//nächster spieler berechnung und wenn playernumber = ich dann max else min
			
			int value = min(board_copy, depth - 1, ig, playerNumber+1);
			
			board_copy = board;
			possibleMoves--;
			
			if (value > maxValue) {
				maxValue = value;

				if (depth == 4)
					savedMoveMax = temp_move;
			}
		}
		return maxValue;
	}
	
	public int min(int[][] board, int depth, IntegrateMove ig, int playerNumber) {
		
		if (depth == 0) {
			return ig.evaluateMove(saveMoveMin, board);
		}
		
		int minValue = 10000;
		List<PossibleMove> pm = cv.getPossibleMoves(playerNumber, board, ig);
		int possibleMoves = pm.size();
		
		if(pm.isEmpty()) {
			return ig.evaluateMove(saveMoveMin, board);
		}

		while (possibleMoves > 0) {
			
			int[][] board_copy = copyBoard(board);
			Move temp_move = new Move(playerNumber, pm.get(possibleMoves).x, pm.get(possibleMoves).y);
			
			ig.integrateMove(temp_move, board_copy);
			
			int value = min(board_copy, depth - 1, ig, playerNumber+1);
			
			board_copy = board;
			possibleMoves--;
			
			if (value < minValue) {
				minValue = value;

				if (depth == 4)
					saveMoveMin = temp_move;
			}
		}
		return minValue;
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