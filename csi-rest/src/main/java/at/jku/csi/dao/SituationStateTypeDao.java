package at.jku.csi.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;

@Dao
public class SituationStateTypeDao extends AbstractDao<SituationStateType> {

	private static final String NAME = "name";

	public SituationStateType findSituationStateType(String name) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SituationStateType> query = builder.createQuery(SituationStateType.class);
		Root<SituationStateType> root = query.from(SituationStateType.class);
		Predicate predicate = builder.equal(root.get(NAME), name);
		return getSingleResult(query.select(root).where(predicate));
	}
}
