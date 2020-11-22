package pl.dernovyi.osrazure.model.respEntity;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "boundingBox",
    "words"
})
public class Line {

    @JsonProperty("boundingBox")
    private String boundingBox;
    @JsonProperty("words")
    private List<Word> words = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("boundingBox")
    public String getBoundingBox() {
        return boundingBox;
    }

    @JsonProperty("boundingBox")
    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    @JsonProperty("words")
    public List<Word> getWords() {
        return words;
    }

    @JsonProperty("words")
    public void setWords(List<Word> words) {
        this.words = words;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Line{");
        sb.append("boundingBox='").append(boundingBox).append('\'');
        sb.append(", words=").append(words);
        sb.append(", additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }
}
