import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by kopankom on 30.01.17.
 */
public class Xliff2YamlConverter extends ConverterAbstract {
    @Override
    public void convert() throws ParserConfigurationException, ConverterException {
        NodeList translationItems = this.getTranslationNodes();
        for(int i = 0, c = translationItems.getLength(); i < c; i++) {
            Element element = (Element)translationItems.item(i);
            if (0 == element.getElementsByTagName("source").getLength()) {
                throw new ConverterException("There is no source in node");
            }
            String source = element.getElementsByTagName("source").item(0).getTextContent();
            String target = source;
            if (0 != element.getElementsByTagName("target").getLength()) {
                target = element.getElementsByTagName("target").item(0).getTextContent();
            }
            this.append(source, target);
        }
    }

    private NodeList getTranslationNodes() throws ParserConfigurationException {
        Document document = this.getXml();
        return document.getElementsByTagName("trans-unit");
    }
}
