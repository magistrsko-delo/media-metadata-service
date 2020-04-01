package si.fri.mag;

import java.util.Date;
import java.util.List;

public abstract class MediaAbstract {
    protected Integer mediaId;
    protected String name;
    protected String siteName;
    protected Integer length;
    protected Integer status;
    protected String thumbnail;
    protected Integer projectId;
    protected String awsBucketWholeMedia;
    protected String awsStorageNameWholeMedia;
    // getters

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSiteName() {
        return siteName;
    }

    public Integer getProjectId() {
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

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    // setters


    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setAwsStorageNameWholeMedia(String awsStorageNameWholeMedia) {
        this.awsStorageNameWholeMedia = awsStorageNameWholeMedia;
    }

    public void setAwsBucketWholeMedia(String awsBucketWholeMedia) {
        this.awsBucketWholeMedia = awsBucketWholeMedia;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
}
