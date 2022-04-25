package academy.reyngardt.annotation.processor;

import java.io.FileNotFoundException;

public class ResourcesManagerException extends FileNotFoundException {
    public ResourcesManagerException(String s) {
        super(s);
    }
}
