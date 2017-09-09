package at.jku.csi.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import at.jku.csi.dao.AbstractDao;
import at.jku.csi.rest.model.Filter;
import at.jku.tk.csi.entity.BaseEntity;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.EvolvingObject;

public abstract class AbstractService<T extends BaseEntity> implements Serializable {

	public T findById(int id) {
		return getDao().findById(id);
	}

	public long count() {
		return count(new ArrayList<>());
	}

	public long count(List<Filter> filter) {
		return getDao().count(filter);
	}

	public List<T> findAll(int page, int pageSize) {
		return findAll(page, pageSize, new ArrayList<>());
	}

	public List<T> findAll(int page, int pageSize, List<Filter> filter) {
		return getDao().findAll(page, pageSize, filter);
	}

	protected abstract AbstractDao<T> getDao();
}
