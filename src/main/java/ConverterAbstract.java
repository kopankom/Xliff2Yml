import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

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

    public abstract void convert() throws Exception;

    protected Document getXml() throws ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory
                .newInstance().newDocumentBuilder();
        Document document = null;
        try {
            document = documentBuilder.parse(this.getFile());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}

