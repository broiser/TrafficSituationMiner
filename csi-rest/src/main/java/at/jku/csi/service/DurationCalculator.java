package at.jku.csi.service;

import java.io.Serializable;

import at.jku.csi.cdi.Service;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.DiscreteStateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class DurationCalculator implements Serializable {

	private static final double MILLIS = 1000;
	private static final double MINUTES = 60 * MILLIS;

	public double calculateDuration(StateInstance firstStateInstance, StateInstance lastStateInstance) {
		return noramlize(lastStateInstance.getBeginTime().getTime() - firstStateInstance.getBeginTime().getTime());
	}

	public double calculateDuration(DiscreteStateInstance firstStateInstance, DiscreteStateInstance lastStateInstance) {
		return noramlize(lastStateInstance.getBeginTime().getTime() - firstStateInstance.getBeginTime().getTime());
	}

	public double calculateDuration(AsfinagTrafficmessage firstTrafficmessage,
			AsfinagTrafficmessage lastTrafficmessage) {
		return noramlize(lastTrafficmessage.getBegintime().getTime() - firstTrafficmessage.getBegintime().getTime());
	}

	private double noramlize(long time) {
		return time / MINUTES;
	}

}
