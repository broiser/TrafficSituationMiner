package at.jku.csi.service;

import java.io.Serializable;

import javax.inject.Inject;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.RelationTypeDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType;

@Service
public class RelationTypeService implements Serializable {
	
	@Inject
	private RelationTypeDao relationTypeDao;

	public RelationType createRelationType(String name){
		return relationTypeDao.save(buildRelationType(name));
	}
	
	private RelationType buildRelationType(String name) {
		RelationType relationType = new RelationType();
		relationType.setName(name);
		return relationType ;
	}

	public RelationType findRelationType(String name) {
		return relationTypeDao.findRelationType(name);
	}

}
