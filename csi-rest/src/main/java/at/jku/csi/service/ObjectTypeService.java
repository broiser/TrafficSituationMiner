package at.jku.csi.service;

import static at.jku.csi.evaluator.ExactRelationTypeEvaluator.EXACT_RELATION;
import static at.jku.csi.evaluator.InFrontOfRelationTypeEvaluator.IN_FRONT_OF_RELATION;
import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.ObjectTypeDao;
import at.jku.csi.evaluator.ExactRelationTypeEvaluator;
import at.jku.csi.evaluator.InFrontOfRelationTypeEvaluator;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TrafficType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class ObjectTypeService implements Serializable {

	@Inject
	private ObjectTypeDao objectTypeDao;
	@Inject
	private TrafficTypeService trafficTypeService;
	@Inject
	private RelationTypeService relationTypeService;
	@Inject
	private ExactRelationTypeEvaluator exactRelationTypeEvaluator;
	@Inject
	private InFrontOfRelationTypeEvaluator inFrontOfRelationTypeEvaluator;

	@Transactional
	public List<ObjectType> createObjectTypes(List<StateInstance> stateInstances) {
		Map<SituationStateType, List<StateInstance>> situationStateType2StateInstances = stateInstances.stream()
				.collect(groupingBy(stateInstance -> stateInstance.getSituationStateType()));

		List<ObjectType> objectTypes = new ArrayList<>();
		for (SituationStateType situationStateType : situationStateType2StateInstances.keySet()) {
			for (StateInstance stateInstance : situationStateType2StateInstances.get(situationStateType)) {
				objectTypes.addAll(buildObjectTypes(stateInstance));
			}
		}

		// return objectTypeDao.saveAll(objectTypes);
		return objectTypes;
	}

	private List<ObjectType> buildObjectTypes(StateInstance stateInstance) {
		List<ObjectType> objectTypes = new ArrayList<>();

		for (Pair<AsfinagTrafficmessage, AsfinagTrafficmessage> trafficmessagePair : determineAsfinagTrafficmessagePairs(
				stateInstance)) {
			SituationStateType situationStateType = stateInstance.getSituationStateType();
			Stream<String> situationTypeStream = asList(trafficmessagePair.left, trafficmessagePair.right).stream()
					.map(trafficmessage -> trafficmessage.getDatex_phr());
			List<String> aliases = determineObjectTypeAliases(situationTypeStream);
			if (inFrontOfRelationTypeEvaluator.evaluate(trafficmessagePair.left, trafficmessagePair.right)) {
				objectTypes.addAll(buildInFrontOfObjectTypes(aliases.get(0), aliases.get(1), situationStateType));
			} else if (inFrontOfRelationTypeEvaluator.evaluate(trafficmessagePair.right, trafficmessagePair.left)) {
				objectTypes.addAll(buildInFrontOfObjectTypes(aliases.get(1), aliases.get(0), situationStateType));
			}
			if (exactRelationTypeEvaluator.evaluate(trafficmessagePair.right, trafficmessagePair.left)) {
				objectTypes.addAll(buildExactObjectTypes(aliases.get(0), aliases.get(1), situationStateType));
			}
		}
		// return objectTypeDao.saveAll(objectTypes);
		return objectTypes;
	}

	private List<ObjectType> buildExactObjectTypes(String alias1, String alias2,
			SituationStateType situationStateType) {
		ObjectType objectType1 = buildObjectType(alias1, situationStateType);
		ObjectType objectType2 = buildObjectType(alias2, situationStateType);
		RelationType relationType = findOrCreateRelationType(EXACT_RELATION);
		objectType1.setRelationTypeR(relationType);
		objectType2.setRelationTypeL(relationType);
		return asList(objectType1, objectType2);
	}

	private List<ObjectType> buildInFrontOfObjectTypes(String alias1, String alias2,
			SituationStateType situationStateType) {
		ObjectType objectType1 = buildObjectType(alias1, situationStateType);
		ObjectType objectType2 = buildObjectType(alias2, situationStateType);
		objectType1.setRelationTypeR(findOrCreateRelationType(IN_FRONT_OF_RELATION));
		objectType2.setRelationTypeL(findOrCreateRelationType(IN_FRONT_OF_RELATION));
		return asList(objectType1, objectType2);
	}

	private RelationType findOrCreateRelationType(String name) {
		RelationType relationType = relationTypeService.findRelationType(name);
		return relationType == null ? relationTypeService.createRelationType(name) : relationType;
	}

	private ObjectType buildObjectType(String alias, SituationStateType situationStateType) {
		ObjectType objectType = new ObjectType();
		objectType.setAlias(alias);
		objectType.setSituationStateType(situationStateType);
		objectType.setTrafficType(findTrafficMessageType(alias));
		return objectType;
	}

	private TrafficType findTrafficMessageType(String alias) {
		String type = alias.substring(1).split("_")[0];
		return trafficTypeService.findTrafficeType(type);
	}

	private List<Pair<AsfinagTrafficmessage, AsfinagTrafficmessage>> determineAsfinagTrafficmessagePairs(
			StateInstance stateInstance) {
		List<AsfinagTrafficmessage> trafficmessages = new ArrayList<>(stateInstance.getAsfinagTrafficmessage());

		List<Pair<AsfinagTrafficmessage, AsfinagTrafficmessage>> trafficmessagePairs = new ArrayList<>();
		for (int i = 0; i < trafficmessages.size(); i++) {
			for (int j = i + 1; j < trafficmessages.size(); j++) {
				trafficmessagePairs.add(new Pair<>(trafficmessages.get(i), trafficmessages.get(j)));
			}
		}
		return trafficmessagePairs;
	}

	private List<String> determineObjectTypeAliases(Stream<String> typeStream) {
		Map<String, Long> type2Count = typeStream.collect(groupingBy(identity(), counting()));

		List<String> aliases = new ArrayList<>();
		for (String type : type2Count.keySet()) {
			long count = type2Count.get(type);
			aliases.addAll(buildObjectTypeAliases(type, count));
		}

		return aliases;
	}

	private List<String> buildObjectTypeAliases(String type, long count) {
		if (count == 1) {
			return asList(format("${0}", type));
		}
		List<String> aliases = new ArrayList<>();
		for (long index = 1; index <= count; index++) {
			aliases.add(format("${0}_{1}", type, index));
		}
		return aliases;
	}

	private final class Pair<F, S> {
		private final F left;
		private final S right;

		public Pair(final F left, final S right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "(" + left + ", " + right + ")";
		}

		@Override
		public int hashCode() {
			return left.hashCode() + right.hashCode();
		}

		@Override
		public boolean equals(final Object obj) {
			if (!(obj instanceof Pair)) {
				return false;
			}
			return left.equals(((Pair<?, ?>) obj).left) && right.equals(((Pair<?, ?>) obj).right);
		}
	}
}
