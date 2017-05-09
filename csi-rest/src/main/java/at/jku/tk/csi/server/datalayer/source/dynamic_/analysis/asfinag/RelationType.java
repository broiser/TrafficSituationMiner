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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import at.jku.tk.csi.entity.BaseEntity;


@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "RelationType")
public class RelationType extends BaseEntity {
	@Column(name = "Name", nullable = true, length = 255)
	private String name;

	@OneToOne(mappedBy = "relationTypeL", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType objectTypeL;

	@OneToOne(mappedBy = "relationTypeR", targetEntity = at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType objectTypeR;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return name;
	}

	public void setObjectTypeL(at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType value) {
		this.objectTypeL = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType getObjectTypeL() {
		return objectTypeL;
	}

	public void setObjectTypeR(at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType value) {
		this.objectTypeR = value;
	}

	public at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType getObjectTypeR() {
		return objectTypeR;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("RelationType[ ");
			sb.append("ID=").append(getID()).append(" ");
			sb.append("Name=").append(getName()).append(" ");
			if (getObjectTypeL() != null)
				sb.append("ObjectTypeL.Persist_ID=").append(getObjectTypeL().toString(true)).append(" ");
			else
				sb.append("ObjectTypeL=null ");
			if (getObjectTypeR() != null)
				sb.append("ObjectTypeR.Persist_ID=").append(getObjectTypeR().toString(true)).append(" ");
			else
				sb.append("ObjectTypeR=null ");
			sb.append("]");
			return sb.toString();
		}
	}

}
