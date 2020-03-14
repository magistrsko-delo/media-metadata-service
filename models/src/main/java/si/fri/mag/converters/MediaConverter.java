package si.fri.mag.converters;

import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.entities.MediaEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MediaConverter {
    public MediaDTO toDTO(MediaEntity mediaEntity) {

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
        return media;
    }
}
