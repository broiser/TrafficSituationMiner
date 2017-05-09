package at.jku.csi.service;

import java.io.Serializable;

import javax.inject.Inject;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.EvolvingObjectDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.EvolvingObject;

@Service
public class EvolvingObjectService implements Serializable {

	@Inject
	private EvolvingObjectDao evolvingObjectDao;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficemessageService;

	public EvolvingObject createEvolvingObject(int vmsId) {
		asfinagTrafficemessageService.findAsfinagTrafficmessagesByVmsId(vmsId);
		return null;
	}

}
