package at.jku.csi.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import at.jku.csi.rest.model.Filter;
import at.jku.tk.csi.entity.BaseEntity;

public abstract class AbstractDao<T extends BaseEntity> implements Serializable {

	@Inject
	private DaoQueryService daoQueryService;
	@Inject
	private DaoHelperService daoHelperService;
	
	@PersistenceContext(unitName = "csiSA")
	protected EntityManager entityManager;

	private Class<T> responseClass;

	public AbstractDao(Class<T> responseClass) {
		this.responseClass = responseClass;
	}

	@Transactional(value = TxType.MANDATORY)
	public T save(T entity) {
		return entityManager.merge(entity);
	}

	@Transactional(value = TxType.MANDATORY)
	public List<T> saveAll(List<T> entities) {
		List<T> savedEntities = new ArrayList<>();
		for (T entity : entities) {
			savedEntities.add(save(entity));
		}
		return savedEntities;
	}

	public T findById(int id) {
		return entityManager.find(responseClass, id);
	}

	public List<T> findAll(int page, int pageSize, List<Filter> filters) {
		CriteriaQuery<T> query = daoQueryService.buildFindAllQuery(entityManager, responseClass);
		return getResultList(daoQueryService.extendFilters(entityManager, query, filters), page, pageSize);
	}

	public long count(List<Filter> filters) {
		CriteriaQuery<Long> query = daoQueryService.buildCountQuery(entityManager, responseClass);
		return getSingleResult(daoQueryService.extendFilters(entityManager, query, filters));
	}

	private <S> List<S> getResultList(CriteriaQuery<S> query, int page, int pageSize) {
		return daoHelperService.getResultList(entityManager.createQuery(query), page, pageSize);
	}

	protected <S> List<S> getResultList(CriteriaQuery<S> query) {
		return daoHelperService.getResultList(entityManager.createQuery(query));
	}

	protected <S> S getSingleResult(CriteriaQuery<S> query) {
		return daoHelperService.getSingleResult(entityManager.createQuery(query));
	}

}
