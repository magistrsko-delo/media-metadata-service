package si.fri.mag.input;

import si.fri.mag.MediaAbstract;

import java.util.Date;
import java.util.List;

public class NewMediaInput extends MediaAbstract {
    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
