import lombok.Getter;
import lombok.Setter;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kopankom on 10.01.17.
 */

public class Config {

    @Getter
    public File file = null;

    @Getter
    List<File> files = new ArrayList<>();

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
            this.files.add(file);
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
            Collections.addAll(this.files, dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.toString().endsWith(".xliff");
                }
            }));
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
