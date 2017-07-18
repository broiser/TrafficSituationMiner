package at.jku.csi.evaluator;

import at.jku.csi.cdi.Service;

@Service
public class FarDistanceRelationTypeEvaluator extends DistanceRelationTypeEvaluator {

	public static final String FAR_DISTANCE_RELATION = "FAR";

	@Override
	protected boolean evaluate(int distance) {
		return 8 < distance;
	}
}
