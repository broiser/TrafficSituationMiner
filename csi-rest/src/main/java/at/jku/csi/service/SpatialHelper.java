package at.jku.csi.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.linearref.LinearLocation;

import at.jku.csi.cdi.Service;

@Service
public class SpatialHelper implements Serializable {

	@Inject
	private GeometryFactory geometryFactory;

	public LineString getSubLineString(LineString l, double from, double to) {
		boolean reverse = false;
		if (from > to) {
			reverse = false;
			double h = from;
			from = to;
			to = h;
		}

		if (from > 1) {
			from = 1;
		} else if (from < 0) {
			from = 0;
		}
		if (to > 1) {
			to = 1;
		} else if (to < 0) {
			to = 0;
		}

		if (from == to) {
			Coordinate p = interpolatePoint(l, from);
			return geometryFactory.createLineString(new Coordinate[] { p, p });
		}

		LinkedList<Coordinate> result = new LinkedList<Coordinate>();
		Coordinate start = l.getCoordinateN(0);
		Coordinate[] coords = l.getCoordinates();
		boolean fromFound = false;
		double len = l.getLength();
		double fromLen = len * from;
		double toLen = len * to;
		LineString currString = geometryFactory.createLineString(new Coordinate[] { start, start });
		Coordinate inter = start;
		for (int i = 1; i < coords.length; i++) {
			double prevLen = currString.getLength();
			currString = geometryFactory
					.createLineString(concat(currString.getCoordinates(), new Coordinate[] { coords[i] }));
			double currLen = currString.getLength();
			if (!fromFound && currLen >= fromLen) {
				double segmentLen = geometryFactory.createLineString(new Coordinate[] { coords[i - 1], coords[i] })
						.getLength();
				inter = LinearLocation.pointAlongSegmentByFraction(coords[i - 1], coords[i],
						(fromLen - prevLen) / segmentLen);
				result.add(inter);
				fromFound = true;
			}
			if (fromFound) {
				if (currLen >= toLen) {
					double segmentLen = geometryFactory.createLineString(new Coordinate[] { coords[i - 1], coords[i] })
							.getLength();
					inter = LinearLocation.pointAlongSegmentByFraction(coords[i - 1], coords[i],
							(toLen - prevLen) / segmentLen);
					result.add(inter);
					break;
				} else
					result.add(coords[i]);
			}
		}

		LineString ret = geometryFactory.createLineString(result.toArray(new Coordinate[0]));
		if (reverse)
			ret = (LineString) ret.reverse();
		return ret;
	}

	public Coordinate interpolatePoint(LineString l, double percent) {
		Coordinate start = l.getCoordinateN(0);
		if (percent <= 0) {
			return start;
		}
		if (percent >= 1) {
			return l.getCoordinateN(l.getNumPoints() - 1);
		}
		Coordinate[] coords = l.getCoordinates();
		double len = l.getLength();
		double percentLen = len * percent;
		LineString currString = geometryFactory.createLineString(new Coordinate[] { start, start });
		Coordinate inter = start;
		for (int i = 1; i < coords.length; i++) {
			double prevLen = currString.getLength();
			currString = geometryFactory
					.createLineString(concat(currString.getCoordinates(), new Coordinate[] { coords[i] }));
			double currLen = currString.getLength();
			if (currLen >= percentLen) {
				double segmentLen = geometryFactory.createLineString(new Coordinate[] { coords[i - 1], coords[i] })
						.getLength();
				inter = LinearLocation.pointAlongSegmentByFraction(coords[i - 1], coords[i],
						(percentLen - prevLen) / segmentLen);
				break;
			}
		}
		return inter;
	}

	private Coordinate[] concat(Coordinate[] coordinates, Coordinate[] coordinates2) {
		List<Coordinate[]> asList = Arrays.asList(coordinates, coordinates2);
		return asList.toArray(new Coordinate[coordinates.length + coordinates2.length]);
	}
}
