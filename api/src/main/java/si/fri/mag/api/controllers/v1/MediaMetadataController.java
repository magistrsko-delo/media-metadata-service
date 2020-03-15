package si.fri.mag.api.controllers.v1;

import com.google.gson.Gson;
import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.api.controllers.MainController;
import si.fri.mag.input.MediaInput;
import si.fri.mag.services.MediaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
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

    @GET
    @Path("{mediaId}")
    public Response getOneMedia(@PathParam("mediaId") Integer mediaId) {

        MediaDTO mediaDTO = mediaService.getMedia(mediaId);
        if (mediaDTO == null) {
            return this.responseError(400, "Media with id: " + mediaId + " not found");
        }

        return this.responseOk("", mediaDTO);
    }

    @POST
    @Path("new")
    public Response addMediaMetadata(String body) {

        Gson gson = new Gson();
        MediaInput mediaMetadata;
        try {
            mediaMetadata = gson.fromJson(body, MediaInput.class);
        } catch (Exception e) {
            return  this.responseError(500, "failed to parse input data");
        }

        MediaDTO mediaDTO = mediaService.addNewMedia(mediaMetadata);
        if (mediaDTO == null) {
            return this.responseError(500, "failed to create media");
        }

        return this.responseOk("", mediaDTO);
    }

    @PUT
    @Path("{mediaId}/update")
    public Response updateMedia(@PathParam("mediaId") Integer mediaId, String body) {
        Gson gson = new Gson();
        MediaInput mediaMetadata;
        try {
            mediaMetadata = gson.fromJson(body, MediaInput.class);
        } catch (Exception e) {
            return  this.responseError(500, "failed to parse input data");
        }

        MediaDTO mediaDTO = mediaService.updateMedia(mediaId, mediaMetadata);

        if (mediaDTO == null) {
            return this.responseError(500, "failed to update given media");
        }

        return this.responseOk("", mediaDTO);
    }

    @DELETE
    @Path("{mediaId}/delete")
    public Response deleteMedia(@PathParam("mediaId") Integer mediaId) {

        boolean isDeleted = mediaService.deleteMedia(mediaId);

        if (!isDeleted) {
            responseError(500, "error when deleting media");
        }

        return this.responseOk("", "ok");
    }

}
