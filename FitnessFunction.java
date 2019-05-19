
public class FitnessFunction{
	
	int playerNumber;
	IntegrateMove ig;
	
	public FitnessFunction(IntegrateMove ig) {
		this.ig = ig;
		
}
	public int evalutateGame(int winner) {
		int fitnessValue = 1;

		if (playerNumber == 0) {
			fitnessValue = ig.pointsP1 * 3 - (ig.pointsP2 + ig.pointsP3 + ig.pointsP4);
			System.out.println("player number 0 points: "  + ig.pointsP1 + " fintess value: " + fitnessValue );
		}
		if (playerNumber == 1) {
			fitnessValue = ig.pointsP2 * 3 - (ig.pointsP1 + ig.pointsP3 + ig.pointsP4);
			System.out.println("player number 1 points: "  + ig.pointsP2 + " fintess value: " + fitnessValue );
		}
		if (playerNumber == 2) {
			fitnessValue = ig.pointsP3 * 3 - (ig.pointsP2 + ig.pointsP1 + ig.pointsP4);
			System.out.println("player number 2 points: "  + ig.pointsP3 + " fintess value: " + fitnessValue );
		}
		if (playerNumber == 3) {
			fitnessValue = ig.pointsP4 * 3 - (ig.pointsP2 + ig.pointsP3 + ig.pointsP1);
			System.out.println("player number 3 points: "  + ig.pointsP4 + " fintess value: " + fitnessValue );
		}
		return fitnessValue;
	}
}
