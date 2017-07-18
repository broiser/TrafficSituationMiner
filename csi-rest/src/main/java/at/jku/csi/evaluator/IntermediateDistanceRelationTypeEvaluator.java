package at.jku.csi.evaluator;

import at.jku.csi.cdi.Service;

@Service
public class IntermediateDistanceRelationTypeEvaluator extends DistanceRelationTypeEvaluator {

	public static final String INTERMEDIATE_DISTANCE_RELATION = "INTERMEDIATE";

	@Override
	protected boolean evaluate(int distance) {
		return 6 < distance && distance <= 8;
	}
}