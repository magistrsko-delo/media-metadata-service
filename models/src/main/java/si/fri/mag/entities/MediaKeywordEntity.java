package si.fri.mag.entities;

import si.fri.mag.MainEntity;

import javax.persistence.*;

@Entity
@Table(name = "media_keywords")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getMediaKeywords",
                query = "SELECT * FROM media_keywords WHERE fk_media_id = ?1",
                resultClass = MediaKeywordEntity.class
        ),
        @NamedNativeQuery(
                name = "deleteMediaKeywords",
                query = "DELETE FROM media_keywords WHERE media_keywords.fk_media_id = ?1"
        )
})

public class MediaKeywordEntity implements MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_media_id")
    private MediaEntity mediaEntity;

    @Column(name = "keyword")
    private String keyword;

    public Integer getId() {
        return this.id;
    }

    // getters
    public MediaEntity getMediaEntity() {
        return mediaEntity;
    }

    public String getKeyword() {
        return keyword;
    }

    // setters

    public void setMediaEntity(MediaEntity mediaEntity) {
        this.mediaEntity = mediaEntity;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
