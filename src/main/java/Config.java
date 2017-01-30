import lombok.Getter;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopankom on 10.01.17.
 */

public class Config {

    @Getter
    public File file = null;

    public File[] files = null;

    @Getter
    public File directory = null;

    @Argument
    private List<String> arguments = new ArrayList<String>();

    @Option(name="-file",usage="file name")
    public void setFile(File file) throws Exception {
        if (null != directory) {
            throw new Exception("You can't use -file and -dir params at once");
        }
        if (file.exists()) {
            this.files[0] = file;
        } else {
            throw new Exception("File not found !");
        }
    }

    @Option(name="-dir",usage="file name")
    public void setDirectory(File directory) throws Exception {
        if (null != file) {
            throw new Exception("You can't use -file and -dir params at once");
        }
        if (directory.exists() && directory.isDirectory()) {
            this.directory = directory;
            File dir = new File(String.valueOf(directory));
            File[] files = dir.listFiles();
        } else {
            throw new Exception("Directory not found or not directory !");
        }
    }


    public void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(80);

        try {
            parser.parseArgument(args);
        } catch( CmdLineException e ) {
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
            parser.printUsage(System.err);
            return;
        }
    }
}
