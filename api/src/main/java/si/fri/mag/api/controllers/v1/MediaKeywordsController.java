package si.fri.mag.api.controllers.v1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.api.controllers.MainController;
import si.fri.mag.input.MediaInput;
import si.fri.mag.services.MediaKeywordsService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped

@Path("/v1/media/keywords")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MediaKeywordsController extends MainController {

    @Inject
    private MediaKeywordsService mediaKeywordsService;

    @PUT
    @Path("{mediaId}")
    public Response updateMediaKeywords(@PathParam("mediaId") Integer mediaId, String body) {

        Gson gson = new Gson();
        List<String> keywords;
        try {
            keywords = gson.fromJson(body, new TypeToken<ArrayList<String>>(){}.getType());
        } catch (Exception e) {
            return  this.responseError(500, "failed to parse input data");
        }

        MediaDTO mediaDTO = mediaKeywordsService.updateKeyWords(mediaId, keywords);
        if (mediaDTO == null) {
            return this.responseError(500, "Error when updating media keywords");
        }

        return this.responseOk("updated media keywords", mediaDTO);
    }

}
