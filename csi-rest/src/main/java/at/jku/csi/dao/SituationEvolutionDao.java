package at.jku.csi.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution;

@Dao
public class SituationEvolutionDao extends AbstractDao<SituationEvolution> {

	private static final String SITUATION_ID = "situation_id";

	public SituationEvolutionDao() {
		super(SituationEvolution.class);
	}

	public SituationEvolution findBySituationId(long situationId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SituationEvolution> query = builder.createQuery(SituationEvolution.class);
		Root<SituationEvolution> root = query.from(SituationEvolution.class);
		return getSingleResult(query.where(builder.equal(root.get(SITUATION_ID), situationId)));
	}
}
