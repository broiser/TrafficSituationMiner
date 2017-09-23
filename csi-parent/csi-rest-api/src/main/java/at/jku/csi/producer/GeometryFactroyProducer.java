package at.jku.csi.producer;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

import at.jku.csi.cdi.Service;

@Service
public class GeometryFactroyProducer implements Serializable {

	@Produces
	@Dependent
	public GeometryFactory produceGeometryFactory() {
		return new GeometryFactory(new PrecisionModel(), 4326);
	}
}
