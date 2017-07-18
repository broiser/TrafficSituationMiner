package at.jku.csi.determiner;

import java.io.Serializable;

import at.jku.csi.cdi.Service;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@Service
public class RoadDirectionDeterminer implements Serializable {

	private static final int DIRECTION_1 = 1;
	private static final int DIRECTION_2 = 2;

	public enum Direction {
		ASC, DESC, UNDEFINED
	}

	public Direction determineDirection(AsfinagTrafficmessage left, AsfinagTrafficmessage right) {
		if (left.getRoad_direction() == DIRECTION_1 && right.getRoad_direction() == DIRECTION_1) {
			return Direction.ASC;
		}
		if (left.getRoad_direction() == DIRECTION_2 && right.getRoad_direction() == DIRECTION_2) {
			return Direction.DESC;
		}
		return Direction.UNDEFINED;
	}
}
