package pl.dernovyi.osrazure.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.dernovyi.osrazure.model.ImageDto;
import pl.dernovyi.osrazure.model.respEntity.*;

import java.util.List;

@Service
public class MapperToDto {
    public ImageDto toDto(ResponseEntity<ResponsePicture> exchange){

        exchange.getBody().getLanguage();
        StringBuilder t = new StringBuilder(5000);
        List<Region> regions = exchange.getBody().getRegions();
        regions.stream().forEach(region -> region.getLines().stream()
                .forEach(line -> line.getWords().stream()
                        .forEach(text -> t.append(text.getText() + " "))));

       ImageDto answer = new ImageDto();

       answer.setText(t.toString());
       String language = exchange.getBody().getLanguage();
       answer.setLanguage(Language.valueOf(language));
       return answer;
    }
}
