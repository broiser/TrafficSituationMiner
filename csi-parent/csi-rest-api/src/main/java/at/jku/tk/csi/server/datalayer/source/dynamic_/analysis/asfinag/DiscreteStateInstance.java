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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import at.jku.tk.csi.entity.BaseEntity;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DiscreteStateInstance")
public class DiscreteStateInstance extends BaseEntity {

	@ManyToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({
			@JoinColumn(name = "SituationEvolutionID", referencedColumnName = "ID", insertable = false, updatable = false) })
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution situationEvolution;

	@ManyToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "SituationStateTypeID", referencedColumnName = "ID") })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType situationStateType;

	@Column(name = "BeginTime", nullable = true)
	private java.sql.Timestamp beginTime;

	@Column(name = "Duration", nullable = false)
	private double duration;

	@Column(name = "Name", nullable = true, length = 255)
	private String name;

	@OneToMany(mappedBy = "discreteStateInstance", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set<StateInstance> stateInstance = new java.util.HashSet<>();

	public void setBeginTime(java.sql.Timestamp value) {
		this.beginTime = value;
	}

	public java.sql.Timestamp getBeginTime() {
		return beginTime;
	}

	public void setDuration(double value) {
		this.duration = value;
	}

	public double getDuration() {
		return duration;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return name;
	}

	public void setSituationEvolution(
			at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution value) {
		this.situationEvolution = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution getSituationEvolution() {
		return situationEvolution;
	}

	public void setStateInstance(java.util.Set<StateInstance> value) {
		this.stateInstance = value;
	}

	public java.util.Set<StateInstance> getStateInstance() {
		return stateInstance;
	}

	public void setSituationStateType(
			at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType value) {
		this.situationStateType = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType getSituationStateType() {
		return situationStateType;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("DiscreteStateInstance[ ");
			sb.append("ID=").append(getID()).append(" ");
			if (getSituationEvolution() != null)
				sb.append("SituationEvolution.Persist_ID=").append(getSituationEvolution().toString(true)).append(" ");
			else
				sb.append("SituationEvolution=null ");
			if (getSituationStateType() != null)
				sb.append("SituationStateType.Persist_ID=").append(getSituationStateType().toString(true)).append(" ");
			else
				sb.append("SituationStateType=null ");
			sb.append("BeginTime=").append(getBeginTime()).append(" ");
			sb.append("Duration=").append(getDuration()).append(" ");
			sb.append("Name=").append(getName()).append(" ");
			sb.append("StateInstance.size=").append(getStateInstance().size()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
