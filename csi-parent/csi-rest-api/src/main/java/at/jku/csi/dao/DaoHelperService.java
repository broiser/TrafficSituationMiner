package at.jku.csi.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import at.jku.csi.cdi.Service;

@Service
public class DaoHelperService implements Serializable {

	public <S> List<S> getResultList(TypedQuery<S> query) {
		return query.getResultList();
	}

	public <S> List<S> getResultList(TypedQuery<S> query, int page, int pageSize) {
		int firstResult = Math.max((page - 1), 0) * pageSize;
		return getResultList(query.setFirstResult(firstResult).setMaxResults(pageSize));
	}

	public <S> S getSingleResult(TypedQuery<S> query) {
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
