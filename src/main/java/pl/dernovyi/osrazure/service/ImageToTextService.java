package pl.dernovyi.osrazure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dernovyi.osrazure.model.ImageDto;
import pl.dernovyi.osrazure.model.RequestPicture;
import pl.dernovyi.osrazure.model.respEntity.ResponsePicture;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageToTextService {

    private final MapperToDto mapperToDto;
    @Value("${ContentType}")
    private String contentType;
    @Value("${Ocp-Apim-Subscription-Key}")
    private String ocpApimSubscriptionKey;
    @Value("${url}")
    private String url;

    @Autowired
    public ImageToTextService(MapperToDto mapperToDto) {
        this.mapperToDto = mapperToDto;
    }

    public ImageDto getTextFromUrl(RequestPicture image){

        RestTemplate restTemplate= new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", contentType);
        httpHeaders.add("Ocp-Apim-Subscription-Key", ocpApimSubscriptionKey);

        Map<String, String> params = new HashMap<>();
        params.put("language", image.getLanguage());
        params.put("detectOrientation", "true");

        HttpEntity<RequestPicture> imageHttpEntity = new HttpEntity<>(image , httpHeaders);
        ResponseEntity<ResponsePicture> exchange = restTemplate.exchange(url, HttpMethod.POST, imageHttpEntity, ResponsePicture.class, params);
        ImageDto imageDto = mapperToDto.toDto(exchange);
        imageDto.setUrl(image.getUrl());

        return imageDto;

    }

}
