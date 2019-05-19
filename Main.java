import java.io.IOException;
import java.net.UnknownHostException;
import lenz.htw.sawhian.Server;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		int evolutions = 0;


		do {
			IntegrateMove ig = new IntegrateMove();
			FitnessFunction ff = new FitnessFunction(ig);
			WeightFunction wf = new WeightFunction(true,ff);
			
			Thread t1 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1000);
						Client c1 = new Client("KI", true, wf, ig);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread t2 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1500);
						Client c2 = new Client("Bot 1", false, wf, null);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread t3 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2000);
						Client c3 = new Client("Bot 2", false, wf, null);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread t4 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2500);
						Client c4 = new Client("Bot 3", false, wf, null);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			t1.start();
			t2.start();
			t3.start();
			t4.start();
			int winner = Server.runOnceAndReturnTheWinner(4);
			System.out.println("winner: " + winner);
			wf.evaluateBoard(winner);
			
			evolutions++;
		} while (evolutions < 10);
	}
}
