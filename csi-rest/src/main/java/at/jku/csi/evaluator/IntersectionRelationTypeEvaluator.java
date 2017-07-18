package at.jku.csi.evaluator;

import javax.inject.Inject;

import at.jku.csi.cdi.Service;
import at.jku.csi.determiner.RoadDirectionDeterminer;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class IntersectionRelationTypeEvaluator implements Evaluator {

	public static final String INTERSECTION_RELATION = "INTERSECTION";

	@Inject
	private RoadDirectionDeterminer roadDirectionDeterminer;

	@Override
	public boolean evaluate(AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		if (left.getRoad_code() == null || !(left.getRoad_code().equals(right.getRoad_code()))) {
			return false;
		}

		switch (roadDirectionDeterminer.determineDirection(left, right)) {
		case ASC:
			return isOverlapping(Operator.LTE, left, right);
		case DESC:
			return isOverlapping(Operator.GTE, left, right);
		default:
			return false;
		}
	}

	private boolean isOverlapping(Operator operator, AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		boolean validLeft = operator.apply(left.getBeginmeter(), left.getEndmeter());
		boolean validRight = operator.apply(right.getBeginmeter(), right.getEndmeter());
		boolean validBetween = isBetween(operator, left, right);
		boolean validLeftOverlapping = isLeftOverlapping(operator, left, right);
		boolean validRightOverlapping = isRightOverlapping(operator, left, right);
		return validLeft && validRight && (validBetween || validLeftOverlapping || validRightOverlapping);
	}

	private boolean isBetween(Operator operator, AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		return operator.apply(left.getBeginmeter(), right.getBeginmeter())
				&& operator.apply(right.getBeginmeter(), left.getEndmeter());
	}

	private boolean isRightOverlapping(Operator operator, AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		return operator.apply(left.getBeginmeter(), right.getEndmeter())
				&& operator.apply(right.getEndmeter(), left.getEndmeter());
	}

	private boolean isLeftOverlapping(Operator operator, AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		return operator.apply(left.getBeginmeter(), right.getBeginmeter())
				&& operator.apply(right.getEndmeter(), left.getEndmeter());
	}
}
