package at.jku.csi.service.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import at.jku.csi.cdi.Service;
import at.jku.csi.service.model.dto.EvolvingObjectDTO;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.EvolvingObject;

@Service
public class EvolvingObjectDTOConverter implements Function<EvolvingObject, EvolvingObjectDTO> {

	@Inject
	private AsfinagTrafficmessageDTOConverter asfinagTrafficmessageDTOConverter;

	public List<EvolvingObjectDTO> apply(List<EvolvingObject> evolvingObjects) {
		return evolvingObjects.parallelStream().map((source) -> apply(source)).collect(toList());
	}

	@Override
	public EvolvingObjectDTO apply(EvolvingObject source) {
		EvolvingObjectDTO target = new EvolvingObjectDTO();
		target.setID(source.getID());
		target.setDuration(source.getDuration());
		target.setEvoSequence(source.getEvoSequence());
		target.setNrPHRchanges(source.getNrPHRchanges());
		target.setNrSpatialEvolutions(source.getNrSpatialEvolutions());
		target.setNrUpdates(source.getNrUpdates());
		target.setSources(source.getSources());
		target.setVmis_id(source.getVmis_id());
		target.setAsfinagTrafficmessages(asfinagTrafficmessageDTOConverter.apply(source.getAsfinagTrafficmessage()));
		return target;
	}
}
