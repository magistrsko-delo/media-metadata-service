package si.fri.mag.converters;

import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.entities.MediaEntity;
import si.fri.mag.entities.MediaKeywordEntity;
import si.fri.mag.input.MediaInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MediaConverter {
    @Inject
    private EntityManager em;

    public MediaDTO toDTO(MediaEntity mediaEntity) {

        List<String> mediaKeywords = this.getMediaKeywords(mediaEntity.getMediaId());

        MediaDTO media = new MediaDTO();
        media.setAwsBucketWholeMedia(mediaEntity.getAwsBucketWholeMedia());
        media.setAwsStorageNameWholeMedia(mediaEntity.getAwsStorageNameWholeMedia());
        media.setCreatedAt(mediaEntity.getCreatedAt());
        media.setLength(mediaEntity.getLength());
        media.setMediaId(mediaEntity.getMediaId());
        media.setName(mediaEntity.getName());
        media.setProjectId(mediaEntity.getProjectId());
        media.setSiteName(mediaEntity.getSiteName());
        media.setStatus(mediaEntity.getStatus());
        media.setThumbnail(mediaEntity.getThumbnail());
        media.setUpdatedAt(mediaEntity.getUpdatedAt());
        media.setKeywords(mediaKeywords);

        return media;
    }

    public MediaEntity toEntityNew(MediaInput mediaInput) {
        MediaEntity mediaEntity = this.toEntity(mediaInput);
        mediaEntity.setCreatedAt(new Date(System.currentTimeMillis()));

        return  mediaEntity;
    }

    public MediaEntity toEntityUpdate(MediaInput mediaInput) {
        MediaEntity mediaEntity = this.toEntity(mediaInput);
        mediaEntity.setCreatedAt(new Date(mediaInput.getCreatedAt() * 1000));
        return mediaEntity;
    }

    private MediaEntity toEntity(MediaInput mediaInput) {
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setName(mediaInput.getName());
        mediaEntity.setSiteName(mediaInput.getSiteName());
        mediaEntity.setLength(mediaInput.getLength());
        mediaEntity.setStatus(mediaInput.getStatus());
        mediaEntity.setThumbnail(mediaInput.getThumbnail());
        mediaEntity.setProjectId(mediaInput.getProjectId());
        mediaEntity.setAwsStorageNameWholeMedia(mediaInput.getAwsStorageNameWholeMedia());
        mediaEntity.setAwsBucketWholeMedia(mediaInput.getAwsBucketWholeMedia());
        mediaEntity.setUpdatedAt(new Date(System.currentTimeMillis()));
        return mediaEntity;
    }

    private List<String> getMediaKeywords(Integer mediaId) {
        Query q = em.createNamedQuery("getMediaKeywords").setParameter(1, mediaId);
        List<MediaKeywordEntity> mediaKeywordEntities = q.getResultList();

        List<String> mediaKeyWords = new ArrayList<String>();
        for (MediaKeywordEntity mediaKeywordEntity : mediaKeywordEntities) {
            mediaKeyWords.add(mediaKeywordEntity.getKeyword());
        }

        return mediaKeyWords;
    }
}
