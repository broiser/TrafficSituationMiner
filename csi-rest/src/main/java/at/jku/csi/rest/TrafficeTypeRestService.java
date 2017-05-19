package at.jku.csi.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Tuple;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.csi.cdi.Service;
import at.jku.csi.service.AsfinagTrafficmessageService;
import at.jku.csi.service.TrafficTypeService;
import at.jku.tk.csi.server.datalayer.source.dynamic_.analysis.asfinag.TrafficType;

@Service
@Path("trafficType")
@Produces(MediaType.APPLICATION_JSON)
public class TrafficeTypeRestService implements RestService {

	@Inject
	private TrafficTypeService trafficTypeService;
	@Inject
	private AsfinagTrafficmessageService asfinagTrafficemessageService;

	@GET
	public Response getTrafficTypes() {
		List<TrafficType> trafficTypes = new ArrayList<>();

		List<Tuple> tuples = asfinagTrafficemessageService.findTrafficTypeTuples();
		for (Tuple tuple : tuples) {
			String type = tuple.get(0, String.class);
			String messageText = tuple.get(1, String.class);
			trafficTypes.add(findOrCreateTrafficType(type, messageText));
		}
		return Response.ok(trafficTypes).build();
	}

	private TrafficType findOrCreateTrafficType(String type, String messageText) {
		TrafficType trafficType = trafficTypeService.findTrafficeType(type);
		if (trafficType == null) {
			trafficType = trafficTypeService.createTrafficType(type, messageText);
		}
		return trafficType;
	}
}