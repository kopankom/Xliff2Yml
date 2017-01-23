import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kopankom on 10.01.17.
 */

public class Config {

    public File file = null;
    public File directory = null;

    @Option(name="-file",usage="file name")
    public void setFile(File file) throws Exception {
        if (file.exists()) {
            this.file = file;
        } else {
            throw new Exception("File not found !");
        }
    }

    @Option(name="-file",usage="file name")
    public void setDirectory(File directory) throws Exception {
        if (directory.exists()) {
            this.directory = directory;
        } else {
            throw new Exception("File not found !");
        }
    }

    @Argument
    private List<String> arguments = new ArrayList<String>();

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(80);

        try {
            parser.parseArgument(args);

            if( arguments.isEmpty() )
                throw new CmdLineException(parser,"No argument is given");

        } catch( CmdLineException e ) {
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            return;
        }
        System.out.println(recursive);
    }
}
