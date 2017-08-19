package at.jku.csi.service.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import at.jku.csi.cdi.Service;
import at.jku.csi.service.model.dto.SituationEvolutionDTO;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution;

@Service
public class SituationEvolutionDTOConverter implements Function<SituationEvolution, SituationEvolutionDTO> {

	@Inject
	private StateInstanceDTOConverter stateInstanceDTOConverter;

	public List<SituationEvolutionDTO> apply(List<SituationEvolution> situationEvolutions) {
		return situationEvolutions.parallelStream().map((source) -> apply(source)).collect(toList());
	}

	@Override
	public SituationEvolutionDTO apply(SituationEvolution source) {
		SituationEvolutionDTO target = new SituationEvolutionDTO();
		target.setID(source.getID());
		target.setEvolutionSequence(source.getEvolutionSequence());
		target.setEvoSteps(source.getEvoSteps());
		target.setMajorEvoSteps(source.getMajorEvoSteps());
		target.setSituation_id(source.getSituation_id());
		target.setStateInstances(stateInstanceDTOConverter.apply(source.getStateInstance()));
		target.setTotalDuration(source.getTotalDuration());
		return target;
	}
}
