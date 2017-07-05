package at.jku.csi.rest;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.csi.cdi.Service;
import at.jku.csi.comparator.StateInstanceComparator;
import at.jku.csi.service.ObjectTypeService;
import at.jku.csi.service.SituationEvolutionService;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;

@Service
@Path("objectType")
@Produces(MediaType.APPLICATION_JSON)
public class ObjectTypeRestService implements RestService {

	@Inject
	private ObjectTypeService objectTypeService;
	@Inject
	private StateInstanceComparator stateInstanceComparator;
	@Inject
	private SituationEvolutionService situationEvolutionService;

	@GET
	@Path("{situationId}")
	public Response getObjectType(@PathParam("situationId") long situationId) {
		return Response.ok(objectTypeService.createObjectTypes(findStateInstances(situationId))).build();
	}

	private List<StateInstance> findStateInstances(long situationId) {
		SituationEvolution situationEvoution = situationEvolutionService.findSituationEvoution(situationId);
		return situationEvoution.getStateInstance().stream().sorted(stateInstanceComparator).collect(toList());
	}
}
