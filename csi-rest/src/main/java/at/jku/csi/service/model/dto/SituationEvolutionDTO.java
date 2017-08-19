package at.jku.csi.service.model.dto;

import java.util.ArrayList;
import java.util.List;

public class SituationEvolutionDTO extends BaseDTO {

	private Integer situation_id;
	private String evolutionSequence;
	private double totalDuration;
	private int evoSteps;
	private int majorEvoSteps;
	private List<StateInstanceDTO> stateInstances = new ArrayList<>();

	public Integer getSituation_id() {
		return situation_id;
	}

	public void setSituation_id(Integer situation_id) {
		this.situation_id = situation_id;
	}

	public String getEvolutionSequence() {
		return evolutionSequence;
	}

	public void setEvolutionSequence(String evolutionSequence) {
		this.evolutionSequence = evolutionSequence;
	}

	public double getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(double totalDuration) {
		this.totalDuration = totalDuration;
	}

	public int getEvoSteps() {
		return evoSteps;
	}

	public void setEvoSteps(int evoSteps) {
		this.evoSteps = evoSteps;
	}

	public int getMajorEvoSteps() {
		return majorEvoSteps;
	}

	public void setMajorEvoSteps(int majorEvoSteps) {
		this.majorEvoSteps = majorEvoSteps;
	}

	public List<StateInstanceDTO> getStateInstances() {
		return stateInstances;
	}

	public void setStateInstances(List<StateInstanceDTO> stateInstance) {
		this.stateInstances = stateInstance;
	}

}
