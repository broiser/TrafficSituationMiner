package at.jku.csi.rest;

import java.util.HashMap;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vividsolutions.jts.geom.LineString;

import at.jku.csi.cdi.Service;
import at.jku.csi.geoJson.GeoJsonFeature;
import at.jku.csi.service.AsfinagTrafficmessageService;
import at.jku.csi.service.GeometryService;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
@Path("geometry")
@Produces(MediaType.APPLICATION_JSON)
public class GeometryRestService implements RestService {

	@Inject
	private GeometryService geometryService;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficmessageService;

	@GET
	@Path("{asfinagTrafficmessageId}")
	public Response buildGeoJsonFeature(@PathParam("asfinagTrafficmessageId") int asfinagTrafficmessageId) {
		AsfinagTrafficmessage trafficmessage = asfinagTrafficmessageService
				.findAsfinagTrafficmessageById(asfinagTrafficmessageId);
		return trafficmessage == null ? Response.noContent().build() : buildGeoJsonFeature(trafficmessage);
	}

	private Response buildGeoJsonFeature(AsfinagTrafficmessage trafficmessage) {
		LineString lineString = geometryService.generateLineString(trafficmessage);
		return lineString == null ? Response.noContent().build() : buildGeoJsonFeature(lineString);
	}

	private Response buildGeoJsonFeature(LineString lineString) {
		GeoJsonFeature geoJsonFeature = new GeoJsonFeature();
		geoJsonFeature.setGeometry(lineString);
		geoJsonFeature.setProperties(new HashMap<>());
		return Response.ok(geoJsonFeature).build();
	}
}