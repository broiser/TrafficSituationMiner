package at.jku.csi.evaluator;

import at.jku.csi.cdi.Service;

@Service
public class VeryCloseDistanceRelationTypeEvaluator extends DistanceRelationTypeEvaluator {

	public static final String VERY_CLOSE_DISTANCE_RELATION = "VERY_CLOSE";

	@Override
	protected boolean evaluate(int distance) {
		return 0 < distance && distance <= 3;
	}
}
