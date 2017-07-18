package at.jku.csi.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import at.jku.tk.csi.entity.BaseEntity;

public abstract class AbstractDao<T extends BaseEntity> implements Serializable {

	protected static final int FETCH_SIZE = 5;

	@Inject
	EntityManager entityManager;
	
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
		return entityManager.createQuery(query).getResultList();
	}

	protected <S> S getSingleResult(CriteriaQuery<S> query) {
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
