package obj.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Post {

    @JsonProperty("comment")
    private String text;

    @JsonProperty("files")
    private List<Files> files;

    @JsonProperty("num")
    private Integer id;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("parent")
    private Integer replyTo;


    public Integer getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Integer replyTo) {
        this.replyTo = replyTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

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
}
