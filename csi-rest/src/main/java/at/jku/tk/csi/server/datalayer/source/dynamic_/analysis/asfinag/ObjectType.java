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
import javax.persistence.Table;

import at.jku.tk.csi.entity.BaseEntity;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "ObjectType")
public class ObjectType extends BaseEntity {

	@ManyToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TrafficType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "TrafficTypeID", referencedColumnName = "ID", nullable = false) })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TrafficType trafficType;

	@ManyToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "SituationStateTypeID", referencedColumnName = "ID") })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType situationStateType;

	@ManyToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "RelationTypeID2", referencedColumnName = "ID", nullable = false) })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType relationTypeR;

	@ManyToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "RelationTypeID", referencedColumnName = "ID", nullable = false) })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType relationTypeL;

	@Column(name = "Alias", nullable = true, length = 255)
	private String alias;

	public void setAlias(String value) {
		this.alias = value;
	}

	public String getAlias() {
		return alias;
	}

	public void setRelationTypeL(at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType value) {
		this.relationTypeL = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType getRelationTypeL() {
		return relationTypeL;
	}

	public void setRelationTypeR(at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType value) {
		this.relationTypeR = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.RelationType getRelationTypeR() {
		return relationTypeR;
	}

	public void setSituationStateType(
			at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType value) {
		this.situationStateType = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationStateType getSituationStateType() {
		return situationStateType;
	}

	public void setTrafficType(at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TrafficType value) {
		this.trafficType = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TrafficType getTrafficType() {
		return trafficType;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("ObjectType[ ");
			sb.append("ID=").append(getID()).append(" ");
			if (getTrafficType() != null)
				sb.append("TrafficType.Persist_ID=").append(getTrafficType().toString(true)).append(" ");
			else
				sb.append("TrafficType=null ");
			if (getSituationStateType() != null)
				sb.append("SituationStateType.Persist_ID=").append(getSituationStateType().toString(true)).append(" ");
			else
				sb.append("SituationStateType=null ");
			if (getRelationTypeR() != null)
				sb.append("RelationTypeR.Persist_ID=").append(getRelationTypeR().toString(true)).append(" ");
			else
				sb.append("RelationTypeR=null ");
			if (getRelationTypeL() != null)
				sb.append("RelationTypeL.Persist_ID=").append(getRelationTypeL().toString(true)).append(" ");
			else
				sb.append("RelationTypeL=null ");
			sb.append("Alias=").append(getAlias()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
}
