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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import at.jku.tk.csi.entity.BaseEntity;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "SituationEvolutionType")
public class SituationEvolutionType extends BaseEntity {

	@Column(name = "Name", nullable = true, length = 255)
	private String name;

	@ManyToMany(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@JoinTable(name = "SituationStateType_SituationEvolutionType", joinColumns = {
			@JoinColumn(name = "SituationEvolutionTypeID") }, inverseJoinColumns = {
					@JoinColumn(name = "SituationStateTypeID") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set situationStateType = new java.util.HashSet();

	@ManyToMany(mappedBy = "situationEvolutionType", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set transitionType = new java.util.HashSet();

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return name;
	}

	public void setSituationStateType(java.util.Set value) {
		this.situationStateType = value;
	}

	public java.util.Set getSituationStateType() {
		return situationStateType;
	}

	public void setTransitionType(java.util.Set value) {
		this.transitionType = value;
	}

	public java.util.Set getTransitionType() {
		return transitionType;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("SituationEvolutionType[ ");
			sb.append("ID=").append(getID()).append(" ");
			sb.append("Name=").append(getName()).append(" ");
			sb.append("SituationStateType.size=").append(getSituationStateType().size()).append(" ");
			sb.append("TransitionType.size=").append(getTransitionType().size()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
