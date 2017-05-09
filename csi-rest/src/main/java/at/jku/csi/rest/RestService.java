package at.jku.csi.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;


@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public interface RestService extends Serializable{

}
