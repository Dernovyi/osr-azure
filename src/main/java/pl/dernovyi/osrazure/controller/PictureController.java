package pl.dernovyi.osrazure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dernovyi.osrazure.exception.ImageNotFoundException;
import pl.dernovyi.osrazure.model.ImageDto;
import pl.dernovyi.osrazure.model.RequestPicture;
import pl.dernovyi.osrazure.model.respEntity.Language;
import pl.dernovyi.osrazure.service.ImageToTextService;
import pl.dernovyi.osrazure.service.ImageValidator;
import pl.dernovyi.osrazure.service.ServiceImages;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/picture")
@CrossOrigin(origins = "https://osr-angular.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
public class PictureController {
    private final ImageToTextService textService;
    private final ImageValidator imageValidator;
    private final ServiceImages serviceImages;
    @Autowired
    public PictureController(ImageToTextService textService, ImageValidator imageValidator, ServiceImages serviceImages) {
        this.textService = textService;
        this.imageValidator = imageValidator;
        this.serviceImages = serviceImages;
    }

    @GetMapping("/lang")
    public Language[] getLanguage(){
        return Language.values();
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> getAll(){
      return new ResponseEntity<>(serviceImages.getAllImage(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ImageDto> findTextInPicture(@RequestBody RequestPicture picture){
        ImageDto imageDto = serviceImages.saveNewImage(picture);
        if(imageDto!=null){
            return new ResponseEntity<>(imageDto,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity saveChange(@RequestBody ImageDto imageDto){

        Long id = imageDto.getId();
        return new ResponseEntity(serviceImages.findImageById(id)
                .map(image -> {
                    image.setText(imageDto.getText());
                    image.setUpdatedOn(LocalDateTime.now());
                    return serviceImages.saveImage(image);
                }).orElseThrow(() -> new ImageNotFoundException(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getImage(@PathVariable Long id){
        if(serviceImages.findImageById(id).isPresent()){
            return new ResponseEntity(serviceImages.findImageById(id).get(), HttpStatus.OK);
        }
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
