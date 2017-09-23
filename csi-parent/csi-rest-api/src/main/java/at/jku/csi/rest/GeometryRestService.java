package at.jku.csi.rest;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;

import at.jku.csi.cdi.Service;
import at.jku.csi.geojson.GeojsonFeature;
import at.jku.csi.service.AsfinagTrafficmessageService;
import at.jku.csi.service.GeometryService;
import at.jku.csi.service.SituationEvolutionService;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.SituationEvolution;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.StateInstance;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
@Path("geometry")
@Produces(MediaType.APPLICATION_JSON)
public class GeometryRestService implements RestService {

	@Inject
	private GeometryService geometryService;
	@Inject
	private SituationEvolutionService situationEvolutionService;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficmessageService;

	@GET
	@Path("asfinagTrafficmessage/{asfinagTrafficmessageId}")
	public Response buildAsfinagTrafficmessageGeoJsonFeature(
			@PathParam("asfinagTrafficmessageId") int asfinagTrafficmessageId) {
		AsfinagTrafficmessage trafficmessage = asfinagTrafficmessageService.findById(asfinagTrafficmessageId);
		return trafficmessage == null ? Response.noContent().build() : buildGeoJsonFeature(trafficmessage);
	}

	@GET
	@Path("situationEvolution/{situationEvolutionId}")
	public Response buildSituationEvolutionGeoJsonFeature(@PathParam("situationEvolutionId") int situationEvolutionId) {
		SituationEvolution situationEvolution = situationEvolutionService.findById(situationEvolutionId);
		return situationEvolution == null ? Response.noContent().build() : buildGeoJsonFeature(situationEvolution);
	}

	private Response buildGeoJsonFeature(SituationEvolution situationEvolution) {
		List<Geometry> geometries = buildSituationGeometries(situationEvolution);
		Geometry situationGeometry = geometryService.generateSituationGeometry(geometries);
		return Response.ok(buildGeoJsonFeature(situationGeometry)).build();
	}

	private List<Geometry> buildSituationGeometries(SituationEvolution situationEvolution) {
		Stream<StateInstance> stateInstanceStream = situationEvolution.getStateInstance().stream();
		Stream<AsfinagTrafficmessage> trafficmessageStream = stateInstanceStream
				.flatMap(stateInstance -> stateInstance.getAsfinagTrafficmessage().stream());
		return trafficmessageStream.map(trafficMessage -> geometryService.generateLineString(trafficMessage))
				.filter(Objects::nonNull).collect(toList());
	}

	private Response buildGeoJsonFeature(AsfinagTrafficmessage trafficmessage) {
		LineString lineString = geometryService.generateLineString(trafficmessage);
		return lineString == null ? Response.noContent().build() : Response.ok(buildGeoJsonFeature(lineString)).build();
	}

	private GeojsonFeature buildGeoJsonFeature(Geometry lineString) {
		GeojsonFeature geoJsonFeature = new GeojsonFeature();
		geoJsonFeature.setGeometry(lineString);
		geoJsonFeature.setProperties(new HashMap<>());
		return geoJsonFeature;
	}
}