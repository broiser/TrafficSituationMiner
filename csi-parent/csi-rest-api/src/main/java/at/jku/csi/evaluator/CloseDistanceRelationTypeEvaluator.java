package at.jku.csi.evaluator;

import at.jku.csi.cdi.Service;

@Service
public class CloseDistanceRelationTypeEvaluator extends DistanceRelationTypeEvaluator {

	public static final String CLOSE_DISTANCE_RELATION = "CLOSE";

	@Override
	protected boolean evaluate(int distance) {
		return 3 < distance && distance <= 6;
	}
}
