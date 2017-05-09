package at.jku.csi.service;

import java.io.Serializable;

import at.jku.csi.cdi.Service;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.DiscreteStateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;

@Service
public class StateDurationCalculator implements Serializable {

	private static final double MILLIS = 1000;
	private static final double MINUTES = 60 * MILLIS;

	public double calculateDuration(StateInstance firstStateInstance, StateInstance lastStateInstance) {
		return (lastStateInstance.getBeginTime().getTime() - firstStateInstance.getBeginTime().getTime()) / MINUTES;
	}
	
	public double calculateDuration(DiscreteStateInstance firstStateInstance, DiscreteStateInstance lastStateInstance) {
		return (lastStateInstance.getBeginTime().getTime() - firstStateInstance.getBeginTime().getTime()) / MINUTES;
	}

}
