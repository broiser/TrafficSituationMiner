package at.jku.csi.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.ObjectTypeDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType;

@Service
public class ObjectTypeService implements Serializable {

	@Inject
	private ObjectTypeDao objectTypeDao;
	@Inject
	private TrafficTypeService trafficTypeService;

	public ObjectType findObjectType(String alias) {
		return objectTypeDao.findObjectType(alias);
	}

	@Transactional
	public ObjectType createObjectType(String alias) {
		return objectTypeDao.save(buildObjectType(alias));
	}

	private ObjectType buildObjectType(String alias) {
		ObjectType objectType = new ObjectType();
		objectType.setAlias(alias);
		objectType.setRelationTypeL(new RelationType());
		objectType.getRelationTypeL().setObjectTypeL(objectType);
		objectType.getRelationTypeL().setObjectTypeR(objectType);
		objectType.setRelationTypeR(new RelationType());
		objectType.getRelationTypeR().setObjectTypeL(objectType);
		objectType.getRelationTypeR().setObjectTypeR(objectType);
		objectType.setTrafficType(trafficTypeService.findTrafficeType(alias));
		return objectType;
	}
}
