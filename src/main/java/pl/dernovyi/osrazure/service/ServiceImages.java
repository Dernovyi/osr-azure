package pl.dernovyi.osrazure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dernovyi.osrazure.model.ImageDto;
import pl.dernovyi.osrazure.model.RequestPicture;
import pl.dernovyi.osrazure.repo.ImageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImages {
    private final ImageRepository repository;
    private final ImageValidator validate;
    private final ImageToTextService textService;
    @Autowired
    public ServiceImages(ImageRepository repository, ImageValidator validate, ImageToTextService textService) {
        this.repository = repository;
        this.validate = validate;
        this.textService = textService;
    }

    public Optional<ImageDto> findImageById(Long id){
      return   repository.findById(id);
    }

    public  List<ImageDto> getAllImage(){
        return  repository.findAll();
    }

    private boolean isPresentURL(String url){
       return repository.existsPictureByUrl(url);
    }


    public ImageDto saveImage(ImageDto imageDto){
       return repository.save(imageDto);
    }
    public ImageDto saveNewImage(RequestPicture image) {
        if (validate.validate(image.getUrl())) {
            if(!isPresentURL(image.getUrl())){
                ImageDto textFromUrl = textService.getTextFromUrl(image);
                if(textFromUrl!=null){
                    return repository.save(textFromUrl);
                }
            }
        }
        return null;
    }
}
