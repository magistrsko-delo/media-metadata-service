package si.fri.mag.services;

import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.converters.MediaConverter;
import si.fri.mag.entities.MediaEntity;
import si.fri.mag.input.MediaInput;
import si.fri.mag.util_entity.EntityManagement;
import si.fri.mag.util_entity.EntityTransactions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MediaService {
    @Inject
    private EntityManager em;

    @Inject
    private MediaConverter mediaConverter;

    @Inject
    EntityManagement entityManagement;

    @Inject
    EntityTransactions entityTransactions;

    private List<MediaDTO> prepareMediaDTOResponse(List<MediaEntity> mediaEntities) {
        List<MediaDTO> mediaDTOS = new ArrayList<MediaDTO>();
        for (MediaEntity mediaEntity : mediaEntities) {
            mediaDTOS.add(mediaConverter.toDTO(mediaEntity));
        }

        return mediaDTOS;
    }

    public List<MediaDTO> getAllMedias() {
        Query q = em.createNamedQuery("getAllMedias");
        List<MediaEntity> mediaEntities = (List<MediaEntity>)q.getResultList();

        return this.prepareMediaDTOResponse(mediaEntities);
    }

    public List<MediaDTO> getLiveMedias() {
        Query q = em.createNamedQuery("getLiveMedias");
        List<MediaEntity> mediaEntities = (List<MediaEntity>)q.getResultList();

        return this.prepareMediaDTOResponse(mediaEntities);
    }

    public List<MediaDTO> getMediasInProgress() {
        Query q = em.createNamedQuery("getMediasInProgress");
        List<MediaEntity> mediaEntities = (List<MediaEntity>)q.getResultList();

        return this.prepareMediaDTOResponse(mediaEntities);
    }

    public MediaDTO getMedia(Integer mediaId) {
        MediaEntity mediaEntity = em.find(MediaEntity.class, mediaId);
        if (mediaEntity == null) {
            throw new EntityNotFoundException("Entity not found with id: " + mediaId);
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

    public MediaDTO updateMedia(Integer mediaId,  MediaInput mediaInput) {

        MediaEntity entity = em.find(MediaEntity.class, mediaId);
        if (entity == null) {
            throw new EntityNotFoundException("Entity not found with id: " + mediaId);
        }

        MediaEntity updatedMediaEntity = mediaConverter.toEntityUpdate(mediaInput);
        updatedMediaEntity.setMediaId(entity.getMediaId());
        updatedMediaEntity.setCreatedAt(entity.getCreatedAt());
        updatedMediaEntity = (MediaEntity) entityManagement.updateEntity(updatedMediaEntity);

        if (updatedMediaEntity == null) {
            return null;
        }

        return mediaConverter.toDTO(updatedMediaEntity);
    }

    public boolean deleteMedia(Integer mediaId) {

        Query queryKeywordsDelete = em.createNamedQuery("deleteMediaKeywords").setParameter(1, mediaId);
        Query queryMediaDelete = em.createNamedQuery("deleteMedia").setParameter(1, mediaId);

        try {
            entityTransactions.beginTx();
            queryKeywordsDelete.executeUpdate();
            queryMediaDelete.executeUpdate();
            entityTransactions.commitTx();
        } catch (Exception e) {
            entityTransactions.rollbackTx();
            return false;
        }

        return true;
    }

}
