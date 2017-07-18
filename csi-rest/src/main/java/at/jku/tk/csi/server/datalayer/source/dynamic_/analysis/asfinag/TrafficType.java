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
import javax.persistence.Table;

import at.jku.tk.csi.entity.BaseEntity;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "TrafficType")
public class TrafficType extends BaseEntity {

	@Column(name = "PHR", nullable = true, length = 255)
	private String PHR;

	@Column(name = "MessageText", nullable = true, length = 255)
	private String messageText;

	@Column(name = "Type", nullable = true, length = 255)
	private String type;

	public void setPHR(String value) {
		this.PHR = value;
	}

	public String getPHR() {
		return PHR;
	}

	public void setMessageText(String value) {
		this.messageText = value;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setType(String value) {
		this.type = value;
	}

	public String getType() {
		return type;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("TrafficType[ ");
			sb.append("ID=").append(getID()).append(" ");
			sb.append("PHR=").append(getPHR()).append(" ");
			sb.append("MessageText=").append(getMessageText()).append(" ");
			sb.append("Type=").append(getType()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
}
