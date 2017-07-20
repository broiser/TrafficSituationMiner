package at.jku.tk.csi.server.datalayer.source.static_.analysis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "Routen")
public class Route implements Serializable {

	@Id
	@Column(name = "gid")
	private long ID;

	@Column(name = "rn", nullable = true)
	private String road_code;

	@Column(name = "geom84", columnDefinition = "geometry(LineString,4326)")
	private Geometry geometry;

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getRoad_code() {
		return road_code;
	}
	
	public void setRoad_code(String road_code) {
		this.road_code = road_code;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public int hashCode() {
		return (int) (ID * 13);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Route)) {
			return false;
		}
		return ((Route) obj).getID() == ID;
	}
}
