package at.jku.csi.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import at.jku.csi.cdi.Service;
import at.jku.csi.service.SituationEvolutionService;

@Service
@Path("situationEvolution")
public class SituationEvolutionRestService implements RestService {

	@Inject
	private SituationEvolutionService situationEvolutionService;

	@GET
	@Path("{situationId}")
	public Response getSituationIds(@PathParam("situationId") int situationId) {
		return Response.ok(situationEvolutionService.createSituationEvolution(situationId)).build();
	}
}