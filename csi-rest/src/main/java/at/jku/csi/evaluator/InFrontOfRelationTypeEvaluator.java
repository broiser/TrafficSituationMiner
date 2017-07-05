package at.jku.csi.evaluator;

import at.jku.csi.cdi.Service;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class InFrontOfRelationTypeEvaluator implements Evaluator {

	public static final String IN_FRONT_OF_RELATION = "IN_FRONT_OF";

	private enum DIRECTION {
		ASC {
			@Override
			public boolean apply(int x1, int x2) {
				return x1 < x2;
			}
		},
		DESC {

			@Override
			public boolean apply(int x1, int x2) {
				return x1 > x2;
			}
		};

		public abstract boolean apply(int x1, int x2);
	}

	@Override
	public boolean evaluate(AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		if (left.getRoad_code() == null || !(left.getRoad_code().equals(right.getRoad_code()))) {
			return false;
		}
		return isInFrontOf(determineDirection(left, right), left.getBeginmeter(), left.getEndmeter(),
				right.getBeginmeter(), right.getEndmeter());
	}

	private DIRECTION determineDirection(AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		return left.getRoad_direction() == 1 && right.getRoad_direction() == 1 ? DIRECTION.ASC : DIRECTION.DESC;
	}

	private boolean isInFrontOf(DIRECTION direction, int leftBeginmeter, int leftEndmeter, int rightBeginmeter,
			int rightEndmeter) {
		boolean validLeft = direction.apply(leftBeginmeter, leftEndmeter);
		boolean validRight = direction.apply(rightBeginmeter, rightEndmeter);
		return validLeft && validRight && direction.apply(leftEndmeter, rightBeginmeter);
	}
}
