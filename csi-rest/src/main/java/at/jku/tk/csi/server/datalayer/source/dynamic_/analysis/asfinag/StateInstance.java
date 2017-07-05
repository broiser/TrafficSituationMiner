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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import at.jku.tk.csi.entity.BaseEntity;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "StateInstance")
public class StateInstance extends BaseEntity {

	@ManyToOne(targetEntity = DiscreteStateInstance.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "DiscreteStateInstanceID", referencedColumnName = "ID") })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private DiscreteStateInstance discreteStateInstance;

	@ManyToOne(targetEntity = SituationEvolution.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({
			@JoinColumn(name = "SituationEvolutionID", referencedColumnName = "ID", insertable = false, updatable = false) })
	private SituationEvolution situationEvolution;

	@ManyToOne(targetEntity = SituationStateType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "SituationStateTypeID", referencedColumnName = "ID") })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private SituationStateType situationStateType;

	@Column(name = "BeginTime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date beginTime;

	@Column(name = "Duration", nullable = false)
	private double duration;

	@Column(name = "Name", nullable = true, length = 255)
	private String name;

	@OneToOne(mappedBy = "nextStateInstanceStateinstance", targetEntity = Transition.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	private Transition previousStateInstanceTransition;

	@OneToMany(mappedBy = "stateInstance", targetEntity = AsfinagTrafficmessage.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set<AsfinagTrafficmessage> asfinagTrafficmessage = new java.util.HashSet<>();

	@OneToOne(mappedBy = "previousStateInstanceStateinstance", targetEntity = Transition.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	private Transition nextStateInstanceTransition;

	public void setBeginTime(java.util.Date value) {
		this.beginTime = value;
	}

	public java.util.Date getBeginTime() {
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

	public void setSituationEvolution(SituationEvolution value) {
		this.situationEvolution = value;
	}

	public SituationEvolution getSituationEvolution() {
		return situationEvolution;
	}

	public void setDiscreteStateInstance(DiscreteStateInstance value) {
		this.discreteStateInstance = value;
	}

	public DiscreteStateInstance getDiscreteStateInstance() {
		return discreteStateInstance;
	}

	public StateInstance getPreviousStateInstanceStateinstance() {
		if (previousStateInstanceTransition != null) {
			return previousStateInstanceTransition.getPreviousStateInstanceStateinstance();
		} else {
			return null;
		}
	}

	public void removePreviousStateInstanceStateinstance() {
		if (previousStateInstanceTransition != null) {
			previousStateInstanceTransition.setPreviousStateInstanceStateinstance(null);
			this.setPreviousStateInstanceTransition(null);
		}
	}

	public void addPreviousStateInstanceStateinstance(Transition aPreviousStateInstanceTransition,
			StateInstance aPreviousStateInstanceStateinstance) {
		this.setPreviousStateInstanceTransition(aPreviousStateInstanceTransition);
		aPreviousStateInstanceTransition.setPreviousStateInstanceStateinstance(aPreviousStateInstanceStateinstance);
	}

	public void setPreviousStateInstanceTransition(Transition value) {
		this.previousStateInstanceTransition = value;
	}

	public Transition getPreviousStateInstanceTransition() {
		return previousStateInstanceTransition;
	}

	public void setSituationStateType(SituationStateType value) {
		this.situationStateType = value;
	}

	public SituationStateType getSituationStateType() {
		return situationStateType;
	}

	public void setAsfinagTrafficmessage(java.util.Set<AsfinagTrafficmessage> value) {
		this.asfinagTrafficmessage = value;
	}

	public java.util.Set<AsfinagTrafficmessage> getAsfinagTrafficmessage() {
		return asfinagTrafficmessage;
	}

	public StateInstance getNextStateInstanceStateinstance() {
		if (nextStateInstanceTransition != null) {
			return nextStateInstanceTransition.getNextStateInstanceStateinstance();
		} else {
			return null;
		}
	}

	public void removeNextStateInstanceStateinstance() {
		if (nextStateInstanceTransition != null) {
			nextStateInstanceTransition.setNextStateInstanceStateinstance(null);
			this.setNextStateInstanceTransition(null);
		}
	}

	public void addNextStateInstanceStateinstance(Transition aNextStateInstanceTransition,
			StateInstance aNextStateInstanceStateinstance) {
		this.setNextStateInstanceTransition(aNextStateInstanceTransition);
		aNextStateInstanceTransition.setNextStateInstanceStateinstance(aNextStateInstanceStateinstance);
	}

	public void setNextStateInstanceTransition(Transition value) {
		this.nextStateInstanceTransition = value;
	}

	public Transition getNextStateInstanceTransition() {
		return nextStateInstanceTransition;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("StateInstance[ ");
			sb.append("ID=").append(getID()).append(" ");
			if (getDiscreteStateInstance() != null)
				sb.append("DiscreteStateInstance.Persist_ID=").append(getDiscreteStateInstance().toString(true))
						.append(" ");
			else
				sb.append("DiscreteStateInstance=null ");
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
			if (getPreviousStateInstanceTransition() != null)
				sb.append("PreviousStateInstanceTransition.Persist_ID=")
						.append(getPreviousStateInstanceTransition().toString(true)).append(" ");
			else
				sb.append("PreviousStateInstanceTransition=null ");
			sb.append("AsfinagTrafficmessage.size=").append(getAsfinagTrafficmessage().size()).append(" ");
			if (getNextStateInstanceTransition() != null)
				sb.append("NextStateInstanceTransition.Persist_ID=")
						.append(getNextStateInstanceTransition().toString(true)).append(" ");
			else
				sb.append("NextStateInstanceTransition=null ");
			sb.append("]");
			return sb.toString();
		}
	}
}
