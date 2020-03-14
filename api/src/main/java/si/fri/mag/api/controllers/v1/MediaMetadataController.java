package si.fri.mag.api.controllers.v1;

import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.api.controllers.MainController;
import si.fri.mag.services.MediaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/v1/media/metadata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MediaMetadataController extends MainController {

    @Inject
    MediaService mediaService;

    @GET
    @Path("all")
    public Response getAllMediaMetadata() {
        List<MediaDTO> mediaDTO = mediaService.getAllMedias();
        return this.responseOk("", mediaDTO);
    }
}
