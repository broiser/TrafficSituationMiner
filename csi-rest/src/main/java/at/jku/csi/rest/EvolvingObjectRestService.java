package at.jku.csi.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import at.jku.csi.cdi.Service;
import at.jku.csi.service.EvolvingObjectService;
import at.jku.csi.service.SituationEvolutionService;

@Service
@Path("evolvingObject")
public class EvolvingObjectRestService implements RestService {

	@Inject
	private EvolvingObjectService evolvingObjectService;

	@GET
	@Path("{vms_id}")
	public Response getEvolvingObject(@PathParam("vms_id") int vmsId) {
		return Response.ok(evolvingObjectService.createEvolvingObject(vmsId)).build();
	}
}