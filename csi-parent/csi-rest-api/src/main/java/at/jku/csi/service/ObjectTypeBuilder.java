package at.jku.csi.service;

import static at.jku.csi.evaluator.CloseDistanceRelationTypeEvaluator.CLOSE_DISTANCE_RELATION;
import static at.jku.csi.evaluator.ExactRelationTypeEvaluator.EXACT_RELATION;
import static at.jku.csi.evaluator.FarDistanceRelationTypeEvaluator.FAR_DISTANCE_RELATION;
import static at.jku.csi.evaluator.InFrontOfRelationTypeEvaluator.IN_FRONT_OF_RELATION;
import static at.jku.csi.evaluator.IntermediateDistanceRelationTypeEvaluator.INTERMEDIATE_DISTANCE_RELATION;
import static at.jku.csi.evaluator.IntersectionRelationTypeEvaluator.INTERSECTION_RELATION;
import static at.jku.csi.evaluator.VeryCloseDistanceRelationTypeEvaluator.VERY_CLOSE_DISTANCE_RELATION;
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

import at.jku.csi.cdi.Service;
import at.jku.csi.evaluator.CloseDistanceRelationTypeEvaluator;
import at.jku.csi.evaluator.ExactRelationTypeEvaluator;
import at.jku.csi.evaluator.FarDistanceRelationTypeEvaluator;
import at.jku.csi.evaluator.InFrontOfRelationTypeEvaluator;
import at.jku.csi.evaluator.IntermediateDistanceRelationTypeEvaluator;
import at.jku.csi.evaluator.IntersectionRelationTypeEvaluator;
import at.jku.csi.evaluator.VeryCloseDistanceRelationTypeEvaluator;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TrafficType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class ObjectTypeBuilder implements Serializable {

	@Inject
	private TrafficTypeService trafficTypeService;
	@Inject
	private RelationTypeService relationTypeService;
	@Inject
	private ExactRelationTypeEvaluator exactRelationTypeEvaluator;
	@Inject
	private InFrontOfRelationTypeEvaluator inFrontOfRelationTypeEvaluator;
	@Inject
	private IntersectionRelationTypeEvaluator intersectionRelationTypeEvaluator;
	@Inject
	private VeryCloseDistanceRelationTypeEvaluator veryCloseDistanceRelationTypeEvaluator;
	@Inject
	private CloseDistanceRelationTypeEvaluator closeDistanceRelationTypeEvaluator;
	@Inject
	private IntermediateDistanceRelationTypeEvaluator intermediateDistanceRelationTypeEvaluator;
	@Inject
	private FarDistanceRelationTypeEvaluator farDistanceRelationTypeEvaluator;

	public List<ObjectType> buildObjectTypes(StateInstance stateInstance) {
		List<ObjectType> objectTypes = new ArrayList<>();
		for (Pair<AsfinagTrafficmessage, AsfinagTrafficmessage> trafficmessagePair : determineTrafficmessagePairs(
				stateInstance)) {
			objectTypes.addAll(buildObjectTypes(stateInstance, trafficmessagePair));
		}
		return objectTypes;
	}

	private List<ObjectType> buildObjectTypes(StateInstance stateInstance,
			Pair<AsfinagTrafficmessage, AsfinagTrafficmessage> trafficmessagePair) {
		List<ObjectType> objectTypes = new ArrayList<>();
		SituationStateType situationStateType = stateInstance.getSituationStateType();

		List<String> aliases = determineObjectTypeAliases(determineSituationTypeStream(trafficmessagePair));
		if (inFrontOfRelationTypeEvaluator.evaluate(trafficmessagePair.left, trafficmessagePair.right)) {
			objectTypes.addAll(buildObjectTypes(aliases, situationStateType, IN_FRONT_OF_RELATION));
		} else if (inFrontOfRelationTypeEvaluator.evaluate(trafficmessagePair.right, trafficmessagePair.left)) {
			List<String> reservedAliases = new ArrayList<>(asList(aliases.get(1), aliases.get(0)));
			objectTypes.addAll(buildObjectTypes(reservedAliases, situationStateType, IN_FRONT_OF_RELATION));
		}
		if (exactRelationTypeEvaluator.evaluate(trafficmessagePair.right, trafficmessagePair.left)) {
			objectTypes.addAll(buildObjectTypes(aliases, situationStateType, EXACT_RELATION));
		} else if (intersectionRelationTypeEvaluator.evaluate(trafficmessagePair.left, trafficmessagePair.right)) {
			objectTypes.addAll(buildObjectTypes(aliases, situationStateType, INTERSECTION_RELATION));
		} else if (veryCloseDistanceRelationTypeEvaluator.evaluate(trafficmessagePair.left, trafficmessagePair.right)) {
			objectTypes.addAll(buildObjectTypes(aliases, situationStateType, VERY_CLOSE_DISTANCE_RELATION));
		} else if (closeDistanceRelationTypeEvaluator.evaluate(trafficmessagePair.left, trafficmessagePair.right)) {
			objectTypes.addAll(buildObjectTypes(aliases, situationStateType, CLOSE_DISTANCE_RELATION));
		} else if (intermediateDistanceRelationTypeEvaluator.evaluate(trafficmessagePair.left,
				trafficmessagePair.right)) {
			objectTypes.addAll(buildObjectTypes(aliases, situationStateType, INTERMEDIATE_DISTANCE_RELATION));
		} else if (farDistanceRelationTypeEvaluator.evaluate(trafficmessagePair.left, trafficmessagePair.right)) {
			objectTypes.addAll(buildObjectTypes(aliases, situationStateType, FAR_DISTANCE_RELATION));
		}
		return objectTypes;
	}

	private Stream<String> determineSituationTypeStream(
			Pair<AsfinagTrafficmessage, AsfinagTrafficmessage> trafficmessagePair) {
		AsfinagTrafficmessage[] asfinagTrafficmessages = { trafficmessagePair.left, trafficmessagePair.right };
		return asList(asfinagTrafficmessages).stream().map(trafficmessage -> trafficmessage.getDatex_phr());
	}

	private List<ObjectType> buildObjectTypes(List<String> aliases, SituationStateType situationStateType,
			String relationTypeName) {
		ObjectType objectType1 = buildObjectType(aliases.get(0), situationStateType);
		ObjectType objectType2 = buildObjectType(aliases.get(1), situationStateType);
		objectType1.setRelationTypeR(findOrCreateRelationType(relationTypeName));
		objectType2.setRelationTypeL(findOrCreateRelationType(relationTypeName));
		return new ArrayList<>(asList(objectType1, objectType2));
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

	private List<Pair<AsfinagTrafficmessage, AsfinagTrafficmessage>> determineTrafficmessagePairs(
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
