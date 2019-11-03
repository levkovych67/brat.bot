package obj.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ThreadHead {

    @JsonProperty("comment")
    private String text;

    @JsonProperty("files")
    private List<Files> files;

    @JsonProperty("num")
    private Long id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
