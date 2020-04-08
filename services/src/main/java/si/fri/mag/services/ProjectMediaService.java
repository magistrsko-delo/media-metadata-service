package si.fri.mag.services;

import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.converters.MediaConverter;
import si.fri.mag.entities.MediaEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProjectMediaService {
    @Inject
    private EntityManager em;

    @Inject
    private MediaConverter mediaConverter;

    public List<MediaDTO> getProjectMedias(Integer projectId) {
        Query q = em.createNamedQuery("getProjectMedias").setParameter(1, projectId);
        List<MediaEntity> mediaEntities = (List<MediaEntity>)q.getResultList();

        List<MediaDTO> mediaDTOS = new ArrayList<MediaDTO>();
        for (MediaEntity mediaEntity : mediaEntities) {
            mediaDTOS.add(mediaConverter.toDTO(mediaEntity));
        }

        return mediaDTOS;
    }

    public MediaDTO getProjectOneMedia(Integer mediaId, Integer projectId) {

        Query q = em.createNamedQuery("getProjectOneMedia").setParameter(1, projectId).setParameter(2, mediaId);

        try {
            MediaEntity mediaEntity = (MediaEntity)q.getSingleResult();

            if (mediaEntity == null) {
                throw  new EntityNotFoundException("Media with project id: " + projectId + " and media id: " + mediaId + " not found");
            }

            return mediaConverter.toDTO(mediaEntity);
        } catch (Exception e) {
            throw  new EntityNotFoundException("Media with project id: " + projectId + " and media id: " + mediaId + " not found");
        }

    }
}
