package academy.reyngardt.annotation.processor;

import javax.annotation.processing.Filer;
import javax.tools.StandardLocation;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Mikhail Reyngardt 23.04.2022
 */
public class ResourcesManager {

    private final Filer filer;

    public ResourcesManager(Filer filer) {
        this.filer = filer;
    }

    public String getFilePath(String file) throws ResourcesManagerException {
        String path;
        for (int i = 0; i < StandardLocation.values().length; ) {
            try {
                path = getPathOfFileFromLocation(file, StandardLocation.values()[i]);
                return path;
            } catch (IOException exception) {
                ++i;
            }
        }
        throw new ResourcesManagerException(String.format("file not found: %s", file));
    }

    private String getPathOfFileFromLocation(String file, StandardLocation location) throws IOException {
        URL resource = filer.getResource(location, "", file).toUri().toURL();
        return new File(resource.getFile()).toPath().toString();
    }
}
