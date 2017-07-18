package at.jku.csi.rest;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.csi.cdi.Service;
import at.jku.csi.comparator.StateInstanceComparator;
import at.jku.csi.marschaller.DateMarshaller;
import at.jku.csi.service.AsfinagTrafficmessageService;
import at.jku.csi.service.ObjectTypeService;
import at.jku.csi.service.SituationEvolutionService;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.ObjectType;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;

@Service
@Path("objectType")
@Produces(MediaType.APPLICATION_JSON)
public class ObjectTypeRestService implements RestService {

	@Inject
	private DateMarshaller dateMarshaller;
	@Inject
	private ObjectTypeService objectTypeService;
	@Inject
	private StateInstanceComparator stateInstanceComparator;
	@Inject
	private SituationEvolutionService situationEvolutionService;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficmessageService;

	@GET
	@Path("{situationId}")
	public Response getObjectTypes(@PathParam("situationId") long situationId) {
		return Response.ok(createObjectTypesBySituationId(situationId)).build();
	}

	@GET
	@Path("{fromDate}/{toDate}")
	public Response getObjectTypes(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate)
			throws Exception {
		Date from = dateMarshaller.unmarshal(fromDate);
		Date to = dateMarshaller.unmarshal(toDate);
		return Response.ok(createObjectTypesByDateRange(from, to)).build();
	}

	private List<ObjectType> createObjectTypesByDateRange(Date from, Date to) {
		Stream<Integer> situationIds = asfinagTrafficmessageService.findSituationIds(from, to).stream();
		return situationIds.flatMap(situationId -> createObjectTypesBySituationId(situationId).stream())
				.collect(toList());
	}

	private List<ObjectType> createObjectTypesBySituationId(long situationId) {
		return objectTypeService.createObjectTypes(findStateInstances(situationId));
	}

	private List<StateInstance> findStateInstances(long situationId) {
		SituationEvolution situationEvoution = situationEvolutionService.findSituationEvoution(situationId);
		return situationEvoution.getStateInstance().stream().sorted(stateInstanceComparator).collect(toList());
	}
}
