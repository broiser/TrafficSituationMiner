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

	protected static final int FETCH_SIZE = 5;

	@PersistenceContext(unitName = "csiSA")
	protected EntityManager entityManager;
	@Inject
	private DaoHelperService daoHelperService;
	
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

	protected <S> List<S> getResultList(CriteriaQuery<S> query) {
		return daoHelperService.getResultList(entityManager.createQuery(query));
	}

	protected <S> S getSingleResult(CriteriaQuery<S> query) {
		return daoHelperService.getSingleResult(entityManager.createQuery(query));
	}

}
