import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by kopankom on 30.01.17.
 */
public class Xliff2YamlConverter extends ConverterAbstract {
    @Override
    public void convert() throws ParserConfigurationException {
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
        NodeList translationItems = document.getElementsByTagName("trans-unit");
        for(int i = 0, c = translationItems.getLength(); i < c; i++) {
            Element element = (Element)translationItems.item(i);
            System.out.println(element.getElementsByTagName("source").item(0).getTextContent());
            System.out.println(element.getElementsByTagName("target").item(0).getTextContent());
        }

    }
}
