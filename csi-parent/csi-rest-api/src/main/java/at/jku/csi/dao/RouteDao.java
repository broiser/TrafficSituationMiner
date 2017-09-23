package at.jku.csi.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import at.jku.csi.cdi.Dao;
import at.jku.tk.csi.server.datalayer.source.static_.analysis.Route;

@Dao
public class RouteDao implements Serializable {

	@PersistenceContext(unitName = "infrastructureSA")
	private EntityManager entityManager;

	@Inject
	private DaoHelperService daoHelperService;

	public Route findRouteByRoadname(String road_code) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Route> query = builder.createQuery(Route.class);
		query.where(builder.equal(query.from(Route.class).get("road_code"), road_code));
		TypedQuery<Route> typedQuery = entityManager.createQuery(query);
		return daoHelperService.getSingleResult(typedQuery);
	}
}
