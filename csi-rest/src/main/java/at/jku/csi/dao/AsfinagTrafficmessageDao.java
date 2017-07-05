package at.jku.csi.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Dao
public class AsfinagTrafficmessageDao extends AbstractDao<AsfinagTrafficmessage> {
	private static final String DATEX_PHR = "datex_phr";
	private static final String BEGINTIME = "begintime";
	private static final String UPDATETIME = "update_time";
	private static final String SITUATION_ID = "situation_id";
	private static final String MESSAGETEXT = "messagetext";
	private static final String VMIS_ID = "vmis_id";

	public List<Integer> findSituationIds(Date from, Date to) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
		Root<AsfinagTrafficmessage> root = query.from(AsfinagTrafficmessage.class);
		query.where(builder.between(root.get(BEGINTIME), from, to));
		return getResultList(query.distinct(true).select(root.get(SITUATION_ID)));
	}

	public List<Integer> findVmisIds(Date from, Date to) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
		Root<AsfinagTrafficmessage> root = query.from(AsfinagTrafficmessage.class);
		query.where(builder.between(root.get(BEGINTIME), from, to));
		return getResultList(query.distinct(true).select(root.get(VMIS_ID)));
	}

	public List<AsfinagTrafficmessage> findAsfinagTrafficsByVmisId(int vmisId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AsfinagTrafficmessage> query = builder.createQuery(AsfinagTrafficmessage.class);
		Root<AsfinagTrafficmessage> root = query.from(AsfinagTrafficmessage.class);
		query.orderBy(builder.asc(root.get(BEGINTIME)), builder.asc(root.get(UPDATETIME)));
		return getResultList(query.where(builder.equal(root.get(VMIS_ID), vmisId)));
	}

	public List<AsfinagTrafficmessage> findAsfinagTrafficsBySituationId(int situationId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AsfinagTrafficmessage> query = builder.createQuery(AsfinagTrafficmessage.class);
		Root<AsfinagTrafficmessage> root = query.from(AsfinagTrafficmessage.class);
		query.where(builder.equal(root.get(SITUATION_ID), situationId), builder.isNotNull(root.get(BEGINTIME)),
				builder.isNotNull(root.get(DATEX_PHR)), builder.isNotNull(root.get(VMIS_ID)));
		query.orderBy(builder.asc(root.get(BEGINTIME)), builder.asc(root.get(DATEX_PHR)));
		return getResultList(query);
	}

	public List<Tuple> findTrafficTypeTuples() {
		CriteriaQuery<Tuple> query = entityManager.getCriteriaBuilder().createTupleQuery();
		Root<AsfinagTrafficmessage> root = query.from(AsfinagTrafficmessage.class);
		return getResultList(query.distinct(true).multiselect(root.get(DATEX_PHR), root.get(MESSAGETEXT)));
	}
}
