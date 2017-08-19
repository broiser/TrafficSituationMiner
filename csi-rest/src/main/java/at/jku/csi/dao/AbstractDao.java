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

import at.jku.tk.csi.entity.BaseEntity;

public abstract class AbstractDao<T extends BaseEntity> implements Serializable {

	@PersistenceContext(unitName = "csiSA")
	protected EntityManager entityManager;

	@Inject
	private DaoQueryService daoQueryService;
	@Inject
	private DaoHelperService daoHelperService;

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

	public List<T> findAll() {
		return getResultList(daoQueryService.buildFindAllQuery(entityManager, responseClass));
	}

	public List<T> findAll(int page, int pageSize) {
		return getResultList(daoQueryService.buildFindAllQuery(entityManager, responseClass), page, pageSize);
	}

	public long count() {
		return getSingleResult(daoQueryService.buildCountQuery(entityManager, responseClass));
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
