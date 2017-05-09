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
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.jku.tk.csi.entity.BaseEntity;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="EvolvingObject")
public class EvolvingObject extends BaseEntity {
	
	@Column(name="Vmis_id", nullable=false)	
	private int vmis_id;
	
	@Column(name="NrSpatialEvolutions", nullable=false)	
	private int nrSpatialEvolutions;
	
	@Column(name="NrPHRchanges", nullable=false)	
	private int nrPHRchanges;
	
	@Column(name="EvoSequence", nullable=true, length=255)	
	private String evoSequence;
	
	@Column(name="NrUpdates", nullable=false)	
	private int nrUpdates;
	
	@Column(name="Duration", nullable=false)	
	private double duration;
	
	@Column(name="Sources", nullable=true, length=255)	
	private String sources;
	
	@OneToMany(targetEntity=at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="EvolvingObjectID2", nullable=true) })	
	@org.hibernate.annotations.IndexColumn(name="`Column`")	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.List asfinagTrafficmessage = new java.util.ArrayList();
	
	public void setVmis_id(int value) {
		this.vmis_id = value;
	}
	
	public int getVmis_id() {
		return vmis_id;
	}
	
	public void setNrSpatialEvolutions(int value) {
		this.nrSpatialEvolutions = value;
	}
	
	public int getNrSpatialEvolutions() {
		return nrSpatialEvolutions;
	}
	
	public void setNrPHRchanges(int value) {
		this.nrPHRchanges = value;
	}
	
	public int getNrPHRchanges() {
		return nrPHRchanges;
	}
	
	public void setEvoSequence(String value) {
		this.evoSequence = value;
	}
	
	public String getEvoSequence() {
		return evoSequence;
	}
	
	public void setNrUpdates(int value) {
		this.nrUpdates = value;
	}
	
	public int getNrUpdates() {
		return nrUpdates;
	}
	
	public void setDuration(double value) {
		this.duration = value;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public void setSources(String value) {
		this.sources = value;
	}
	
	public String getSources() {
		return sources;
	}
	
	public void setAsfinagTrafficmessage(java.util.List value) {
		this.asfinagTrafficmessage = value;
	}
	
	public java.util.List getAsfinagTrafficmessage() {
		return asfinagTrafficmessage;
	}
	
	
	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("EvolvingObject[ ");
			sb.append("ID=").append(getID()).append(" ");
			sb.append("Vmis_id=").append(getVmis_id()).append(" ");
			sb.append("NrSpatialEvolutions=").append(getNrSpatialEvolutions()).append(" ");
			sb.append("NrPHRchanges=").append(getNrPHRchanges()).append(" ");
			sb.append("EvoSequence=").append(getEvoSequence()).append(" ");
			sb.append("NrUpdates=").append(getNrUpdates()).append(" ");
			sb.append("Duration=").append(getDuration()).append(" ");
			sb.append("Sources=").append(getSources()).append(" ");
			sb.append("AsfinagTrafficmessage.size=").append(getAsfinagTrafficmessage().size()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}
