package at.jku.csi.comparator;

import java.io.Serializable;
import java.util.Comparator;

import at.jku.csi.cdi.Service;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;

@Service
public class StateInstanceComparator implements Comparator<StateInstance>, Serializable {

	@Override
	public int compare(StateInstance o1, StateInstance o2) {
		return o1.getBeginTime().compareTo(o2.getBeginTime());
	}

}
