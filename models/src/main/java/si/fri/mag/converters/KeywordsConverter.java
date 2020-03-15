package si.fri.mag.converters;

import si.fri.mag.entities.MediaEntity;
import si.fri.mag.entities.MediaKeywordEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KeywordsConverter {

    public MediaKeywordEntity toEntity(String keyword, MediaEntity mediaEntity) {
        MediaKeywordEntity mediaKeywordEntity = new MediaKeywordEntity();
        mediaKeywordEntity.setKeyword(keyword);
        mediaKeywordEntity.setMediaEntity(mediaEntity);
        return mediaKeywordEntity;
    }
}
