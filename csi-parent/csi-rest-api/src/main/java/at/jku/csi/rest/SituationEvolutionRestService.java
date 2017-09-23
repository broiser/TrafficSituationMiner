package at.jku.csi.rest;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.csi.cdi.Service;
import at.jku.csi.marschaller.DateMarshaller;
import at.jku.csi.rest.model.Filter;
import at.jku.csi.rest.model.PageResult;
import at.jku.csi.service.AsfinagTrafficmessageService;
import at.jku.csi.service.SituationEvolutionService;
import at.jku.csi.service.dto.SituationEvolutionDTOConverter;
import at.jku.csi.service.model.dto.SituationEvolutionDTO;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution;

@Service
@Path("situationEvolution")
@Produces(MediaType.APPLICATION_JSON)
public class SituationEvolutionRestService implements RestService {

	@Inject
	private DateMarshaller dateMarshaller;
	@Inject
	private SituationEvolutionService situationEvolutionService;
	@Inject
	private SituationEvolutionDTOConverter situationEvolutionDTOConverter;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficmessageService;

	@GET
	@Path("{id}")
	public Response getSituationEvolution(@PathParam("id") int id) {
		SituationEvolution situationEvolution = situationEvolutionService.findById(id);
		return Response.ok(situationEvolutionDTOConverter.apply(situationEvolution)).build();
	}

	@POST
	@Path("{page}/{pageSize}")
	public Response getSituationEvolutions(@PathParam("page") int page, @PathParam("pageSize") int pageSize,
			List<Filter> filters) {
		List<SituationEvolution> situationEvolutions = situationEvolutionService.findAll(page, pageSize, filters);
		List<SituationEvolutionDTO> situationEvolutionDTOs = situationEvolutionDTOConverter.apply(situationEvolutions);
		return Response.ok(new PageResult(situationEvolutionDTOs, situationEvolutionService.count(filters))).build();
	}

	@GET
	@Path("/build/{fromDate}/{toDate}")
	public Response getSituationEvolutions(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate)
			throws Exception {
		Date from = dateMarshaller.unmarshal(fromDate);
		Date to = dateMarshaller.unmarshal(toDate);
		return Response.ok(createSituationEvolutions(from, to)).build();
	}

	private List<SituationEvolutionDTO> createSituationEvolutions(Date from, Date to) {
		List<Integer> situationIds = asfinagTrafficmessageService.findSituationIds(from, to);
		return situationIds.stream().map(situationId -> createSituationEvolution(situationId)).collect(toList());
	}

	private SituationEvolutionDTO createSituationEvolution(int situationId) {
		return situationEvolutionDTOConverter.apply(situationEvolutionService.createSituationEvolution(situationId));
	}
}