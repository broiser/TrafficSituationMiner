package at.jku.csi.rest;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.csi.cdi.Service;
import at.jku.csi.marschaller.DateMarshaller;
import at.jku.csi.rest.model.PageResult;
import at.jku.csi.service.AsfinagTrafficmessageService;
import at.jku.csi.service.EvolvingObjectService;
import at.jku.csi.service.dto.EvolvingObjectDTOConverter;
import at.jku.csi.service.model.dto.EvolvingObjectDTO;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.EvolvingObject;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
@Path("evolvingObject")
@Produces(MediaType.APPLICATION_JSON)
public class EvolvingObjectRestService implements RestService {

	@Inject
	private DateMarshaller dateMarshaller;
	@Inject
	private EvolvingObjectService evolvingObjectService;
	@Inject
	private EvolvingObjectDTOConverter evolvingObjectDTOConverter;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficmessageService;

	@GET
	@Path("{id}")
	public Response getEvolvingObject(@PathParam("id") int id) {
		EvolvingObject evolvingObject = evolvingObjectService.findById(id);
		return Response.ok(evolvingObjectDTOConverter.apply(evolvingObject)).build();
	}

	@GET
	@Path("{page}/{pageSize}")
	public Response getEvolvingObjects(@PathParam("page") int page, @PathParam("pageSize") int pageSize) {
		List<EvolvingObject> evolvingObjects = evolvingObjectService.findAll(page, pageSize);
		List<EvolvingObjectDTO> evolvingObjectDTOs = evolvingObjectDTOConverter.apply(evolvingObjects);
		return Response.ok(new PageResult(evolvingObjectDTOs, evolvingObjectService.count())).build();
	}

	@GET
	@Path("build/{fromDate}/{toDate}")
	public Response getEvolvingObjects(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate)
			throws Exception {
		Date from = dateMarshaller.unmarshal(fromDate);
		Date to = dateMarshaller.unmarshal(toDate);
		return Response.ok(createEvolvingObjects(from, to)).build();
	}

	private List<EvolvingObject> createEvolvingObjects(Date from, Date to) {
		List<Integer> vmisIds = asfinagTrafficmessageService.findVmisIds(from, to);
		return vmisIds.stream().map(vmisId -> createEvolvingObject(vmisId)).collect(toList());
	}

	private EvolvingObject createEvolvingObject(int vmisId) {
		List<AsfinagTrafficmessage> trafficmessages = asfinagTrafficmessageService
				.findByVmisId(vmisId);
		return evolvingObjectService.createEvolvingObject(trafficmessages);
	}

}
