package at.jku.csi.service;

import java.io.Serializable;
import java.util.HashSet;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.SituationStateTypeDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;

@Service
public class SituationStateTypeService implements Serializable {

	@Inject
	private ObjectTypeService objectTypeService;
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
		situationStateType.setObjectType(new HashSet<ObjectType>());
		// for (String objectType : type.split(",")) {
		// situationStateType.getObjectType().add(createObjectType(objectType));
		// }
		return situationStateType;
	}

	private String determineType(String name) {
		return name.substring(1, name.length() - 1);
	}

	// private ObjectType createObjectType(String alias) {
	// return objectTypeService.createObjectType(alias);
	// }
}