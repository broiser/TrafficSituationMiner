package at.jku.csi.dao;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;

@Dao
public class StateInstanceDao extends AbstractDao<StateInstance> {

	public StateInstance findByid(int id) {
		return entityManager.find(StateInstance.class, id);
	}

}
