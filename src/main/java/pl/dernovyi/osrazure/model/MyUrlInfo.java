package pl.dernovyi.osrazure.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyUrlInfo {
    @Value("${url}")
    private String url;
    @Value("${ContentType}")
    private String contentType;
    @Value("${Ocp-Apim-Subscription-Key}")
    private String ocpApimSubscriptionKey;

    public MyUrlInfo() {
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

    public String getOcpApimSubscriptionKey() {
        return ocpApimSubscriptionKey;
    }
}
