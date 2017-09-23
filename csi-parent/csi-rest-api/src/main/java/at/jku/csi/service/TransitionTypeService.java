package at.jku.csi.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.TransitionTypeDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType;

@Service
public class TransitionTypeService implements Serializable {

	@Inject
	private TransitionTypeDao transitionTypeDao;

	@Inject
	private SituationStateTypeService situationStateTypeService;

	public TransitionType findTransitionType(String previousName, String nextName) {
		String transitionSequence = buildTransitionSequence(previousName, nextName);
		return transitionTypeDao.findTransitionType(transitionSequence);
	}

	@Transactional
	public TransitionType createTransitionType(String previousName, String nextName) {
		return transitionTypeDao.save(buildTransitionType(previousName, nextName));
	}

	private TransitionType buildTransitionType(String previousName, String nextName) {
		TransitionType transitionType = new TransitionType();
		transitionType.setTransitionSequence(buildTransitionSequence(previousName, nextName));
		transitionType.setFromSituationStateType(findOrCreateSituationStateType(previousName));
		transitionType.setToSituationStateType(findOrCreateSituationStateType(nextName));
		return transitionType;
	}

	private SituationStateType findOrCreateSituationStateType(String name) {
		SituationStateType situationStateType = situationStateTypeService.findSituationStateType(name);
		if (situationStateType != null) {
			return situationStateType;
		}
		return situationStateTypeService.createSituationStateType(name);
	}

	private String buildTransitionSequence(String previousName, String nextName) {
		return String.format("%s -> %s", previousName, nextName);
	}

}
