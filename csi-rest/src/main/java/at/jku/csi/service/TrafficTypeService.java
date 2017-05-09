package at.jku.csi.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.TrafficTypeDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TrafficType;

@Service
public class TrafficTypeService implements Serializable {

	@Inject
	private TrafficTypeDao trafficTypeDao;

	public TrafficType findTrafficeType(String type) {
		return trafficTypeDao.findTrafficType(type);
	}

	@Transactional
	public TrafficType createTrafficType(String type, String messageText) {
		return trafficTypeDao.save(buildTrafficType(type, messageText));
	}

	private TrafficType buildTrafficType(String type, String messageText) {
		TrafficType trafficType = new TrafficType();
		trafficType.setPHR(type);
		trafficType.setType(type);
		trafficType.setMessageText(messageText);
		return trafficType;
	}

}
