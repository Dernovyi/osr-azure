package pl.dernovyi.osrazure.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImageValidator {
    private Pattern pattern;
    private Matcher matcher;


    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg|tiff|pdf))$)";

    public ImageValidator() {
        pattern = Pattern.compile(IMAGE_PATTERN);
    }

    public boolean validate(final String image) {

        if(image.length()>255){
            return false;
        }
        matcher = pattern.matcher(image);
        return matcher.matches();

    }
}
