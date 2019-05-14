import java.util.ArrayList;
import java.util.List;

public class CalculationResult {

	ArrayList<Integer> values = new ArrayList<Integer>();
	List<PossibleMove> moves =  new ArrayList<PossibleMove>();
	
	public CalculationResult(ArrayList<Integer> values, List<PossibleMove> moves) {
		super();
		this.values = values;
		this.moves = moves;
	}
}
