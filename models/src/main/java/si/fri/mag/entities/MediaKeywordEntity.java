package si.fri.mag.entities;

import javax.persistence.*;

@Entity
@Table(name = "media_keywords")

@NamedNativeQueries({
})

public class MediaKeywordEntity {

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
