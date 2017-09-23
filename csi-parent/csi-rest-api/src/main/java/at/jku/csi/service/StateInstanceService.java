package at.jku.csi.service;

import static java.lang.String.format;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
			stateInstances.add(createStateInstance(groupedTrafficmessages));
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
		return new ArrayList<>(trafficmessages.stream()
				.collect(groupingBy(trafficmesssage -> trafficmesssage.getBegintime())).values());
	}

	private StateInstance createStateInstance(List<AsfinagTrafficmessage> trafficmessages) {
		StateInstance stateInstance = stateInstanceDao.save(buildStateInstance(trafficmessages));
		stateInstance.getAsfinagTrafficmessage()
				.forEach(asfinagTrafficmessage -> asfinagTrafficmessage.setStateInstance(stateInstance));
		return stateInstanceDao.save(stateInstance);
	}

	private StateInstance buildStateInstance(List<AsfinagTrafficmessage> trafficmessages) {
		String name = determineStateInstanceName(trafficmessages);
		StateInstance stateInstance = new StateInstance();
		stateInstance.setName(name);
		stateInstance.setSituationStateType(findOrCreateSituationStateType(name));
		stateInstance.setBeginTime(trafficmessages.iterator().next().getBegintime());
		stateInstance.setAsfinagTrafficmessage(new HashSet<>(trafficmessages));
		return stateInstance;
	}

	private String determineStateInstanceName(List<AsfinagTrafficmessage> trafficMessages) {
		return format("{%s}", trafficMessages.stream().map(t -> t.getDatex_phr()).sorted().collect(joining(",")));
	}

	private SituationStateType findOrCreateSituationStateType(String name) {
		SituationStateType situationStateType = situationStateTypeService.findSituationStateType(name);
		if (situationStateType != null) {
			return situationStateType;
		}
		return situationStateTypeService.createSituationStateType(name);
	}
}
