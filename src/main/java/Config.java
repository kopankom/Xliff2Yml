import lombok.Getter;
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
    List<File> files = new ArrayList<>();

    @Getter
    @Option(name = "-trans-unit", usage = "Translation unit node name")
    private String transUnitNodeName = "trans-unit";

    @Getter
    @Option(name = "-xliff-ext", usage = "Xliff files extension")
    private String xliffExtension = "xliff";

    @Getter
    @Option(name = "-source", usage = "Specify 'source' node name")
    private String sourceNodeName = "source";

    @Getter
    @Option(name = "-target", usage = "Specify 'target' node name")
    private String targetNodeName = "target";

    @Getter
    @Option(name = "-yaml-ext", usage = "Yaml files extension")
    private String yamlExtension = "yml";

    @Option(name = "-file", usage = "Specify file name to convert", forbids = "-dir")
    private void setFile(File file) throws ConverterException {
        if (file.exists()) {
            this.files.add(file);
        } else {
            throw new ConverterException("File not found !");
        }
    }

    @Option(name="-dir",usage="Directory path to convert - convert all files inside directory",forbids = "-file")
    private void setDirectory(File directory) throws ConverterException {
        if (directory.exists() && directory.isDirectory()) {
            File dir = new File(String.valueOf(directory));
            Collections.addAll(this.files, dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                return pathname.toString().endsWith(".xliff");
                }
            }));
        } else {
            throw new ConverterException("Directory not found or not directory !");
        }
    }

    public void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch( CmdLineException e ) {
            System.err.println("\nXliff2Yaml Converter [options...]");
            parser.printUsage(System.err);
        }
    }
}
