import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by kopankom on 30.01.17.
 */
public class Application {

    private Config config = new Config();

    public void setArgs(String[] args) {
        config.parse(args);
    }

    private void convertFiles() {
        for (int i = 0, c = config.getFiles().size(); i < c; i++) {
            Xliff2YamlConverter converter = new Xliff2YamlConverter();
            converter.setFile(config.getFiles().get(i));
            try {
                converter.convert();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            System.out.println("a");

        }
    }

    public void run() {
        convertFiles();
    }
}
