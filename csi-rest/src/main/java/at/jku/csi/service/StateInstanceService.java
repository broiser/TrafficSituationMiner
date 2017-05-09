package at.jku.csi.service;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.StateInstanceDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.Transition;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class StateInstanceService implements Serializable {

	@Inject
	private StateInstanceDao stateInstanceDao;
	@Inject
	private TransitionService transitionService;
	@Inject
	private SituationStateTypeService situationStateTypeService;

	@Transactional
	public List<StateInstance> createStateInstances(List<AsfinagTrafficmessage> trafficmessages) {
		List<StateInstance> stateInstances = new ArrayList<>();
		for (List<AsfinagTrafficmessage> groupedTrafficmessages : groupTrafficeMessagesByBegintime(trafficmessages)) {
			stateInstances.add(stateInstanceDao.save(buildStateInstance(groupedTrafficmessages)));
			if (2 <= stateInstances.size()) {
				StateInstance nextStateInstance = stateInstances.get(stateInstances.size() - 1);
				StateInstance previousStateInstance = stateInstances.get(stateInstances.size() - 2);
				Transition transition = transitionService.createTransition(previousStateInstance, nextStateInstance);
				previousStateInstance.setNextStateInstanceTransition(transition);
				nextStateInstance.setPreviousStateInstanceTransition(transition);
			}
		}
		return stateInstanceDao.saveAll(stateInstances);
	}

	private List<List<AsfinagTrafficmessage>> groupTrafficeMessagesByBegintime(
			List<AsfinagTrafficmessage> trafficmessages) {
		Map<Date, List<AsfinagTrafficmessage>> begintime2Trafficmessages = new TreeMap<>();
		for (AsfinagTrafficmessage trafficmessage : trafficmessages) {
			Timestamp begintime = trafficmessage.getBegintime();
			if (begintime != null) {
				if (!begintime2Trafficmessages.containsKey(begintime)) {
					begintime2Trafficmessages.put(begintime, new ArrayList<>());
				}
				begintime2Trafficmessages.get(begintime).add(trafficmessage);
			}
		}
		return new ArrayList<List<AsfinagTrafficmessage>>(begintime2Trafficmessages.values());
	}

	private StateInstance buildStateInstance(List<AsfinagTrafficmessage> trafficMessages) {
		String name = determineStateInstanceName(trafficMessages);

		StateInstance stateInstance = new StateInstance();
		// stateInstance.setAsfinagTrafficmessage(trafficMessages);
		stateInstance.setName(name);
		stateInstance.setBeginTime(determineBegintime(trafficMessages));
		stateInstance.setSituationStateType(findOrCreateSituationStateType(name));
		return stateInstance;
	}

	private String determineStateInstanceName(List<AsfinagTrafficmessage> trafficMessages) {
		return format("{%s}", trafficMessages.stream().map(t -> t.getDatex_phr()).sorted().collect(joining(",")));
	}

	private Timestamp determineBegintime(List<AsfinagTrafficmessage> trafficMessages) {
		return trafficMessages.iterator().next().getBegintime();
	}

	private SituationStateType findOrCreateSituationStateType(String name) {
		SituationStateType situationStateType = situationStateTypeService.findSituationStateType(name);
		if (situationStateType != null) {
			return situationStateType;
		}
		return situationStateTypeService.createSituationStateType(name);
	}
}
