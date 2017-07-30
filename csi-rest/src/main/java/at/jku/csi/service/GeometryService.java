package at.jku.csi.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;

import at.jku.csi.cdi.Service;
import at.jku.csi.dao.RouteDao;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;
import at.jku.tk.csi.server.datalayer.source.static_.analysis.Route;

@Service
public class GeometryService implements Serializable {

	@Inject
	private RouteDao routeDao;
	@Inject
	private SpatialHelper spatialHelper;

	public LineString generateLineString(AsfinagTrafficmessage asfinagTrafficmessage) {
		Route route = findRoute(asfinagTrafficmessage);
		if (route == null) {
			return null;
		}
		double length = route.getLength();
		double beginmeter = asfinagTrafficmessage.getBeginmeter() / 1.0;
		double endmeter = asfinagTrafficmessage.getEndmeter() / 1.0;
		LineString routeLineString = getLineStringFromGeometry(route);
		return spatialHelper.getSubLineString(routeLineString, beginmeter / length, endmeter / length);
	}

	private LineString getLineStringFromGeometry(Route route) {
		return (LineString) ((MultiLineString) route.getGeometry()).getGeometryN(0);
	}

	private Route findRoute(AsfinagTrafficmessage asfinagTrafficmessage) {
		return routeDao.findRouteByRoadname(asfinagTrafficmessage.getRoad_code());
	}
}
