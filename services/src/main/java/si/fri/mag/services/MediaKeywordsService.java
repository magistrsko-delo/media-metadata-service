package si.fri.mag.services;

import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.converters.KeywordsConverter;
import si.fri.mag.converters.MediaConverter;
import si.fri.mag.entities.MediaEntity;
import si.fri.mag.entities.MediaKeywordEntity;
import si.fri.mag.util_entity.EntityManagement;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class MediaKeywordsService {
    @Inject
    private EntityManager em;

    @Inject
    private EntityManagement entityManagement;

    @Inject
    private KeywordsConverter keywordsConverter;

    @Inject
    private MediaConverter mediaConverter;

    public MediaDTO updateKeyWords(Integer mediaId, List<String> keywords) {
        MediaEntity mediaEntity = em.find(MediaEntity.class, mediaId);
        if (mediaEntity == null) {
            throw new EntityNotFoundException("Entity not found with id: " + mediaId + "\n Keywords can not be updated.");
        }

        Query queryKeywordsDelete = em.createNamedQuery("deleteMediaKeywords").setParameter(1, mediaId);
        boolean isDeleted = entityManagement.executeUpdate(queryKeywordsDelete);
        if (!isDeleted) {
            return null;
        }

        for (String keyword: keywords) {
            MediaKeywordEntity mediaKeywordEntity = keywordsConverter.toEntity(keyword, mediaEntity);
            mediaKeywordEntity = (MediaKeywordEntity) entityManagement.createNewEntity(mediaKeywordEntity);
            if (mediaKeywordEntity == null) {
                return null;
            }
        }
        return mediaConverter.toDTO(mediaEntity);
    }
}
