import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

/**
 * Created by kopankom on 30.01.17.
 */
public class Application {

    private Config config = new Config();

    public void setArgs(String[] args) {
        config.parse(args);
    }

    private void convertFiles() throws ConverterException {
        int filesCount = config.getFiles().size();
        if (0 == filesCount) {
            throw new ConverterException("No files to convert !");
        }
        for (int i = 0; i < filesCount; i++) {
            Xliff2YamlConverter converter = new Xliff2YamlConverter();
            File currentFile = config.getFiles().get(i);
            converter.setFile(currentFile);
            String newFilename = FileManager.obtainFilename(
                    currentFile, "%s.%s", config.getXliffExtension(), config.getYamlExtension());
            try {
                converter.convert();
            } catch (ParserConfigurationException e) {
            } catch (ConverterException e) {}
            converter.saveFile(newFilename);
        }
    }

    public void run() {
        try {
            convertFiles();
        } catch (ConverterException e) {}
    }
}
