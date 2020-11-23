package pl.dernovyi.osrazure.exception;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(Long message) {
        super("Image with number " + message + "  not found");
    }
}
