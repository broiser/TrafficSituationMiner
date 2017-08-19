package at.jku.csi.service.dto;

import static java.util.stream.Collectors.toSet;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;

import at.jku.csi.cdi.Service;
import at.jku.csi.service.model.dto.AsfinagTrafficmessageDTO;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class AsfinagTrafficmessageDTOConverter implements Function<AsfinagTrafficmessage, AsfinagTrafficmessageDTO> {

	public Set<AsfinagTrafficmessageDTO> apply(Set<AsfinagTrafficmessage> asfinagTrafficmessages) {
		return asfinagTrafficmessages.parallelStream().map((source) -> apply(source)).collect(toSet());
	}

	@Override
	public AsfinagTrafficmessageDTO apply(AsfinagTrafficmessage source) {
		AsfinagTrafficmessageDTO target = new AsfinagTrafficmessageDTO();
		target.setID(source.getID());
		target.setBeginmeter(source.getBeginmeter());
		target.setEndmeter(source.getEndmeter());
		target.setBegintime(toMillis(source.getBegintime()));
		target.setEndtime(toMillis(source.getEndtime()));
		target.setDatex_dst(source.getDatex_dst());
		target.setDatex_mse(source.getDatex_mse());
		target.setDatex_phr(source.getDatex_phr());
		target.setImportDate(toMillis(source.getImportDate()));
		target.setUpdateDate(toMillis(source.getUpdate_time()));
		target.setMessage_id(source.getMessage_id());
		target.setMessageText(source.getMessagetext());
		target.setRoad_id(source.getRoad_id());
		target.setRoad_code(source.getRoad_code());
		target.setRoadname(source.getRoadname());
		target.setRoad_direction(source.getRoad_direction());
		target.setVmis_id(source.getVmis_id());
		return target;
	}

	private Long toMillis(Date date) {
		return date == null ? 0 : date.getTime();
	}

	private Long toMillis(Timestamp time) {
		return time == null ? 0 : time.getTime();
	}
}
