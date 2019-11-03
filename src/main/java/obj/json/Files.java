package obj.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Files {


    @JsonProperty("path")
    private String link;

    @JsonProperty("thumbnail")
    private String thumbnail;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
