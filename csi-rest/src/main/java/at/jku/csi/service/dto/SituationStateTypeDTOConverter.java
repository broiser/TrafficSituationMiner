package at.jku.csi.service.dto;

import java.util.function.Function;

import at.jku.csi.cdi.Service;
import at.jku.csi.service.model.dto.SituationStateTypeDTO;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;

@Service
public class SituationStateTypeDTOConverter implements Function<SituationStateType, SituationStateTypeDTO> {

	@Override
	public SituationStateTypeDTO apply(SituationStateType source) {
		SituationStateTypeDTO target = new SituationStateTypeDTO();
		target.setID(source.getID());
		target.setName(source.getName());
		target.setType(source.getType());
		return target;
	}

}
