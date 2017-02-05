import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * Created by kopankom on 05.02.17.
 */
public class FileManager {

    public static String obtainFilename(File file, String fileSuffixFormat,
                                 String extensionToReplace, String newExtension) {
        String regex = String.format("(?i).%s$", extensionToReplace);
        String newFilename = file.getAbsolutePath().replaceAll(regex, "." + newExtension);
        String suffix = null;
        int i = 1;
        while (true == new File(newFilename).exists()) {
            suffix = String.format(fileSuffixFormat, String.valueOf(i), newExtension);
            newFilename = file.getAbsolutePath().replaceAll(regex, suffix);
            i++;
        }
        return newFilename;
    }
}
