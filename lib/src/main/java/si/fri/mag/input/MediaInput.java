package si.fri.mag.input;

import si.fri.mag.MediaAbstract;

import java.util.Date;
import java.util.List;

public class MediaInput extends MediaAbstract {
    private String keywords;
    protected long createdAt;

    public String getKeywords() {
        return keywords;
    }
    public long getCreatedAt() {
        return createdAt;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
