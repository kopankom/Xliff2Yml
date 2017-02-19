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
        NodeList translationItems = getTranslationNodes();
        for(int i = 0, c = translationItems.getLength(); i < c; i++) {
            Element element = (Element)translationItems.item(i);
            String source = getSource(element);
            String target = getTarget(element);
            target = "" != target ? target : source;
            append(source, target);
        }
    }

    private String getSource(Element element) throws ConverterException {
        if (0 == element.getElementsByTagName("source").getLength()) {
            throw new ConverterException("There is no source in node");
        }
        return element.getElementsByTagName("source").item(0).getTextContent();
    }

    private String getTarget(Element element) {
        if (0 != element.getElementsByTagName("target").getLength()) {
            return element.getElementsByTagName("target").item(0).getTextContent();
        }
        return "";
    }

    private NodeList getTranslationNodes() throws ParserConfigurationException {
        Document document = this.getXml();
        return document.getElementsByTagName("trans-unit");
    }
}
