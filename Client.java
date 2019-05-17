import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import javax.imageio.ImageIO;

import lenz.htw.sawhian.Move;
import lenz.htw.sawhian.Server;
import lenz.htw.sawhian.net.NetworkClient;

public class Client {

	//public static void main(String[] args) throws UnknownHostException, IOException {
	//	Client c1 = new Client("KI", false);
	//}
	
	int calculationDepth = 8;
	int playerNumber = 0;
	int timeLimit = 0;
	int latency = 0;
	int[][] board = new int[7][7];

	public Client(String name, boolean useLearning) throws IOException, UnknownHostException {

		Arrays.stream(board).forEach(a -> Arrays.fill(a, -1));
		IntegrateMove ig = new IntegrateMove();
		BufferedImage logo = ImageIO.read(new File("src/logo.png"));
		// port: 22135
		NetworkClient client = new NetworkClient("localhost", name, logo);

		playerNumber = client.getMyPlayerNumber();
		timeLimit = client.getTimeLimitInSeconds();
		latency = client.getExpectedNetworkLatencyInMilliseconds();

		AlphaBeta ab = new AlphaBeta(playerNumber, ig, calculationDepth, useLearning);

		System.out.println("Player Number:  " + playerNumber + " Latency in ms: " + latency);

		while (true) {
			Move move = client.receiveMove();

			if (move == null) {

				long startTime = System.currentTimeMillis();

				ab.max(board, calculationDepth, ig, playerNumber, null);

				long endTime = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				System.out.println("Result Move: Player: " + ab.getSavedMoveMax() + " in Time: " + totalTime + "ms");

				Move resultMove = ab.getSavedMoveMax();
				client.sendMove(resultMove);

			} else {
				ig.integrateMove(move, board, true);
			}
		}
	}
}