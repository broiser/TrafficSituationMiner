package at.jku.csi.evaluator;

import at.jku.csi.cdi.Service;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class ExactRelationTypeEvaluator implements Evaluator {

	public static final String EXACT_RELATION = "EXACT";

	@Override
	public boolean evaluate(AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		if (left.getRoad_code() == null || !(left.getRoad_code().equals(right.getRoad_code()))) {
			return false;
		}

		return left.getBeginmeter().intValue() == right.getBeginmeter().intValue()
				&& left.getEndmeter().intValue() == right.getEndmeter().intValue();
	}
}
