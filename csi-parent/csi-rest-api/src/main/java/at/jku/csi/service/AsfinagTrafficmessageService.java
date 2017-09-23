package at.jku.csi.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Tuple;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.AsfinagTrafficmessageDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class AsfinagTrafficmessageService implements Serializable {

	@Inject
	private AsfinagTrafficmessageDao asfinagTrafficmessageDao;

	public AsfinagTrafficmessage findById(int id) {
		return asfinagTrafficmessageDao.findById(id);
	}

	public List<AsfinagTrafficmessage> findByVmisId(int vmisId) {
		return asfinagTrafficmessageDao.findByVmisId(vmisId);
	}

	public List<AsfinagTrafficmessage> findBySituationId(int situationId) {
		return asfinagTrafficmessageDao.findBySituationId(situationId);
	}

	public List<Integer> findSituationIds(Date from, Date to) {
		return asfinagTrafficmessageDao.findSituationIds(from, to);
	}

	public List<Integer> findVmisIds(Date from, Date to) {
		return asfinagTrafficmessageDao.findVmisIds(from, to);
	}

	public List<Tuple> findTrafficTypes() {
		return asfinagTrafficmessageDao.findTrafficTypes();
	}
}
