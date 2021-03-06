package at.jku.csi.service;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.joining;

import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.AbstractDao;
import at.jku.csi.dao.EvolvingObjectDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.EvolvingObject;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class EvolvingObjectService extends AbstractService<EvolvingObject> {

	private static final String EVO_SYMBOL = " -> ";
	private static final String SOURCE_SYMBOL = ";";

	@Inject
	private EvolvingObjectDao evolvingObjectDao;
	@Inject
	private DurationCalculator durationCalculator;

	@Transactional
	public EvolvingObject createEvolvingObject(List<AsfinagTrafficmessage> trafficmessages) {
		EvolvingObject evolvingObject = evolvingObjectDao.save(buildEvolvingObject(trafficmessages));
		evolvingObject.getAsfinagTrafficmessage()
				.forEach(trafficmessage -> trafficmessage.setEvolvingObject(evolvingObject));
		return evolvingObjectDao.save(evolvingObject);
	}

	private EvolvingObject buildEvolvingObject(List<AsfinagTrafficmessage> trafficmessages) {
		EvolvingObject evolvingObject = new EvolvingObject();
		evolvingObject.setVmis_id(determineVmisId(trafficmessages));
		evolvingObject.setNrUpdates(trafficmessages.size());
		evolvingObject.setAsfinagTrafficmessage(new HashSet<>(trafficmessages));
		evolvingObject.setDuration(caluclateDuration(trafficmessages));
		evolvingObject.setSources(determineSources(trafficmessages));
		evolvingObject.setEvoSequence(determineEvoSequence(trafficmessages));
		evolvingObject.setNrSpatialEvolutions(countSpatialEvolutions(trafficmessages));
		evolvingObject.setNrPHRchanges(countDatexPhrChanges(trafficmessages));
		return evolvingObject;
	}

	private Integer determineVmisId(List<AsfinagTrafficmessage> trafficmessages) {
		return trafficmessages.stream().map(message -> message.getVmis_id()).findFirst().get();
	}

	private String determineSources(List<AsfinagTrafficmessage> trafficmessages) {
		String sources = trafficmessages.stream().map(message -> message.getDatex_mse()).distinct()
				.collect(joining(SOURCE_SYMBOL));
		return 255 < sources.length() ? format("{0}...", sources.substring(0, 251)) : sources;
	}

	private String determineEvoSequence(List<AsfinagTrafficmessage> trafficmessage) {
		String evoSequence = trafficmessage.stream().map(message -> message.getDatex_phr())
				.collect(joining(EVO_SYMBOL));
		return 255 < evoSequence.length() ? format("{0}...", evoSequence.substring(0, 251)) : evoSequence;
	}

	private double caluclateDuration(List<AsfinagTrafficmessage> trafficmessages) {
		if (trafficmessages.size() < 2) {
			return 0.0;
		}
		AsfinagTrafficmessage firstTrafficmessage = trafficmessages.get(0);
		AsfinagTrafficmessage lastTrafficmessage = trafficmessages.get(trafficmessages.size() - 1);
		return durationCalculator.calculateDuration(firstTrafficmessage, lastTrafficmessage);
	}

	private int countDatexPhrChanges(List<AsfinagTrafficmessage> trafficmessages) {
		int count = 0;
		for (int i = 1; i < trafficmessages.size(); i++) {
			AsfinagTrafficmessage current = trafficmessages.get(i);
			AsfinagTrafficmessage previous = trafficmessages.get(i - 1);
			if (!previous.getDatex_phr().equals(current.getDatex_phr())) {
				count++;
			}
		}
		return count;
	}

	private int countSpatialEvolutions(List<AsfinagTrafficmessage> trafficmessages) {
		int count = 0;
		for (int i = 1; i < trafficmessages.size(); i++) {
			AsfinagTrafficmessage current = trafficmessages.get(i);
			AsfinagTrafficmessage previous = trafficmessages.get(i - 1);
			if (isSpatialEvolution(previous, current)) {
				count++;
			}
		}
		return count;
	}

	private boolean isSpatialEvolution(AsfinagTrafficmessage trafficmessage1, AsfinagTrafficmessage trafficmessage2) {
		return isSpatialEvolution(trafficmessage1.getBeginmeter(), trafficmessage2.getBeginmeter())
				|| isSpatialEvolution(trafficmessage1.getEndmeter(), trafficmessage2.getEndmeter());
	}

	private boolean isSpatialEvolution(int meter1, int meter2) {
		return !(meter1 == -1 || meter2 == -1) && (meter1 != meter2);
	}

	@Override
	protected AbstractDao<EvolvingObject> getDao() {
		return evolvingObjectDao;
	}
}
