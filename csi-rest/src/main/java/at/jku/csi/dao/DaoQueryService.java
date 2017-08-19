package at.jku.csi.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import at.jku.csi.cdi.Service;

@Service
public class DaoQueryService implements Serializable {

	public <S> CriteriaQuery<Long> buildCountQuery(EntityManager entityManager, Class<S> responseClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		criteriaQuery.select(builder.count(criteriaQuery.from(responseClass)));
		return criteriaQuery;
	}

	public <S> CriteriaQuery<S> buildFindAllQuery(EntityManager entityManager, Class<S> responseClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<S> criteriaQuery = builder.createQuery(responseClass);
		return criteriaQuery.select(criteriaQuery.from(responseClass));
	}

}
