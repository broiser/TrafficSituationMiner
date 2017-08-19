package at.jku.csi.dao;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.Transition;

@Dao
public class TransitionDao extends AbstractDao<Transition>{

	public TransitionDao() {
		super(Transition.class);
	}
}
