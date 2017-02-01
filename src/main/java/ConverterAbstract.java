import lombok.Getter;
import lombok.Setter;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

/**
 * Created by kopankom on 23.01.17.
 */
public abstract class ConverterAbstract {
    @Setter @Getter
    protected String fileContent;

    @Setter @Getter
    public File file;

    protected boolean loadFile() {
        return true;
    }

    protected boolean saveFile() {
        return true;
    }

    public abstract void convert() throws ParserConfigurationException;
}

