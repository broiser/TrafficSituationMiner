package at.jku.csi.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.csi.cdi.Service;
import at.jku.csi.service.AsfinagTrafficmessageService;
import at.jku.csi.service.EvolvingObjectService;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
@Path("evolvingObject")
@Produces(MediaType.APPLICATION_JSON)
public class EvolvingObjectRestService implements RestService {

	@Inject
	private EvolvingObjectService evolvingObjectService;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficmessageService;

	@GET
	@Path("{vmis_id}")
	public Response getEvolvingObject(@PathParam("vmis_id") int vmisId) {
		List<AsfinagTrafficmessage> trafficmessages = asfinagTrafficmessageService
				.findAsfinagTrafficmessagesByVmisId(vmisId);
		
		
		return Response.ok(evolvingObjectService.createEvolvingObject(trafficmessages)).build();
	}

}
