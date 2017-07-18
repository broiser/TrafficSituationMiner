package at.jku.csi.evaluator;

import javax.inject.Inject;

import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

public abstract class DistanceRelationTypeEvaluator implements Evaluator {

	private static final int KILOMETER = 1000;

	@Inject
	private InFrontOfRelationTypeEvaluator inFrontOfRelationTypeEvaluator;

	protected abstract boolean evaluate(int distance);

	@Override
	public boolean evaluate(AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		return evaluate(calculateDistance(left, right));
	}

	private int calculateDistance(AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		int distance = 0;
		if (inFrontOfRelationTypeEvaluator.evaluate(left, right)) {
			distance = left.getEndmeter().intValue() + right.getBeginmeter().intValue();
		} else if (inFrontOfRelationTypeEvaluator.evaluate(right, left)) {
			distance = right.getEndmeter().intValue() - left.getEndmeter().intValue();
		}
		return distance / KILOMETER;
	}
}
