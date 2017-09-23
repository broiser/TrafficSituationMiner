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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import at.jku.tk.csi.entity.BaseEntity;


@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "Transition")
public class Transition extends BaseEntity {

	@ManyToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "TransitionTypeID", referencedColumnName = "ID") })
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType transitionType;

	@OneToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "StateInstanceID", referencedColumnName = "ID", nullable = false) })
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance nextStateInstanceStateinstance;

	@OneToOne(targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "StateInstanceID2", referencedColumnName = "ID", nullable = false) })
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance previousStateInstanceStateinstance;

	public void setTransitionType(
			at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType value) {
		this.transitionType = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TransitionType getTransitionType() {
		return transitionType;
	}

	public void setPreviousStateInstanceStateinstance(
			at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance value) {
		this.previousStateInstanceStateinstance = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance getPreviousStateInstanceStateinstance() {
		return previousStateInstanceStateinstance;
	}

	public void setNextStateInstanceStateinstance(
			at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance value) {
		this.nextStateInstanceStateinstance = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance getNextStateInstanceStateinstance() {
		return nextStateInstanceStateinstance;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("Transition[ ");
			sb.append("ID=").append(getID()).append(" ");
			if (getTransitionType() != null)
				sb.append("TransitionType.Persist_ID=").append(getTransitionType().toString(true)).append(" ");
			else
				sb.append("TransitionType=null ");
			if (getNextStateInstanceStateinstance() != null)
				sb.append("NextStateInstanceStateinstance.Persist_ID=")
						.append(getNextStateInstanceStateinstance().toString(true)).append(" ");
			else
				sb.append("NextStateInstanceStateinstance=null ");
			if (getPreviousStateInstanceStateinstance() != null)
				sb.append("PreviousStateInstanceStateinstance.Persist_ID=")
						.append(getPreviousStateInstanceStateinstance().toString(true)).append(" ");
			else
				sb.append("PreviousStateInstanceStateinstance=null ");
			sb.append("]");
			return sb.toString();
		}
	}

}
