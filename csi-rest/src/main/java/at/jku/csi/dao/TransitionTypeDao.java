package at.jku.csi.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType;

@Dao
public class TransitionTypeDao extends AbstractDao<TransitionType> {

	private static final String TRANSITION_SEQUENCE = "transitionSequence";

	public TransitionTypeDao() {
		super(TransitionType.class);
	}

	public TransitionType findTransitionType(String transitionSequence) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TransitionType> query = builder.createQuery(TransitionType.class);
		Root<TransitionType> root = query.from(TransitionType.class);
		Predicate predicate = builder.equal(root.get(TRANSITION_SEQUENCE), transitionSequence);
		return getSingleResult(query.select(root).where(predicate));
	}
}
