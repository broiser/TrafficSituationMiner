package at.jku.csi.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType;

@Dao
public class RelationTypeDao extends AbstractDao<RelationType> {

	private static final String NAME = "name";

	public RelationType findRelationType(String name) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RelationType> query = builder.createQuery(RelationType.class);
		Root<RelationType> root = query.from(RelationType.class);
		return getSingleResult(query.where(builder.equal(root.get(NAME), name)));
	}

}
