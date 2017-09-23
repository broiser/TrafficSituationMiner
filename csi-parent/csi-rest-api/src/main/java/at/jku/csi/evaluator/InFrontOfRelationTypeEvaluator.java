package at.jku.csi.evaluator;

import javax.inject.Inject;

import at.jku.csi.cdi.Service;
import at.jku.csi.determiner.RoadDirectionDeterminer;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class InFrontOfRelationTypeEvaluator implements Evaluator {

	public static final String IN_FRONT_OF_RELATION = "IN_FRONT_OF";

	@Inject
	private RoadDirectionDeterminer roadDirectionDeterminer;

	@Override
	public boolean evaluate(AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		if (left.getRoad_code() == null || !(left.getRoad_code().equals(right.getRoad_code()))) {
			return false;
		}
		switch (roadDirectionDeterminer.determineDirection(left, right)) {
		case ASC:
			return evalute(Operator.LT, left, right);
		case DESC:
			return evalute(Operator.GT, left, right);
		default:
			return false;
		}
	}

	private boolean evalute(Operator operator, AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		boolean validRight = operator.apply(right.getBeginmeter(), right.getEndmeter());
		boolean validLeft = operator.apply(left.getBeginmeter(), left.getEndmeter());
		return validLeft && validRight && operator.apply(left.getEndmeter(), right.getBeginmeter());
	}
}
