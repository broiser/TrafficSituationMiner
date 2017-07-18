package at.jku.csi.service;

import static java.util.stream.Collectors.groupingBy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.ObjectTypeDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;

@Service
public class ObjectTypeService implements Serializable {

	@Inject
	private ObjectTypeDao objectTypeDao;
	@Inject
	private ObjectTypeBuilder objectTypeBuilder;

	@Transactional
	public List<ObjectType> createObjectTypes(List<StateInstance> stateInstances) {
		Map<SituationStateType, List<StateInstance>> situationStateType2StateInstances = stateInstances.stream()
				.collect(groupingBy(stateInstance -> stateInstance.getSituationStateType()));
		List<ObjectType> objectTypes = new ArrayList<>();
		for (SituationStateType situationStateType : situationStateType2StateInstances.keySet()) {
			for (StateInstance stateInstance : situationStateType2StateInstances.get(situationStateType)) {
				objectTypes.addAll(objectTypeBuilder.buildObjectTypes(stateInstance));
			}
		}
		return objectTypeDao.saveAll(objectTypes);
	}
}
