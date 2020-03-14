package si.fri.mag.api.v1.controllers;

import si.fri.mag.services.MediaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/media/metadata")
public class MediaMetadataController {

    @Inject
    MediaService mediaService;

    @GET
    @Path("all")
    public Response getAllMediaMetadata() {
        mediaService.getAllMedias();
        return Response.status(200).entity("Hello world").build();
    }
}
