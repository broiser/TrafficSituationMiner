package at.jku.csi.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType;

@Dao
public class ObjectTypeDao extends AbstractDao<ObjectType> {

	private static final String ALIAS = "alias";
	
	public ObjectType findObjectType(String alias) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ObjectType> query = builder.createQuery(ObjectType.class);
		Root<ObjectType> root = query.from(ObjectType.class);
		Predicate predicate = builder.equal(root.get(ALIAS), alias);
		return getSingleResult(query.select(root).where(predicate));
	}
}
