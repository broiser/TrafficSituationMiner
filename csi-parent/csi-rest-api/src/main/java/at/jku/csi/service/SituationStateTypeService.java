package at.jku.csi.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.SituationStateTypeDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;

@Service
public class SituationStateTypeService implements Serializable {

	@Inject
	private SituationStateTypeDao situationStateTypeDao;

	public SituationStateType findSituationStateType(String name) {
		return situationStateTypeDao.findSituationStateType(name);
	}

	@Transactional
	public SituationStateType createSituationStateType(String name) {
		return situationStateTypeDao.save(buildSituationStateType(name));
	}

	private SituationStateType buildSituationStateType(String name) {
		SituationStateType situationStateType = new SituationStateType();
		situationStateType.setName(name);
		situationStateType.setType(determineType(name));
		return situationStateType;
	}

	private String determineType(String name) {
		return name.substring(1, name.length() - 1);
	}
}