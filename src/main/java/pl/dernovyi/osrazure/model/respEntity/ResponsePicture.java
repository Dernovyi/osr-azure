package pl.dernovyi.osrazure.model.respEntity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "language",
    "textAngle",
    "orientation",
    "regions"
})
public class ResponsePicture {

    @JsonProperty("language")
    private String language;
    @JsonProperty("textAngle")
    private Double textAngle;
    @JsonProperty("orientation")
    private String orientation;
    @JsonProperty("regions")
    private List<Region> regions = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("textAngle")
    public Double getTextAngle() {
        return textAngle;
    }

    @JsonProperty("textAngle")
    public void setTextAngle(Double textAngle) {
        this.textAngle = textAngle;
    }

    @JsonProperty("orientation")
    public String getOrientation() {
        return orientation;
    }

    @JsonProperty("orientation")
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    @JsonProperty("regions")
    public List<Region> getRegions() {
        return regions;
    }

    @JsonProperty("regions")
    public void setRegions(List<Region> regions) {
        this.regions = regions;
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
        final StringBuffer sb = new StringBuffer("ResponsePicture{");
        sb.append("language='").append(language).append('\'');
        sb.append(", textAngle=").append(textAngle);
        sb.append(", orientation='").append(orientation).append('\'');
        sb.append(", regions=").append(regions);
        sb.append(", additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }
}
