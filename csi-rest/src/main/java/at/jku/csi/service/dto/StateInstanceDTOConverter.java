package at.jku.csi.service.dto;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import static java.util.stream.Collectors.toList;

import java.util.Date;

import at.jku.csi.cdi.Service;
import at.jku.csi.service.model.dto.StateInstanceDTO;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;

@Service
public class StateInstanceDTOConverter implements Function<StateInstance, StateInstanceDTO> {

	@Inject
	private SituationStateTypeDTOConverter situationStateTypeDTOConverter;
	@Inject
	private AsfinagTrafficmessageDTOConverter asfinagTrafficmessageDTOConverter;

	public List<StateInstanceDTO> apply(List<StateInstance> stateInstances) {
		return stateInstances.parallelStream().map(source -> apply(source)).collect(toList());
	}

	@Override
	public StateInstanceDTO apply(StateInstance source) {
		StateInstanceDTO target = new StateInstanceDTO();
		target.setID(source.getID());
		target.setBeginTime(toMillis(source.getBeginTime()));
		target.setDuration(source.getDuration());
		target.setName(source.getName());
		target.setSituationStateType(situationStateTypeDTOConverter.apply(source.getSituationStateType()));
		target.setAsfinagTrafficmessages(asfinagTrafficmessageDTOConverter.apply(source.getAsfinagTrafficmessage()));
		return target;
	}

	private long toMillis(Date date) {
		return date == null ? 0 : date.getTime();
	}

}
