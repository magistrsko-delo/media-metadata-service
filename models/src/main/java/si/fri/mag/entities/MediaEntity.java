package si.fri.mag.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "media")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllMedias",
                query = "SELECT * FROM media",
                resultClass = MediaEntity.class
        )
})

public class MediaEntity {
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
    private String projectId;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    @Column(name = "aws_bucket_whole_media", nullable = false)
    private String awsBucketWholeMedia;

    @Column(name = "aws_storage_name_whole_media", nullable = false)
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

    public String getProjectId() {
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

    public void setProjectId(String projectId) {
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

}
