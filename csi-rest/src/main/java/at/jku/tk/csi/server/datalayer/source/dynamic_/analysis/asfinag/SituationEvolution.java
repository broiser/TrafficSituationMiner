/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.jku.tk.csi.entity.BaseEntity;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "SituationEvolution")
public class SituationEvolution extends BaseEntity {

	@Column(name = "Situation_id", nullable = true)
	private Integer situation_id;

	@Column(name = "EvolutionSequence", nullable = true, length = 255)
	private String evolutionSequence;

	@Column(name = "TotalDuration", nullable = false)
	private double totalDuration;

	@Column(name = "EvoSteps", nullable = false)
	private int evoSteps;

	@Column(name = "MajorEvoSteps", nullable = false)
	private int majorEvoSteps;

	@OneToMany(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "SituationEvolutionID", nullable = true) })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private List<StateInstance> stateInstance = new ArrayList<>();

	@OneToMany(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.DiscreteStateInstance.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "SituationEvolutionID", nullable = true) })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private List<DiscreteStateInstance> discreteStateInstance = new ArrayList<>();

	public void setSituation_id(Integer value) {
		this.situation_id = value;
	}

	public Integer getSituation_id() {
		return situation_id;
	}

	public void setEvolutionSequence(String value) {
		this.evolutionSequence = value;
	}

	public String getEvolutionSequence() {
		return evolutionSequence;
	}

	public void setTotalDuration(double value) {
		this.totalDuration = value;
	}

	public double getTotalDuration() {
		return totalDuration;
	}

	public void setEvoSteps(int value) {
		this.evoSteps = value;
	}

	public int getEvoSteps() {
		return evoSteps;
	}

	public void setMajorEvoSteps(int value) {
		this.majorEvoSteps = value;
	}

	public int getMajorEvoSteps() {
		return majorEvoSteps;
	}

	public void setStateInstance(List<StateInstance> value) {
		this.stateInstance = value;
	}

	public List<StateInstance> getStateInstance() {
		return stateInstance;
	}

	public void setDiscreteStateInstance(List<DiscreteStateInstance> value) {
		this.discreteStateInstance = value;
	}

	public List<DiscreteStateInstance> getDiscreteStateInstance() {
		return discreteStateInstance;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getSituation_id());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("SituationEvolution[ ");
			sb.append("Situation_id=").append(getSituation_id()).append(" ");
			sb.append("EvolutionSequence=").append(getEvolutionSequence()).append(" ");
			sb.append("TotalDuration=").append(getTotalDuration()).append(" ");
			sb.append("EvoSteps=").append(getEvoSteps()).append(" ");
			sb.append("MajorEvoSteps=").append(getMajorEvoSteps()).append(" ");
			sb.append("StateInstance.size=").append(getStateInstance().size()).append(" ");
			sb.append("DiscreteStateInstance.size=").append(getDiscreteStateInstance().size()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
