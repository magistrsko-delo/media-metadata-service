package si.fri.mag.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "media")
public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Integer mediaId;

    @Column(name = "name")
    private String name;

    @Column(name = "site_name")
    private String siteName;

    @Column(name = "length")
    private Integer length;

    // 0 --> mediaUploaded, 1 --> mediaSegmented, 3 --> live rdy
    @Column(name = "status")
    private Integer status;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "fk_project_id")
    private String projectId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "aws_bucket_whole_media")
    private String awsBucketWholeMedia;

    @Column(name = "aws_storage_name_whole_media")
    private String awsStorageNameWholeMedia;

    // Getters
    public String getName() {
        return this.name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
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

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
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
