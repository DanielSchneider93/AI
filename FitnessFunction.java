
public class FitnessFunction {
	public int evalutateGame(IntegrateMove ig, int playerNumber) {
		int fitnessValue = 0;

		if (playerNumber == 0) {
			fitnessValue = ig.pointsP1 * 3 - (ig.pointsP2 + ig.pointsP3 + ig.pointsP4);
		}
		if (playerNumber == 1) {
			fitnessValue = ig.pointsP2 * 3 - (ig.pointsP1 + ig.pointsP3 + ig.pointsP4);
		}
		if (playerNumber == 2) {
			fitnessValue = ig.pointsP3 * 3 - (ig.pointsP2 + ig.pointsP1 + ig.pointsP4);
		}
		if (playerNumber == 3) {
			fitnessValue = ig.pointsP4 * 3 - (ig.pointsP2 + ig.pointsP3 + ig.pointsP1);
		}
		return fitnessValue;
	}
}
