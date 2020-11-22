package pl.dernovyi.osrazure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dernovyi.osrazure.model.ImageDto;
import pl.dernovyi.osrazure.model.MyUrlInfo;
import pl.dernovyi.osrazure.model.RequestPicture;
import pl.dernovyi.osrazure.model.respEntity.ResponsePicture;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageToTextService {

    private final MapperToDto mapperToDto;

    private MyUrlInfo myUrlInfo;

    @Autowired
    public ImageToTextService(MapperToDto mapperToDto, MyUrlInfo myUrlInfo) {
        this.mapperToDto = mapperToDto;
        this.myUrlInfo = myUrlInfo;
    }

    public ImageDto getTextFromUrl(RequestPicture image){

        RestTemplate restTemplate= new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", myUrlInfo.getContentType());
        httpHeaders.add("Ocp-Apim-Subscription-Key", myUrlInfo.getOcpApimSubscriptionKey());

        Map<String, String> params = new HashMap<>();
        params.put("language", image.getLanguage());
        params.put("detectOrientation", "true");

        HttpEntity<RequestPicture> imageHttpEntity = new HttpEntity<>(image , httpHeaders);
        ResponseEntity<ResponsePicture> exchange = restTemplate.exchange(myUrlInfo.getUrl(), HttpMethod.POST, imageHttpEntity, ResponsePicture.class, params);
        ImageDto imageDto = mapperToDto.toDto(exchange);
        imageDto.setUrl(image.getUrl());

        return imageDto;

    }

}
