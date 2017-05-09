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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.jku.tk.csi.entity.BaseEntity;


@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "SituationStateType")
public class SituationStateType extends BaseEntity {

	@Column(name = "Name", nullable = true, length = 255)
	private String name;

	@Column(name = "Type", nullable = true, length = 255)
	private String type;

	@OneToMany(mappedBy = "fromSituationStateType", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set fromTransition = new java.util.HashSet();

	@OneToMany(mappedBy = "toSituationStateType", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set toTransition = new java.util.HashSet();

	@OneToMany(mappedBy = "situationStateType", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set stateInstance = new java.util.HashSet();

	@OneToMany(mappedBy = "situationStateType", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.DiscreteStateInstance.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set discreteStateInstance = new java.util.HashSet();

	@OneToMany(mappedBy = "situationStateType", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set objectType = new java.util.HashSet();

	@ManyToMany(mappedBy = "situationStateType", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolutionType.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private java.util.Set situationEvolutionType = new java.util.HashSet();

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return name;
	}

	public void setType(String value) {
		this.type = value;
	}

	public String getType() {
		return type;
	}

	public void setFromTransition(java.util.Set value) {
		this.fromTransition = value;
	}

	public java.util.Set getFromTransition() {
		return fromTransition;
	}

	public void setToTransition(java.util.Set value) {
		this.toTransition = value;
	}

	public java.util.Set getToTransition() {
		return toTransition;
	}

	public void setStateInstance(java.util.Set value) {
		this.stateInstance = value;
	}

	public java.util.Set getStateInstance() {
		return stateInstance;
	}

	public void setDiscreteStateInstance(java.util.Set value) {
		this.discreteStateInstance = value;
	}

	public java.util.Set getDiscreteStateInstance() {
		return discreteStateInstance;
	}

	public void setObjectType(java.util.Set value) {
		this.objectType = value;
	}

	public java.util.Set getObjectType() {
		return objectType;
	}

	public void setSituationEvolutionType(java.util.Set value) {
		this.situationEvolutionType = value;
	}

	public java.util.Set getSituationEvolutionType() {
		return situationEvolutionType;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("SituationStateType[ ");
			sb.append("ID=").append(getID()).append(" ");
			sb.append("Name=").append(getName()).append(" ");
			sb.append("Type=").append(getType()).append(" ");
			sb.append("FromTransition.size=").append(getFromTransition().size()).append(" ");
			sb.append("ToTransition.size=").append(getToTransition().size()).append(" ");
			sb.append("StateInstance.size=").append(getStateInstance().size()).append(" ");
			sb.append("DiscreteStateInstance.size=").append(getDiscreteStateInstance().size()).append(" ");
			sb.append("ObjectType.size=").append(getObjectType().size()).append(" ");
			sb.append("SituationEvolutionType.size=").append(getSituationEvolutionType().size()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
