package at.jku.csi.service.model.dto;

public class AsfinagTrafficmessageDTO extends BaseDTO {
	private Long begintime;
	private Long endtime;
	private Integer beginmeter;
	private Integer endmeter;
	private String datex_mse;
	private String datex_dst;
	private String datex_phr;
	private Integer message_id;
	private String messageText;

	private Integer vmis_id;

	private Integer road_id;
	private String road_code;
	private Integer road_direction;
	private String roadname;

	private Long importDate;
	private Long updateDate;

	public Long getBegintime() {
		return begintime;
	}

	public void setBegintime(Long begintime) {
		this.begintime = begintime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public Integer getBeginmeter() {
		return beginmeter;
	}

	public void setBeginmeter(Integer beginmeter) {
		this.beginmeter = beginmeter;
	}

	public Integer getEndmeter() {
		return endmeter;
	}

	public void setEndmeter(Integer endmeter) {
		this.endmeter = endmeter;
	}

	public String getDatex_mse() {
		return datex_mse;
	}

	public void setDatex_mse(String datex_mse) {
		this.datex_mse = datex_mse;
	}

	public String getDatex_dst() {
		return datex_dst;
	}

	public void setDatex_dst(String datex_dst) {
		this.datex_dst = datex_dst;
	}

	public String getDatex_phr() {
		return datex_phr;
	}

	public void setDatex_phr(String datex_phr) {
		this.datex_phr = datex_phr;
	}

	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Integer getVmis_id() {
		return vmis_id;
	}

	public void setVmis_id(Integer vmis_id) {
		this.vmis_id = vmis_id;
	}

	public Integer getRoad_id() {
		return road_id;
	}

	public void setRoad_id(Integer road_id) {
		this.road_id = road_id;
	}

	public String getRoad_code() {
		return road_code;
	}

	public void setRoad_code(String road_code) {
		this.road_code = road_code;
	}

	public Integer getRoad_direction() {
		return road_direction;
	}

	public void setRoad_direction(Integer road_direction) {
		this.road_direction = road_direction;
	}

	public String getRoadname() {
		return roadname;
	}

	public void setRoadname(String roadname) {
		this.roadname = roadname;
	}

	public Long getImportDate() {
		return importDate;
	}

	public void setImportDate(Long importDate) {
		this.importDate = importDate;
	}

	public Long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}
}
