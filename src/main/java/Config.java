import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kopankom on 10.01.17.
 */


public class Config {

    public String recursive = null;

    @Option(name="-a",usage="append something")
    public String append = null;

    @Argument
    private List<String> arguments = new ArrayList<String>();

    public String getRecursive() {
        return recursive;
    }

    @Option(name="-r",usage="recursively run something")
    public void setRecursive(String recursive) {
        Pattern p = Pattern.compile("^t[a-z0-9 ]+3$");
        Matcher m = p.matcher(recursive);
        boolean b = m.matches();
        this.recursive = recursive;
    }

    public String getAppend() {
        return append;
    }

    public void setAppend(String append) {
        this.append = append;
    }

    public List<String> getArguments() {
        return arguments;
    }

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
        System.out.println(append);
    }
}
