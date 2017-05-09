package at.jku.csi.dao;

import static org.hibernate.criterion.Projections.groupProperty;
import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Dao
public class AsfinagTrafficmessageDao extends AbstractDao<AsfinagTrafficmessage> {
	private static final String DATEX_PHR = "datex_phr";
	private static final String BEGINTIME = "begintime";
	private static final String SITUATION_ID = "situation_id";
	private static final String MESSAGETEXT = "messagetext";
	private static final String VMS_ID = "vms_id";

	public ScrollableResults findScrollableSituationIds(String phr) {
		Criteria criteria = createCriteria(AsfinagTrafficmessage.class);
		criteria = criteria.add(eq(DATEX_PHR, phr));
		criteria = criteria.setProjection(groupProperty(SITUATION_ID));
		return getScrollableResults(criteria);
	}

	public List<AsfinagTrafficmessage> findAsfinagTrafficsByVmsId(int vmsId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AsfinagTrafficmessage> query = builder.createQuery(AsfinagTrafficmessage.class);
		Root<AsfinagTrafficmessage> root = query.from(AsfinagTrafficmessage.class);
		 query.where(builder.equal(root.get(VMS_ID), vmsId));
		query.orderBy(builder.asc(root.get(BEGINTIME)), builder.asc(root.get(DATEX_PHR)));
		return getResultList(query);
	}

	public List<AsfinagTrafficmessage> findAsfinagTrafficsBySituationId(int situationId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AsfinagTrafficmessage> query = builder.createQuery(AsfinagTrafficmessage.class);
		Root<AsfinagTrafficmessage> root = query.from(AsfinagTrafficmessage.class);
		query.where(builder.equal(root.get(SITUATION_ID), situationId));
		query.orderBy(builder.asc(root.get(BEGINTIME)), builder.asc(root.get(DATEX_PHR)));
		return getResultList(query);
	}

	public List<Tuple> findTrafficTypeTuples() {
		CriteriaQuery<Tuple> query = entityManager.getCriteriaBuilder().createTupleQuery();
		Root<AsfinagTrafficmessage> root = query.from(AsfinagTrafficmessage.class);
		return getResultList(query.distinct(true).multiselect(root.get(DATEX_PHR), root.get(MESSAGETEXT)));
	}
}
