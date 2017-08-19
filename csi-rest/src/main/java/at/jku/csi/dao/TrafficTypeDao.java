package at.jku.csi.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TrafficType;

public class TrafficTypeDao extends AbstractDao<TrafficType> {

	private static final String TYPE = "type";

	public TrafficTypeDao() {
		super(TrafficType.class);
	}

	public TrafficType findTrafficType(String type) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TrafficType> query = builder.createQuery(TrafficType.class);
		Root<TrafficType> root = query.from(TrafficType.class);
		Predicate predicate = builder.equal(root.get(TYPE), type);
		return getSingleResult(query.select(root).where(predicate));
	}
}
