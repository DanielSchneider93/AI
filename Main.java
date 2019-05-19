import java.io.IOException;
import java.net.UnknownHostException;
import lenz.htw.sawhian.Server;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// Repetitions for learning
		int evolutions = 10;
		
		// active bool to let the program learn, else it plays with 1 KI with the
		// learned values and 3 "Bots" with standart values
		boolean actuallyLearn = false;
		
		//depth for the play tree to evaluate alpha beta
		int calculationDepth = 8;

		//Main Loop
		do {
			IntegrateMove ig = new IntegrateMove();
			
			FitnessFunction ff = new FitnessFunction(ig);
			//Weightfunction for KI
			WeightFunction wf = new WeightFunction(true, ff, actuallyLearn);
			//Weightfunction for Bots
			WeightFunction wf1 = new WeightFunction(false, ff, false);

			Thread t1 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1000);	
						Client c1 = new Client("KI", true, wf, ig, calculationDepth);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread t2 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1500);
						Client c2 = new Client("Bot 1", false, wf1, null, calculationDepth);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread t3 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2000);
						Client c3 = new Client("Bot 2", false, wf1, null, calculationDepth);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread t4 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2500);
						Client c4 = new Client("Bot 3", false, wf1, null, calculationDepth);
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

			if (actuallyLearn == true)
				//evaluate board state and save result if better to file
				wf.evaluateBoard(winner);

			evolutions++;
		} while (0 < evolutions);
	}
}
