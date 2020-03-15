package si.fri.mag.DTO;

import java.util.Date;
import java.util.List;

public class MediaDTO {
    private Integer mediaId;
    private String name;
    private String siteName;
    private Integer length;
    private Integer status;
    private String thumbnail;
    private String projectId;
    private Date createdAt;
    private Date updatedAt;
    private List<String> keywords;
    private String awsBucketWholeMedia;
    private String awsStorageNameWholeMedia;

    // getters

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getAwsStorageNameWholeMedia() {
        return awsStorageNameWholeMedia;
    }

    public String getAwsBucketWholeMedia() {
        return awsBucketWholeMedia;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public Integer getLength() {
        return length;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    // setters

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setAwsStorageNameWholeMedia(String awsStorageNameWholeMedia) {
        this.awsStorageNameWholeMedia = awsStorageNameWholeMedia;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void setAwsBucketWholeMedia(String awsBucketWholeMedia) {
        this.awsBucketWholeMedia = awsBucketWholeMedia;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
}
