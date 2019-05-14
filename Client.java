import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import lenz.htw.sawhian.Move;
import lenz.htw.sawhian.net.NetworkClient;

public class Client {

	// TODO: for estimation we need to check how often we can jump @calculating time
	// TODO: if jump ends at finish
	//

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client c = new Client();
	}

	int playerNumber = 0;
	int timeLimit = 0;
	int latency = 0;



	int[][] board = new int[7][7];

	public Client() throws IOException, UnknownHostException {

		Arrays.stream(board).forEach(a -> Arrays.fill(a, -1));
		
		IntegrateMove ig = new IntegrateMove();
		CalculateMoves cv = new CalculateMoves();
		// System.out.println(Arrays.deepToString(board));

		BufferedImage logo = ImageIO.read(new File("src/logo.png"));

		// port: 22135
		NetworkClient client = new NetworkClient("localhost", "Team_Daniel", logo);

		playerNumber = client.getMyPlayerNumber();
		timeLimit = client.getTimeLimitInSeconds();
		latency = client.getExpectedNetworkLatencyInMilliseconds();

		System.out.println("Player Number:  " + playerNumber + " Latency in ms: " + latency);

		while (true) {
			Move move = client.receiveMove();

			if (move == null) {
				//TODO: 
				List<PossibleMove> pm = cv.calculateBestMove(playerNumber, board, ig);
				//int result = alphaBeta(0, 0, true, values, -10000, 10000);
				
				
				
				
				Move m = new Move(playerNumber, pm.get(0).x, pm.get(0).y);
				//System.out.println("My Move is: x: " + pm.x + " y: " + pm.y);
				client.sendMove(m);
			} else {
				System.out.println("Integrate Player " + move + " in the Playfield");
				ig.integrateMove(move,board, true);
			}
		}
	}	
}