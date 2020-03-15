package si.fri.mag.services;

import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.converters.MediaConverter;
import si.fri.mag.entities.MediaEntity;
import si.fri.mag.input.MediaInput;
import si.fri.mag.util_entity.EntityManagement;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class MediaService {
    @Inject
    private EntityManager em;

    @Inject
    private MediaConverter mediaConverter;

    @Inject
    EntityManagement entityManagement;

    public List<MediaDTO> getAllMedias() {
        Query q = em.createNamedQuery("getAllMedias");
        List<MediaEntity> mediaEntities = (List<MediaEntity>)q.getResultList();

        List<MediaDTO> mediaDTOS = new ArrayList<MediaDTO>();
        for (MediaEntity mediaEntity : mediaEntities) {
            mediaDTOS.add(mediaConverter.toDTO(mediaEntity));
        }

        return mediaDTOS;
    }

    public MediaDTO getMedia(Integer mediaId) {
        MediaEntity mediaEntity = em.find(MediaEntity.class, mediaId);
        if (mediaEntity == null) {
            return null;
        }

        return this.mediaConverter.toDTO(mediaEntity);
    }

    public MediaDTO addNewMedia(MediaInput mediaInput) {

        MediaEntity mediaEntity = mediaConverter.toEntityNew(mediaInput);
        mediaEntity = (MediaEntity) entityManagement.createNewEntity(mediaEntity);

        if (mediaEntity == null) {
            return null;
        }

        return mediaConverter.toDTO(mediaEntity);
    }

}
