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
package at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import at.jku.tk.csi.entity.BaseEntity;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.EvolvingObject;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "AsfinagTrafficmessage")
public class AsfinagTrafficmessage extends BaseEntity {
	public AsfinagTrafficmessage() {
	}

	@ManyToOne(targetEntity = EvolvingObject.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "EvolvingObjectID", referencedColumnName = "ID") })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private EvolvingObject evolvingObject;

	@ManyToOne(targetEntity = StateInstance.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "StateInstanceID", referencedColumnName = "ID") })
	@org.hibernate.annotations.LazyToOne(value = org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	private StateInstance stateInstance;

	@Column(name = "Act_state", nullable = true)
	private Integer act_state;

	@Column(name = "Adm", nullable = true, length = 255)
	private String adm;

	@Column(name = "Beginmeter", nullable = true)
	private Integer beginmeter;

	@Column(name = "Begintime", nullable = true)
	private java.sql.Timestamp begintime;

	@Column(name = "Bl_code", nullable = true, length = 255)

	private String bl_code;
	@Column(name = "Bl_id", nullable = true, length = 255)

	private String bl_id;
	@Column(name = "Datex_cs_state", nullable = true, length = 255)

	private String datex_cs_state;
	@Column(name = "Datex_dob", nullable = true, length = 255)

	private String datex_dob;
	@Column(name = "Datex_dst", nullable = true, length = 255)

	private String datex_dst;
	@Column(name = "Datex_inp", nullable = true)
	private Integer datex_inp;

	@Column(name = "Datex_mse", nullable = true, length = 255)
	private String datex_mse;

	@Column(name = "Datex_phq", nullable = true, length = 255)
	private String datex_phq;

	@Column(name = "Datex_phr", nullable = true, length = 255)

	private String datex_phr;
	@Column(name = "Datex_rll", nullable = true)
	private Integer datex_rll;

	@Column(name = "Datex_sta", nullable = true)
	private Integer datex_sta;

	@Column(name = "Datex_sto", nullable = true)
	private Integer datex_sto;

	@Column(name = "Description", nullable = true, length = 255)
	private String description;

	@Column(name = "Elementtype", nullable = true, length = 255)
	private String elementtype;

	@Column(name = "Endmeter", nullable = true)
	private Integer endmeter;

	@Column(name = "Endtime", nullable = true)
	private java.sql.Timestamp endtime;

	@Column(name = "Entrytime", nullable = true)
	private java.sql.Timestamp entrytime;

	@Column(name = "Geo_id", nullable = true)
	private Integer geo_id;

	@Column(name = "Geometrytype", nullable = true, length = 255)
	private String geometrytype;

	@Column(name = "Geoobject_description", nullable = true, length = 255)
	private String geoobject_description;

	@Column(name = "Geotype", nullable = true)
	private Integer geotype;

	@Column(name = "Lambeginpointx", nullable = true)
	private Double lambeginpointx;

	@Column(name = "Lambeginpointy", nullable = true)
	private Double lambeginpointy;

	@Column(name = "Lambeginwindowx", nullable = true)
	private Double lambeginwindowx;

	@Column(name = "Lambeginwindowy", nullable = true)
	private Double lambeginwindowy;

	@Column(name = "Lamendpointx", nullable = true)
	private Double lamendpointx;

	@Column(name = "Lamendpointy", nullable = true)
	private Double lamendpointy;

	@Column(name = "Lamendwindowx", nullable = true)
	private Double lamendwindowx;

	@Column(name = "Lamendwindowy", nullable = true)
	private Double lamendwindowy;

	@Column(name = "Message_id", nullable = true)
	private Integer message_id;

	@Column(name = "Messagetext", nullable = true, length = 255)
	private String messagetext;

	@Column(name = "Next_geo_id", nullable = true)
	private Integer next_geo_id;

	@Column(name = "Next_mess_id", nullable = true)
	private Integer next_mess_id;

	@Column(name = "Parent_id", nullable = true)
	private Integer parent_id;

	@Column(name = "Picurl", nullable = true, length = 255)
	private String picurl;

	@Column(name = "Primlocation_code", nullable = true)
	private Integer primlocation_code;

	@Column(name = "Primlocation_offset", nullable = true)
	private Integer primlocation_offset;

	@Column(name = "Primlocation_text", nullable = true, length = 255)
	private String primlocation_text;
	@Column(name = "Province", nullable = true, length = 255)
	private String province;

	@Column(name = "Road_code", nullable = true, length = 255)
	private String road_code;

	@Column(name = "Road_direction", nullable = true)
	private Integer road_direction;

	@Column(name = "Road_id", nullable = true)
	private Integer road_id;

	@Column(name = "Roadname", nullable = true, length = 255)
	private String roadname;

	@Column(name = "Seclocation_code", nullable = true)
	private Integer seclocation_code;

	@Column(name = "Seclocation_offset", nullable = true)
	private Integer seclocation_offset;

	@Column(name = "Seclocation_text", nullable = true, length = 255)
	private String seclocation_text;

	@Column(name = "Situation_id", nullable = true)
	private Integer situation_id;

	@Column(name = "Strasse_id", nullable = true)
	private Integer strasse_id;

	@Column(name = "Strasse_name", nullable = true, length = 255)
	private String strasse_name;

	@Column(name = "Strasse_titel", nullable = true, length = 255)
	private String strasse_titel;

	@Column(name = "Table_reference", nullable = true, length = 255)
	private String table_reference;

	@Column(name = "Table_version", nullable = true)
	private Integer table_version;

	@Column(name = "Update_time", nullable = true)
	private java.sql.Timestamp update_time;

	@Column(name = "Validtime", nullable = true)
	private java.sql.Timestamp validtime;

	@Column(name = "Vmis_id", nullable = true)
	private Integer vmis_id;

	@Column(name = "Wgs84beginpointx", nullable = true)
	private Double wgs84beginpointx;

	@Column(name = "Wgs84beginpointy", nullable = true)
	private Double wgs84beginpointy;

	@Column(name = "Wgs84beginwindowx", nullable = true)
	private Double wgs84beginwindowx;

	@Column(name = "Wgs84beginwindowy", nullable = true)
	private Double wgs84beginwindowy;

	@Column(name = "Wgs84endpointx", nullable = true)
	private Double wgs84endpointx;

	@Column(name = "Wgs84endpointy", nullable = true)
	private Double wgs84endpointy;

	@Column(name = "Wgs84endwindowx", nullable = true)
	private Double wgs84endwindowx;

	@Column(name = "Wgs84endwindowy", nullable = true)
	private Double wgs84endwindowy;

	@Column(name = "Zoomurl", nullable = true, length = 255)
	private String zoomurl;

	@Column(name = "ImportDate", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date importDate;

	public void setImportDate(java.util.Date value) {
		this.importDate = value;
	}

	public java.util.Date getImportDate() {
		return importDate;
	}

	public void setLamendpointy(double value) {
		setLamendpointy(new Double(value));
	}

	public void setLamendpointy(Double value) {
		this.lamendpointy = value;
	}

	public Double getLamendpointy() {
		return lamendpointy;
	}

	public void setLamendwindowx(double value) {
		setLamendwindowx(new Double(value));
	}

	public void setLamendwindowx(Double value) {
		this.lamendwindowx = value;
	}

	public Double getLamendwindowx() {
		return lamendwindowx;
	}

	public void setLamendwindowy(double value) {
		setLamendwindowy(new Double(value));
	}

	public void setLamendwindowy(Double value) {
		this.lamendwindowy = value;
	}

	public Double getLamendwindowy() {
		return lamendwindowy;
	}

	public void setWgs84beginpointx(double value) {
		setWgs84beginpointx(new Double(value));
	}

	public void setWgs84beginpointx(Double value) {
		this.wgs84beginpointx = value;
	}

	public Double getWgs84beginpointx() {
		return wgs84beginpointx;
	}

	public void setWgs84beginpointy(double value) {
		setWgs84beginpointy(new Double(value));
	}

	public void setWgs84beginpointy(Double value) {
		this.wgs84beginpointy = value;
	}

	public Double getWgs84beginpointy() {
		return wgs84beginpointy;
	}

	public void setWgs84beginwindowx(double value) {
		setWgs84beginwindowx(new Double(value));
	}

	public void setWgs84beginwindowx(Double value) {
		this.wgs84beginwindowx = value;
	}

	public Double getWgs84beginwindowx() {
		return wgs84beginwindowx;
	}

	public void setWgs84beginwindowy(double value) {
		setWgs84beginwindowy(new Double(value));
	}

	public void setWgs84beginwindowy(Double value) {
		this.wgs84beginwindowy = value;
	}

	public Double getWgs84beginwindowy() {
		return wgs84beginwindowy;
	}

	public void setWgs84endpointx(double value) {
		setWgs84endpointx(new Double(value));
	}

	public void setWgs84endpointx(Double value) {
		this.wgs84endpointx = value;
	}

	public Double getWgs84endpointx() {
		return wgs84endpointx;
	}

	public void setWgs84endpointy(double value) {
		setWgs84endpointy(new Double(value));
	}

	public void setWgs84endpointy(Double value) {
		this.wgs84endpointy = value;
	}

	public Double getWgs84endpointy() {
		return wgs84endpointy;
	}

	public void setWgs84endwindowx(double value) {
		setWgs84endwindowx(new Double(value));
	}

	public void setWgs84endwindowx(Double value) {
		this.wgs84endwindowx = value;
	}

	public Double getWgs84endwindowx() {
		return wgs84endwindowx;
	}

	public void setWgs84endwindowy(double value) {
		setWgs84endwindowy(new Double(value));
	}

	public void setWgs84endwindowy(Double value) {
		this.wgs84endwindowy = value;
	}

	public Double getWgs84endwindowy() {
		return wgs84endwindowy;
	}

	public void setLambeginpointx(double value) {
		setLambeginpointx(new Double(value));
	}

	public void setLambeginpointx(Double value) {
		this.lambeginpointx = value;
	}

	public Double getLambeginpointx() {
		return lambeginpointx;
	}

	public void setLambeginpointy(double value) {
		setLambeginpointy(new Double(value));
	}

	public void setLambeginpointy(Double value) {
		this.lambeginpointy = value;
	}

	public Double getLambeginpointy() {
		return lambeginpointy;
	}

	public void setLambeginwindowx(double value) {
		setLambeginwindowx(new Double(value));
	}

	public void setLambeginwindowx(Double value) {
		this.lambeginwindowx = value;
	}

	public Double getLambeginwindowx() {
		return lambeginwindowx;
	}

	public void setLambeginwindowy(double value) {
		setLambeginwindowy(new Double(value));
	}

	public void setLambeginwindowy(Double value) {
		this.lambeginwindowy = value;
	}

	public Double getLambeginwindowy() {
		return lambeginwindowy;
	}

	public void setLamendpointx(double value) {
		setLamendpointx(new Double(value));
	}

	public void setLamendpointx(Double value) {
		this.lamendpointx = value;
	}

	public Double getLamendpointx() {
		return lamendpointx;
	}

	public int getORMID() {
		return getID();
	}

	public void setAct_state(int value) {
		setAct_state(new Integer(value));
	}

	public void setAct_state(Integer value) {
		this.act_state = value;
	}

	public Integer getAct_state() {
		return act_state;
	}

	public void setBeginmeter(int value) {
		setBeginmeter(new Integer(value));
	}

	public void setBeginmeter(Integer value) {
		this.beginmeter = value;
	}

	public Integer getBeginmeter() {
		return beginmeter;
	}

	public void setDatex_inp(int value) {
		setDatex_inp(new Integer(value));
	}

	public void setDatex_inp(Integer value) {
		this.datex_inp = value;
	}

	public Integer getDatex_inp() {
		return datex_inp;
	}

	public void setDatex_rll(int value) {
		setDatex_rll(new Integer(value));
	}

	public void setDatex_rll(Integer value) {
		this.datex_rll = value;
	}

	public Integer getDatex_rll() {
		return datex_rll;
	}

	public void setDatex_sta(int value) {
		setDatex_sta(new Integer(value));
	}

	public void setDatex_sta(Integer value) {
		this.datex_sta = value;
	}

	public Integer getDatex_sta() {
		return datex_sta;
	}

	public void setDatex_sto(int value) {
		setDatex_sto(new Integer(value));
	}

	public void setDatex_sto(Integer value) {
		this.datex_sto = value;
	}

	public Integer getDatex_sto() {
		return datex_sto;
	}

	public void setEndmeter(int value) {
		setEndmeter(new Integer(value));
	}

	public void setEndmeter(Integer value) {
		this.endmeter = value;
	}

	public Integer getEndmeter() {
		return endmeter;
	}

	public void setGeo_id(int value) {
		setGeo_id(new Integer(value));
	}

	public void setGeo_id(Integer value) {
		this.geo_id = value;
	}

	public Integer getGeo_id() {
		return geo_id;
	}

	public void setGeotype(int value) {
		setGeotype(new Integer(value));
	}

	public void setGeotype(Integer value) {
		this.geotype = value;
	}

	public Integer getGeotype() {
		return geotype;
	}

	public void setMessage_id(int value) {
		setMessage_id(new Integer(value));
	}

	public void setMessage_id(Integer value) {
		this.message_id = value;
	}

	public Integer getMessage_id() {
		return message_id;
	}

	public void setNext_geo_id(int value) {
		setNext_geo_id(new Integer(value));
	}

	public void setNext_geo_id(Integer value) {
		this.next_geo_id = value;
	}

	public Integer getNext_geo_id() {
		return next_geo_id;
	}

	public void setNext_mess_id(int value) {
		setNext_mess_id(new Integer(value));
	}

	public void setNext_mess_id(Integer value) {
		this.next_mess_id = value;
	}

	public Integer getNext_mess_id() {
		return next_mess_id;
	}

	public void setParent_id(int value) {
		setParent_id(new Integer(value));
	}

	public void setParent_id(Integer value) {
		this.parent_id = value;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setPrimlocation_code(int value) {
		setPrimlocation_code(new Integer(value));
	}

	public void setPrimlocation_code(Integer value) {
		this.primlocation_code = value;
	}

	public Integer getPrimlocation_code() {
		return primlocation_code;
	}

	public void setPrimlocation_offset(int value) {
		setPrimlocation_offset(new Integer(value));
	}

	public void setPrimlocation_offset(Integer value) {
		this.primlocation_offset = value;
	}

	public Integer getPrimlocation_offset() {
		return primlocation_offset;
	}

	public void setRoad_direction(int value) {
		setRoad_direction(new Integer(value));
	}

	public void setRoad_direction(Integer value) {
		this.road_direction = value;
	}

	public Integer getRoad_direction() {
		return road_direction;
	}

	public void setRoad_id(int value) {
		setRoad_id(new Integer(value));
	}

	public void setRoad_id(Integer value) {
		this.road_id = value;
	}

	public Integer getRoad_id() {
		return road_id;
	}

	public void setSeclocation_code(int value) {
		setSeclocation_code(new Integer(value));
	}

	public void setSeclocation_code(Integer value) {
		this.seclocation_code = value;
	}

	public Integer getSeclocation_code() {
		return seclocation_code;
	}

	public void setSeclocation_offset(int value) {
		setSeclocation_offset(new Integer(value));
	}

	public void setSeclocation_offset(Integer value) {
		this.seclocation_offset = value;
	}

	public Integer getSeclocation_offset() {
		return seclocation_offset;
	}

	public void setSituation_id(int value) {
		setSituation_id(new Integer(value));
	}

	public void setSituation_id(Integer value) {
		this.situation_id = value;
	}

	public Integer getSituation_id() {
		return situation_id;
	}

	public void setStrasse_id(int value) {
		setStrasse_id(new Integer(value));
	}

	public void setStrasse_id(Integer value) {
		this.strasse_id = value;
	}

	public Integer getStrasse_id() {
		return strasse_id;
	}

	public void setTable_version(int value) {
		setTable_version(new Integer(value));
	}

	public void setTable_version(Integer value) {
		this.table_version = value;
	}

	public Integer getTable_version() {
		return table_version;
	}

	public void setVmis_id(int value) {
		setVmis_id(new Integer(value));
	}

	public void setVmis_id(Integer value) {
		this.vmis_id = value;
	}

	public Integer getVmis_id() {
		return vmis_id;
	}

	public void setAdm(String value) {
		this.adm = value;
	}

	public String getAdm() {
		return adm;
	}

	public void setBl_code(String value) {
		this.bl_code = value;
	}

	public String getBl_code() {
		return bl_code;
	}

	public void setBl_id(String value) {
		this.bl_id = value;
	}

	public String getBl_id() {
		return bl_id;
	}

	public void setDatex_cs_state(String value) {
		this.datex_cs_state = value;
	}

	public String getDatex_cs_state() {
		return datex_cs_state;
	}

	public void setDatex_dob(String value) {
		this.datex_dob = value;
	}

	public String getDatex_dob() {
		return datex_dob;
	}

	public void setDatex_dst(String value) {
		this.datex_dst = value;
	}

	public String getDatex_dst() {
		return datex_dst;
	}

	public void setDatex_mse(String value) {
		this.datex_mse = value;
	}

	public String getDatex_mse() {
		return datex_mse;
	}

	public void setDatex_phq(String value) {
		this.datex_phq = value;
	}

	public String getDatex_phq() {
		return datex_phq;
	}

	public void setDatex_phr(String value) {
		this.datex_phr = value;
	}

	public String getDatex_phr() {
		return datex_phr;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return description;
	}

	public void setElementtype(String value) {
		this.elementtype = value;
	}

	public String getElementtype() {
		return elementtype;
	}

	public void setGeometrytype(String value) {
		this.geometrytype = value;
	}

	public String getGeometrytype() {
		return geometrytype;
	}

	public void setGeoobject_description(String value) {
		this.geoobject_description = value;
	}

	public String getGeoobject_description() {
		return geoobject_description;
	}

	public void setMessagetext(String value) {
		this.messagetext = value;
	}

	public String getMessagetext() {
		return messagetext;
	}

	public void setPicurl(String value) {
		this.picurl = value;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPrimlocation_text(String value) {
		this.primlocation_text = value;
	}

	public String getPrimlocation_text() {
		return primlocation_text;
	}

	public void setProvince(String value) {
		this.province = value;
	}

	public String getProvince() {
		return province;
	}

	public void setRoad_code(String value) {
		this.road_code = value;
	}

	public String getRoad_code() {
		return road_code;
	}

	public void setRoadname(String value) {
		this.roadname = value;
	}

	public String getRoadname() {
		return roadname;
	}

	public void setSeclocation_text(String value) {
		this.seclocation_text = value;
	}

	public String getSeclocation_text() {
		return seclocation_text;
	}

	public void setStrasse_name(String value) {
		this.strasse_name = value;
	}

	public String getStrasse_name() {
		return strasse_name;
	}

	public void setStrasse_titel(String value) {
		this.strasse_titel = value;
	}

	public String getStrasse_titel() {
		return strasse_titel;
	}

	public void setTable_reference(String value) {
		this.table_reference = value;
	}

	public String getTable_reference() {
		return table_reference;
	}

	public void setZoomurl(String value) {
		this.zoomurl = value;
	}

	public String getZoomurl() {
		return zoomurl;
	}

	public void setBegintime(java.sql.Timestamp value) {
		this.begintime = value;
	}

	public java.sql.Timestamp getBegintime() {
		return begintime;
	}

	public void setEndtime(java.sql.Timestamp value) {
		this.endtime = value;
	}

	public java.sql.Timestamp getEndtime() {
		return endtime;
	}

	public void setEntrytime(java.sql.Timestamp value) {
		this.entrytime = value;
	}

	public java.sql.Timestamp getEntrytime() {
		return entrytime;
	}

	public void setUpdate_time(java.sql.Timestamp value) {
		this.update_time = value;
	}

	public java.sql.Timestamp getUpdate_time() {
		return update_time;
	}

	public void setValidtime(java.sql.Timestamp value) {
		this.validtime = value;
	}

	public java.sql.Timestamp getValidtime() {
		return validtime;
	}

	public void setStateInstance(StateInstance value) {
		this.stateInstance = value;
	}

	public StateInstance getStateInstance() {
		return stateInstance;
	}

	public void setEvolvingObject(EvolvingObject value) {
		this.evolvingObject = value;
	}

	public EvolvingObject getEvolvingObject() {
		return evolvingObject;
	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getID());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("AsfinagTrafficmessage[ ");
			sb.append("ID=").append(getID()).append(" ");
			if (getEvolvingObject() != null)
				sb.append("EvolvingObject.Persist_ID=").append(getEvolvingObject().toString(true)).append(" ");
			else
				sb.append("EvolvingObject=null ");
			if (getStateInstance() != null)
				sb.append("StateInstance.Persist_ID=").append(getStateInstance().toString(true)).append(" ");
			else
				sb.append("StateInstance=null ");
			sb.append("Act_state=").append(getAct_state()).append(" ");
			sb.append("Adm=").append(getAdm()).append(" ");
			sb.append("Beginmeter=").append(getBeginmeter()).append(" ");
			sb.append("Begintime=").append(getBegintime()).append(" ");
			sb.append("Bl_code=").append(getBl_code()).append(" ");
			sb.append("Bl_id=").append(getBl_id()).append(" ");
			sb.append("Datex_cs_state=").append(getDatex_cs_state()).append(" ");
			sb.append("Datex_dob=").append(getDatex_dob()).append(" ");
			sb.append("Datex_dst=").append(getDatex_dst()).append(" ");
			sb.append("Datex_inp=").append(getDatex_inp()).append(" ");
			sb.append("Datex_mse=").append(getDatex_mse()).append(" ");
			sb.append("Datex_phq=").append(getDatex_phq()).append(" ");
			sb.append("Datex_phr=").append(getDatex_phr()).append(" ");
			sb.append("Datex_rll=").append(getDatex_rll()).append(" ");
			sb.append("Datex_sta=").append(getDatex_sta()).append(" ");
			sb.append("Datex_sto=").append(getDatex_sto()).append(" ");
			sb.append("Description=").append(getDescription()).append(" ");
			sb.append("Elementtype=").append(getElementtype()).append(" ");
			sb.append("Endmeter=").append(getEndmeter()).append(" ");
			sb.append("Endtime=").append(getEndtime()).append(" ");
			sb.append("Entrytime=").append(getEntrytime()).append(" ");
			sb.append("Geo_id=").append(getGeo_id()).append(" ");
			sb.append("Geometrytype=").append(getGeometrytype()).append(" ");
			sb.append("Geoobject_description=").append(getGeoobject_description()).append(" ");
			sb.append("Geotype=").append(getGeotype()).append(" ");
			sb.append("Lambeginpointx=").append(getLambeginpointx()).append(" ");
			sb.append("Lambeginpointy=").append(getLambeginpointy()).append(" ");
			sb.append("Lambeginwindowx=").append(getLambeginwindowx()).append(" ");
			sb.append("Lambeginwindowy=").append(getLambeginwindowy()).append(" ");
			sb.append("Lamendpointx=").append(getLamendpointx()).append(" ");
			sb.append("Lamendpointy=").append(getLamendpointy()).append(" ");
			sb.append("Lamendwindowx=").append(getLamendwindowx()).append(" ");
			sb.append("Lamendwindowy=").append(getLamendwindowy()).append(" ");
			sb.append("Message_id=").append(getMessage_id()).append(" ");
			sb.append("Messagetext=").append(getMessagetext()).append(" ");
			sb.append("Next_geo_id=").append(getNext_geo_id()).append(" ");
			sb.append("Next_mess_id=").append(getNext_mess_id()).append(" ");
			sb.append("Parent_id=").append(getParent_id()).append(" ");
			sb.append("Picurl=").append(getPicurl()).append(" ");
			sb.append("Primlocation_code=").append(getPrimlocation_code()).append(" ");
			sb.append("Primlocation_offset=").append(getPrimlocation_offset()).append(" ");
			sb.append("Primlocation_text=").append(getPrimlocation_text()).append(" ");
			sb.append("Province=").append(getProvince()).append(" ");
			sb.append("Road_code=").append(getRoad_code()).append(" ");
			sb.append("Road_direction=").append(getRoad_direction()).append(" ");
			sb.append("Road_id=").append(getRoad_id()).append(" ");
			sb.append("Roadname=").append(getRoadname()).append(" ");
			sb.append("Seclocation_code=").append(getSeclocation_code()).append(" ");
			sb.append("Seclocation_offset=").append(getSeclocation_offset()).append(" ");
			sb.append("Seclocation_text=").append(getSeclocation_text()).append(" ");
			sb.append("Situation_id=").append(getSituation_id()).append(" ");
			sb.append("Strasse_id=").append(getStrasse_id()).append(" ");
			sb.append("Strasse_name=").append(getStrasse_name()).append(" ");
			sb.append("Strasse_titel=").append(getStrasse_titel()).append(" ");
			sb.append("Table_reference=").append(getTable_reference()).append(" ");
			sb.append("Table_version=").append(getTable_version()).append(" ");
			sb.append("Update_time=").append(getUpdate_time()).append(" ");
			sb.append("Validtime=").append(getValidtime()).append(" ");
			sb.append("Vmis_id=").append(getVmis_id()).append(" ");
			sb.append("Wgs84beginpointx=").append(getWgs84beginpointx()).append(" ");
			sb.append("Wgs84beginpointy=").append(getWgs84beginpointy()).append(" ");
			sb.append("Wgs84beginwindowx=").append(getWgs84beginwindowx()).append(" ");
			sb.append("Wgs84beginwindowy=").append(getWgs84beginwindowy()).append(" ");
			sb.append("Wgs84endpointx=").append(getWgs84endpointx()).append(" ");
			sb.append("Wgs84endpointy=").append(getWgs84endpointy()).append(" ");
			sb.append("Wgs84endwindowx=").append(getWgs84endwindowx()).append(" ");
			sb.append("Wgs84endwindowy=").append(getWgs84endwindowy()).append(" ");
			sb.append("Zoomurl=").append(getZoomurl()).append(" ");
			sb.append("ImportDate=").append(getImportDate()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
