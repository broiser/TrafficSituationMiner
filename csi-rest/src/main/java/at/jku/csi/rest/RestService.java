package at.jku.csi.rest;

import java.io.Serializable;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Produces(MediaType.APPLICATION_JSON)
public interface RestService extends Serializable{

}
