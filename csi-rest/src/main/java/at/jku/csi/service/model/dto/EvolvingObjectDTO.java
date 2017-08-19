package at.jku.csi.service.model.dto;

import java.util.HashSet;
import java.util.Set;

public class EvolvingObjectDTO extends BaseDTO{

	private int vmis_id;
	private int nrSpatialEvolutions;
	private int nrPHRchanges;
	private int nrUpdates;
	private double duration;
	private String sources;
	private String evoSequence;
	private Set<AsfinagTrafficmessageDTO> asfinagTrafficmessages = new HashSet<AsfinagTrafficmessageDTO>();

	public int getVmis_id() {
		return vmis_id;
	}

	public void setVmis_id(int vmis_id) {
		this.vmis_id = vmis_id;
	}

	public int getNrSpatialEvolutions() {
		return nrSpatialEvolutions;
	}

	public void setNrSpatialEvolutions(int nrSpatialEvolutions) {
		this.nrSpatialEvolutions = nrSpatialEvolutions;
	}

	public int getNrPHRchanges() {
		return nrPHRchanges;
	}

	public void setNrPHRchanges(int nrPHRchanges) {
		this.nrPHRchanges = nrPHRchanges;
	}

	public String getEvoSequence() {
		return evoSequence;
	}

	public void setEvoSequence(String evoSequence) {
		this.evoSequence = evoSequence;
	}

	public int getNrUpdates() {
		return nrUpdates;
	}

	public void setNrUpdates(int nrUpdates) {
		this.nrUpdates = nrUpdates;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public Set<AsfinagTrafficmessageDTO> getAsfinagTrafficmessages() {
		return asfinagTrafficmessages;
	}

	public void setAsfinagTrafficmessages(Set<AsfinagTrafficmessageDTO> asfinagTrafficmessages) {
		this.asfinagTrafficmessages = asfinagTrafficmessages;
	}
}
