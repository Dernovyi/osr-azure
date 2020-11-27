package pl.dernovyi.osrazure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.dernovyi.osrazure.model.ImageDto;
import pl.dernovyi.osrazure.model.RequestPicture;
import pl.dernovyi.osrazure.service.ServiceImages;
import pl.dernovyi.osrazure.service.StoreageService;


import java.net.URI;


@RestController
@RequestMapping("/drop")
@CrossOrigin(origins = "https://osr-angular.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
public class AzureBlobController {

    private final StoreageService service;
    private final ServiceImages serviceImages;

    @Autowired
    public AzureBlobController(StoreageService service, ServiceImages serviceImages) {
        this.service = service;
        this.serviceImages = serviceImages;
    }

    @PostMapping
    public ResponseEntity<ImageDto> get(@RequestParam("file") MultipartFile multipartFile){

        URI uri = service.saveToStorage(multipartFile);
        RequestPicture picture = new RequestPicture();
        String u = uri.toString();
        picture.setUrl(u);


        ImageDto imageDto = serviceImages.saveNewImage(picture);
        if(imageDto!=null){
            return new ResponseEntity<>(imageDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


}
