package at.jku.csi.evaluator;

import java.io.Serializable;

import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

public interface Evaluator extends Serializable {

	public enum Operator {
		LT {
			@Override
			public boolean apply(int x1, int x2) {
				return x1 < x2;
			}
		},
		LTE {
			@Override
			public boolean apply(int x1, int x2) {
				return x1 <= x2;
			}
		},
		GT {
			@Override
			public boolean apply(int x1, int x2) {
				return x1 > x2;
			}
		},
		GTE {

			@Override
			public boolean apply(int x1, int x2) {
				return x1 >= x2;
			}
		},
		EQ {

			@Override
			public boolean apply(int x1, int x2) {
				return x1 == x2;
			}
		};

		public abstract boolean apply(int x1, int x2);
	}

	boolean evaluate(AsfinagTrafficmessage left, AsfinagTrafficmessage right);
}
