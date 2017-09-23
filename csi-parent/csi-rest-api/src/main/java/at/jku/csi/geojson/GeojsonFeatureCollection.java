package at.jku.csi.geojson;

import java.util.ArrayList;
import java.util.List;

public class GeojsonFeatureCollection implements GeojsonObject {

	private List<GeojsonFeature> geojsonFeatures = new ArrayList<GeojsonFeature>();

	public GeojsonFeatureCollection() {
	}

	public GeojsonFeatureCollection(GeojsonFeature geoJsonFeature) {
		this.geojsonFeatures.add(geoJsonFeature);
	}

	public GeojsonFeatureCollection(List<GeojsonFeature> geosonFeatures) {
		this.geojsonFeatures.addAll(geosonFeatures);
	}

	public List<GeojsonFeature> getFeatures() {
		return geojsonFeatures;
	}

	public void setFeatures(List<GeojsonFeature> geojsonFeatures) {
		this.geojsonFeatures = geojsonFeatures;
	}
}
