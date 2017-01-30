import java.io.IOException;

/**
 * Created by kopankom on 10.01.17.
 */
public class Xliff2Yaml {

    public static void main(String[] argv) throws IOException {
        new Xliff2Yaml().doMain(argv);
    }

    public void doMain(String[] args) throws IOException {
        Config config = new Config();
        config.parse(args);
    }
}
