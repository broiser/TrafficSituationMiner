package at.jku.csi.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.DiscreteStateInstanceDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.DiscreteStateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class DiscreteStateInstanceService implements Serializable {

	@Inject
	private SituationStateTypeService situationStateTypeService;
	@Inject
	private DiscreteStateInstanceDao discreteStateInstanceDao;
	@Inject
	private StateInstanceService stateInstanceService;
	@Inject
	private DurationCalculator durationCalculator;

	@Transactional
	public List<DiscreteStateInstance> createDiscreteStateInstances(List<AsfinagTrafficmessage> trafficmessages) {
		List<StateInstance> stateInstances = stateInstanceService.createStateInstances(trafficmessages);
		List<DiscreteStateInstance> discreteStateInstances = new ArrayList<>();
		for (List<StateInstance> groupedStateInstances : groupStateInstancesByName(stateInstances)) {
			DiscreteStateInstance discreteStateInstance = createDiscreateStateInstance(groupedStateInstances);
			discreteStateInstance.getStateInstance()
					.forEach(stateInstance -> stateInstance.setDiscreteStateInstance(discreteStateInstance));
			discreteStateInstances.add(discreteStateInstanceDao.save(discreteStateInstance));
		}
		return discreteStateInstances;
	}

	private DiscreteStateInstance createDiscreateStateInstance(List<StateInstance> groupedStateInstances) {
		return discreteStateInstanceDao.save(buildDiscreteStateInstance(groupedStateInstances));
	}

	private DiscreteStateInstance buildDiscreteStateInstance(List<StateInstance> stateInstances) {
		StateInstance firstStateInstance = stateInstances.get(0);
		StateInstance lastStateInstance = stateInstances.get(stateInstances.size() - 1);

		DiscreteStateInstance discreteStateInstance = new DiscreteStateInstance();
		discreteStateInstance.setName(firstStateInstance.getName());
		discreteStateInstance.setBeginTime(firstStateInstance.getBeginTime());
		discreteStateInstance.setStateInstance(new HashSet<>(stateInstances));
		discreteStateInstance.setDuration(calcuateDuration(firstStateInstance, lastStateInstance));
		discreteStateInstance.setSituationStateType(findOrCreateSituationStateType(firstStateInstance.getName()));
		return discreteStateInstance;
	}

	private double calcuateDuration(StateInstance firstStateInstance, StateInstance lastStateInstance) {
		return durationCalculator.calculateDuration(firstStateInstance, lastStateInstance);
	}

	private SituationStateType findOrCreateSituationStateType(String name) {
		SituationStateType situationStateType = situationStateTypeService.findSituationStateType(name);
		if (situationStateType != null) {
			return situationStateType;
		}
		return situationStateTypeService.createSituationStateType(name);
	}

	private List<List<StateInstance>> groupStateInstancesByName(List<StateInstance> stateInstances) {
		Map<String, List<StateInstance>> name2stateInstances = new HashMap<>();
		for (StateInstance stateInstance : stateInstances) {
			String name = stateInstance.getName();
			if (!name2stateInstances.containsKey(name)) {
				name2stateInstances.put(name, new ArrayList<>());
			}
			name2stateInstances.get(name).add(stateInstance);
		}
		return new ArrayList<>(name2stateInstances.values());
	}
}
