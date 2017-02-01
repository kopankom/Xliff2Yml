/**
 * Created by kopankom on 30.01.17.
 */
public class Application {

    private Config config = new Config();

    public void setArgs(String[] args) {
        config.parse(args);
    }

    private void convertFiles() {
        for (int i = 0, c = config.getFiles().length; i < c; i++) {
            System.out.println("a");

        }
    }

    public void run() {
        convertFiles();
    }
}
