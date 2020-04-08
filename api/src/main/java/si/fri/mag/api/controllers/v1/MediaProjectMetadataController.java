package si.fri.mag.api.controllers.v1;

import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.api.controllers.MainController;
import si.fri.mag.services.ProjectMediaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/v1/media")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MediaProjectMetadataController extends MainController {

    @Inject
    private ProjectMediaService projectMediaService;

    @GET
    @Path("project/{projectId}")
    public Response getProjectMedias(@PathParam("projectId") Integer projectId) {

        if (projectId < 0) {
            return this.responseError(400, "project id must be greater then zero: " + projectId);
        }

        List<MediaDTO> mediaDTOS = projectMediaService.getProjectMedias(projectId);
        return this.responseOk("", mediaDTOS);
    }

    @GET
    @Path("{mediaId}/project/{projectId}")
    public Response getProjectOneMedia(@PathParam("mediaId") Integer mediaId ,@PathParam("projectId") Integer projectId) {

        if (projectId < 0) {
            return this.responseError(400, "project id must be greater then zero: " + projectId);
        }

        MediaDTO mediaDTO = projectMediaService.getProjectOneMedia(mediaId, projectId);
        return this.responseOk("", mediaDTO);
    }
}
