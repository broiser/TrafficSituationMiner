package at.jku.csi.geojson;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.vividsolutions.jts.geom.Geometry;

import at.jku.csi.marschaller.GeometryDeserializer;
import at.jku.csi.marschaller.GeometrySerializer;

public class GeojsonFeature implements GeojsonObject {

    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    private Geometry geometry;
    private Map<String, Object> properties = new HashMap<String, Object>();

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public int hashCode() {
        return geometry.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GeojsonFeature)) {
            return false;
        }
        return ((GeojsonFeature) obj).geometry.equals(geometry);
    }
}