package at.jku.csi.geojson;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

//@formatter:off
@JsonTypeInfo(property = GeojsonObject.TYPE, use = Id.NAME)
@JsonSubTypes({ 
	@Type(name = GeojsonObject.FEATURE, value = GeojsonFeature.class),
	@Type(name = GeojsonObject.FEATURE_COLLECTION, value = GeojsonFeatureCollection.class) })
// @formatter:on
public interface GeojsonObject extends Serializable {
	static final String TYPE = "type";
	static final String FEATURE = "Feature";
	static final String FEATURE_COLLECTION = "FeatureCollection";
}