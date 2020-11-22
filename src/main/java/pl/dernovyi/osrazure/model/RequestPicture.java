package pl.dernovyi.osrazure.model;

public class RequestPicture {
    String language;
    String url;

    public RequestPicture() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
