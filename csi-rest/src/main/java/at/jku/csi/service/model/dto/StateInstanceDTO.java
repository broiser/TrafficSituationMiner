package at.jku.csi.service.model.dto;

import java.util.HashSet;
import java.util.Set;

public class StateInstanceDTO extends BaseDTO {

	private String name;
	private long beginTime;
	private double duration;
	private SituationStateTypeDTO situationStateType;

	private Set<AsfinagTrafficmessageDTO> asfinagTrafficmessages = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public SituationStateTypeDTO getSituationStateType() {
		return situationStateType;
	}

	public void setSituationStateType(SituationStateTypeDTO situationStateType) {
		this.situationStateType = situationStateType;
	}

	public Set<AsfinagTrafficmessageDTO> getAsfinagTrafficmessages() {
		return asfinagTrafficmessages;
	}

	public void setAsfinagTrafficmessages(Set<AsfinagTrafficmessageDTO> asfinagTrafficmessages) {
		this.asfinagTrafficmessages = asfinagTrafficmessages;
	}

}
