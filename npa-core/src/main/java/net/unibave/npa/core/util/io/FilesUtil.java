package net.unibave.npa.core.util.io;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FilesUtil {

    protected FilesUtil() {

    }

    public List<File> getFilesByExtension(String directory, String extension) {
        return Arrays.asList(new File(directory).listFiles((file, name) -> name.endsWith(extension)));
    }

    public List<File> getPropertiesFiles(String directory) {
        return getFilesByExtension(directory, FilesExtension.PROPERTIES);
    }

}
