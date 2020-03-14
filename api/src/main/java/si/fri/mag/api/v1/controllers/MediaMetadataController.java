package si.fri.mag.api.v1.controllers;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/media/metadata")
public class MediaMetadataController {
    @GET
    @Path("all")
    public Response getAllMediaMetadata() {
        return Response.status(200).entity("Hello world").build();
    }

}
