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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.jku.tk.csi.entity.BaseEntity;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "TransitionType")
public class TransitionType extends BaseEntity {

	@ManyToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "SituationStateTypeID2", referencedColumnName = "ID") })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType toSituationStateType;

	@ManyToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "SituationStateTypeID", referencedColumnName = "ID") })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType fromSituationStateType;

	@Column(name = "TransitionSequence", nullable = true, length = 255)
	private String transitionSequence;

	@OneToMany(mappedBy = "transitionType", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.Transition.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set<Transition> transition = new java.util.HashSet<>();

	@ManyToMany(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolutionType.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@JoinTable(name = "SituationEvolutionType_TransitionType", joinColumns = {
			@JoinColumn(name = "TransitionTypeID") }, inverseJoinColumns = {
					@JoinColumn(name = "SituationEvolutionTypeID") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set<SituationEvolutionType> situationEvolutionType = new java.util.HashSet<>();

	public void setTransitionSequence(String value) {
		this.transitionSequence = value;
	}

	public String getTransitionSequence() {
		return transitionSequence;
	}

	public void setTransition(java.util.Set value) {
		this.transition = value;
	}

	public java.util.Set getTransition() {
		return transition;
	}

	public void setSituationEvolutionType(java.util.Set value) {
		this.situationEvolutionType = value;
	}

	public java.util.Set getSituationEvolutionType() {
		return situationEvolutionType;
	}

	public void setFromSituationStateType(
			at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType value) {
		this.fromSituationStateType = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType getFromSituationStateType() {
		return fromSituationStateType;
	}

	public void setToSituationStateType(
			at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType value) {
		this.toSituationStateType = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType getToSituationStateType() {
		return toSituationStateType;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("TransitionType[ ");
			sb.append("ID=").append(getID()).append(" ");
			if (getToSituationStateType() != null)
				sb.append("ToSituationStateType.Persist_ID=").append(getToSituationStateType().toString(true))
						.append(" ");
			else
				sb.append("ToSituationStateType=null ");
			if (getFromSituationStateType() != null)
				sb.append("FromSituationStateType.Persist_ID=").append(getFromSituationStateType().toString(true))
						.append(" ");
			else
				sb.append("FromSituationStateType=null ");
			sb.append("TransitionSequence=").append(getTransitionSequence()).append(" ");
			sb.append("Transition.size=").append(getTransition().size()).append(" ");
			sb.append("SituationEvolutionType.size=").append(getSituationEvolutionType().size()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
