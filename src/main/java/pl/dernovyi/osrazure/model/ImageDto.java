package pl.dernovyi.osrazure.model;

import pl.dernovyi.osrazure.model.respEntity.Language;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "images")
public class ImageDto extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String url;

    @Enumerated(EnumType.STRING)
    private Language language;

    public ImageDto(Long id, String text, String url, Language language) {
        this.id = id;
        this.text = text;
        this.url = url;
        this.language = language;
    }

    public ImageDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDto imageDto = (ImageDto) o;
        return Objects.equals(id, imageDto.id) &&
                Objects.equals(text, imageDto.text) &&
                Objects.equals(url, imageDto.url) &&
                language == imageDto.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, url, language);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ImageDto{");
        sb.append("id=").append(id);
        sb.append(", text='").append(text).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", language=").append(language);
        sb.append('}');
        return sb.toString();
    }
}
