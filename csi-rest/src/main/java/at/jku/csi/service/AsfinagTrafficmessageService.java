package at.jku.csi.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Tuple;

import org.hibernate.ScrollableResults;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.AsfinagTrafficmessageDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class AsfinagTrafficmessageService implements Serializable {

	@Inject
	private AsfinagTrafficmessageDao asfinagTrafficmessageDao;

	public ScrollableResults findScrollableSituationIds(String phr) {
		return asfinagTrafficmessageDao.findScrollableSituationIds(phr);
	}

	public List<AsfinagTrafficmessage> findAsfinagTrafficmessagesByVmsId(int vmsId) {
		return asfinagTrafficmessageDao.findAsfinagTrafficsByVmsId(vmsId);
	}

	public List<AsfinagTrafficmessage> findAsfinagTrafficmessagesBySituationId(int situationId) {
		return asfinagTrafficmessageDao.findAsfinagTrafficsBySituationId(situationId);
	}

	public List<Tuple> findTrafficTypeTuples() {
		return asfinagTrafficmessageDao.findTrafficTypeTuples();
	}

}
