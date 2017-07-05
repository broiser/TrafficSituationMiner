package at.jku.csi.evaluator;

import java.io.Serializable;

import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

public interface Evaluator extends Serializable{

	boolean evaluate(AsfinagTrafficmessage left, AsfinagTrafficmessage right);
}
