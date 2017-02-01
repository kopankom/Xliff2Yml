import java.io.IOException;

/**
 * Created by kopankom on 10.01.17.
 */
public class Xliff2Yaml {

    public static void main(String[] argv) throws IOException {
        Application app = new Application();
        app.setArgs(argv);
        app.run();
    }

}
