package si.fri.mag.entities;

import si.fri.mag.MainEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "media")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllMedias",
                query = "SELECT * FROM media",
                resultClass = MediaEntity.class
        ),

        @NamedNativeQuery(
                name = "getLiveMedias",
                query = "SELECT * FROM media where media.fk_project_id < 0 and media.status = 3",
                resultClass = MediaEntity.class
        ),

        @NamedNativeQuery(
                name = "getMediasInProgress",
                query = "SELECT * FROM media where media.fk_project_id < 0 and media.status < 3",
                resultClass = MediaEntity.class
        ),

        @NamedNativeQuery(
                name = "getProjectMedias",
                query = "SELECT * FROM media where media.fk_project_id = ?1",
                resultClass = MediaEntity.class
        ),
        @NamedNativeQuery(
                name = "getProjectOneMedia",
                query = "SELECT * FROM media where media.fk_project_id = ?1 and media.media_id = ?2",
                resultClass = MediaEntity.class
        ),
        @NamedNativeQuery(
                name = "deleteMedia",
                query = "DELETE FROM media WHERE media.media_id = ?1"),
})

public class MediaEntity implements MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id", nullable = false)
    private Integer mediaId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "site_name")
    private String siteName;

    // length in seconds
    @Column(name = "length", nullable = false, columnDefinition = "int DEFAULT 0")
    private Integer length;

    // 0 --> mediaUploaded, 1 --> mediaSegmented, 3 --> live rdy
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "fk_project_id")
    private Integer projectId;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    @Column(name = "aws_bucket_whole_media")
    private String awsBucketWholeMedia;

    @Column(name = "aws_storage_name_whole_media")
    private String awsStorageNameWholeMedia;

    // Getters
    public String getName() {
        return this.name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public Integer getStatus() {
        return status;
    }

    public String getAwsBucketWholeMedia() {
        return awsBucketWholeMedia;
    }

    public String getAwsStorageNameWholeMedia() {
        return awsStorageNameWholeMedia;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setAwsBucketWholeMedia(String awsBucketWholeMedia) {
        this.awsBucketWholeMedia = awsBucketWholeMedia;
    }

    public void setAwsStorageNameWholeMedia(String awsStorageNameWholeMedia) {
        this.awsStorageNameWholeMedia = awsStorageNameWholeMedia;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
}
