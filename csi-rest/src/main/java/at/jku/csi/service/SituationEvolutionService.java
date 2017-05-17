package at.jku.csi.service;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.comparator.StateInstanceComparator;
import at.jku.csi.dao.SituationEvolutionDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.DiscreteStateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class SituationEvolutionService implements Serializable {
	private static final String SEQUENCE_DELIMITER = " -> ";

	@Inject
	private SituationEvolutionDao situationEvolutionDao;
	@Inject
	private StateDurationCalculator stateDurationCalculator;
	@Inject
	private DiscreteStateInstanceService discreteStateInstanceService;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficmessageService;
	@Inject
	private StateInstanceComparator stateInstanceComparator;

	@Transactional
	public SituationEvolution createSituationEvolution(int situationId) {
		List<AsfinagTrafficmessage> trafficmessage = findAsfinagTrafficmessagesBySituationId(situationId);
		List<DiscreteStateInstance> discreteStateInstances = discreteStateInstanceService
				.createDiscreteStateInstances(trafficmessage);
		List<StateInstance> stateInstances = buildStateInstances(discreteStateInstances);

		SituationEvolution situationEvolution = new SituationEvolution();
		situationEvolution.setSituation_id(situationId);
		situationEvolution.setStateInstance(stateInstances);
		situationEvolution.setDiscreteStateInstance(discreteStateInstances);
		situationEvolution.setEvoSteps(stateInstances.size());
		situationEvolution.setEvolutionSequence(buildEvolutionSequence(discreteStateInstances));
		situationEvolution.setMajorEvoSteps(discreteStateInstances.size());
		situationEvolution.setTotalDuration(calculateTotalDuration(stateInstances));
		return situationEvolutionDao.save(situationEvolution);
	}

	private List<StateInstance> buildStateInstances(List<DiscreteStateInstance> discreteStateInstances) {
		return discreteStateInstances.stream().flatMap(t -> t.getStateInstance().stream()).distinct()
				.sorted(stateInstanceComparator).collect(toList());
	}

	private String buildEvolutionSequence(List<DiscreteStateInstance> stateInstances) {
		return stateInstances.stream().map(instance -> instance.getName()).collect(joining(SEQUENCE_DELIMITER));
	}

	private double calculateTotalDuration(List<StateInstance> stateInstances) {
		if (stateInstances.isEmpty()) {
			return 0;
		}
		StateInstance lastStateInstance = stateInstances.get(stateInstances.size() - 1);
		return stateDurationCalculator.calculateDuration(stateInstances.get(0), lastStateInstance);
	}

	private List<AsfinagTrafficmessage> findAsfinagTrafficmessagesBySituationId(int situationId) {
		return filterDupliatedAsfinagTrafficmessage(
				asfinagTrafficmessageService.findAsfinagTrafficmessagesBySituationId(situationId));
	}

	private List<AsfinagTrafficmessage> filterDupliatedAsfinagTrafficmessage(
			List<AsfinagTrafficmessage> trafficmessages) {
		Stream<AsfinagTrafficmessageWrapper> stream = trafficmessages.stream().map(AsfinagTrafficmessageWrapper::new);
		return stream.distinct().map(AsfinagTrafficmessageWrapper::unwrap).collect(Collectors.toList());
	}

	private final class AsfinagTrafficmessageWrapper {
		private AsfinagTrafficmessage trafficmessage;

		public AsfinagTrafficmessageWrapper(AsfinagTrafficmessage trafficmessage) {
			this.trafficmessage = trafficmessage;
		}

		public AsfinagTrafficmessage unwrap() {
			return trafficmessage;
		}

		public boolean equals(Object other) {
			if (!(other instanceof AsfinagTrafficmessageWrapper)) {
				return false;
			}
			return equals(((AsfinagTrafficmessageWrapper) other).trafficmessage);
		}

		private boolean equals(AsfinagTrafficmessage otherTrafficmessage) {
			return trafficmessage.getSituation_id().equals(otherTrafficmessage.getSituation_id())
					&& trafficmessage.getVmis_id().equals(otherTrafficmessage.getVmis_id())
					&& trafficmessage.getBegintime().equals(otherTrafficmessage.getBegintime())
					&& trafficmessage.getDatex_phr().equals(otherTrafficmessage.getDatex_phr());
		}

		public int hashCode() {
			return trafficmessage.getSituation_id().hashCode() + trafficmessage.getVmis_id().hashCode()
					+ trafficmessage.getBeginmeter().hashCode() + trafficmessage.getDatex_phr().hashCode();
		}
	}
}
