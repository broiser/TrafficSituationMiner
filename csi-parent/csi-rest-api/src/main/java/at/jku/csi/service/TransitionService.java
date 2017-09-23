package at.jku.csi.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.TransitionDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.Transition;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType;

@Service
public class TransitionService implements Serializable {

	@Inject
	private TransitionDao transitionDao;
	@Inject
	private TransitionTypeService transitionTypeService;

	@Transactional
	public Transition createTransition(StateInstance previousStateInstance, StateInstance nextStateInstance) {
		return transitionDao.save(buildTransition(previousStateInstance, nextStateInstance));
	}

	private Transition buildTransition(StateInstance previousStateInstance, StateInstance nextStateInstance) {
		Transition transition = new Transition();
		transition.setNextStateInstanceStateinstance(nextStateInstance);
		transition.setPreviousStateInstanceStateinstance(previousStateInstance);
		transition.setTransitionType(
				findOrCreateTransitionType(previousStateInstance.getName(), nextStateInstance.getName()));
		return transition;
	}

	private TransitionType findOrCreateTransitionType(String previousName, String nextName) {
		TransitionType transitionType = transitionTypeService.findTransitionType(previousName, nextName);
		if (transitionType != null) {
			return transitionType;
		}
		return transitionTypeService.createTransitionType(previousName, nextName);
	}

}
