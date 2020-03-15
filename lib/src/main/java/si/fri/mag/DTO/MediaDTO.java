package si.fri.mag.DTO;

import si.fri.mag.MediaAbstract;

import java.util.Date;
import java.util.List;

public class MediaDTO extends MediaAbstract {
    private List<String> keywords;
    private Date createdAt;
    private Date updatedAt;

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
