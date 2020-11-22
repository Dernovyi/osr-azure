package pl.dernovyi.osrazure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dernovyi.osrazure.model.ImageDto;
import pl.dernovyi.osrazure.model.RequestPicture;
import pl.dernovyi.osrazure.model.respEntity.Language;
import pl.dernovyi.osrazure.repo.ImageRepository;
import pl.dernovyi.osrazure.service.ImageToTextService;
import pl.dernovyi.osrazure.service.ImageValidator;

@RestController
@RequestMapping("/picture")
public class PictureController {
    private final ImageToTextService textService;
    private final ImageValidator imageValidator;
    private final ImageRepository repository;
    @Autowired
    public PictureController(ImageToTextService textService, ImageValidator imageValidator, ImageRepository repository) {
        this.textService = textService;
        this.imageValidator = imageValidator;
        this.repository = repository;
    }

    @GetMapping("/lang")
    public Language[] getLanguage(){
        return Language.values();
    }

    @GetMapping
    public ResponseEntity<ImageDto> findTextInPicture(@RequestBody RequestPicture picture){
        if(imageValidator.validate(picture.getUrl())){
            if(!repository.existsPictureByUrl(picture.getUrl())){
                ImageDto textFromUrl = textService.getTextFromUrl(picture);
                ImageDto save = repository.save(textFromUrl);
                return new ResponseEntity<>(save, HttpStatus.OK);
            }return new ResponseEntity<>(HttpStatus.CONFLICT);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }
}
