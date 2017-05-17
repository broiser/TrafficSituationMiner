package at.jku.csi.rest;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.csi.cdi.Service;
import at.jku.csi.marschaller.DateMarshaller;
import at.jku.csi.service.AsfinagTrafficmessageService;
import at.jku.csi.service.SituationEvolutionService;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution;

@Service
@Path("situationEvolution")
@Produces(MediaType.APPLICATION_JSON)
public class SituationEvolutionRestService implements RestService {

	@Inject
	private DateMarshaller dateMarshaller;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficmessageService;
	@Inject
	private SituationEvolutionService situationEvolutionService;

	@GET
	@Path("{situationId}")
	public Response getSituationEvolution(@PathParam("situationId") int situationId) {
		return Response.ok(createSituationEvolution(situationId)).build();
	}

	@GET
	@Path("{fromDate}/{toDate}")
	public void getSituationEvolutions(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate)
			throws Exception {
		Date from = dateMarshaller.unmarshal(fromDate);
		Date to = dateMarshaller.unmarshal(toDate);
		for (int situationId : asfinagTrafficmessageService.findSituationIds(from, to)) {
			System.out.println(situationId);
			createSituationEvolution(situationId);
		}
	}

	private SituationEvolution createSituationEvolution(int situationId) {
		return situationEvolutionService.createSituationEvolution(situationId);
	}
}