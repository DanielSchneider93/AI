import java.io.IOException;
import java.net.UnknownHostException;
import lenz.htw.sawhian.Server;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		int evolutions = 0;

		do {
			Thread t1 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1000);
						Client c1 = new Client("KI", true);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread t2 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1500);
						Client c2 = new Client("Bot 1", false);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread t3 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2000);
						Client c3 = new Client("Bot 2", false);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread t4 = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2500);
						Client c4 = new Client("Bot 3", false);
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			});

			t1.start();
			t2.start();
			t3.start();
			t4.start();
			System.out.println("winner: " + Server.runOnceAndReturnTheWinner(1000));
			evolutions++;
		} while (evolutions < 3);
	}
}
