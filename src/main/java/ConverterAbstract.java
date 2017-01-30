import lombok.Getter;
import lombok.Setter;

/**
 * Created by kopankom on 23.01.17.
 */
public abstract class ConverterAbstract {
    @Setter @Getter
    protected String fileContent;

    @Setter @Getter
    public String fileName;

    protected boolean loadFile() {
        return true;
    }

    protected boolean saveFile() {
        return true;
    }

    public abstract void convert();
}

