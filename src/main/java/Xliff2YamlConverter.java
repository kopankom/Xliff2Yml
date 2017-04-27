import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by kopankom on 30.01.17.
 */
public class Xliff2YamlConverter extends ConverterAbstract {
    @Setter
    @Getter
    private Config config;

    @Override
    public void convert() throws ParserConfigurationException, ConverterException {
        NodeList translationItems = getTranslationNodes();
        if (0 == translationItems.getLength()) {
            throw new ConverterException("Node trans units in file");
        }
        for(int i = 0, c = translationItems.getLength(); i < c; i++) {
            Element element = (Element)translationItems.item(i);
            String source = getSource(element);
            String target = getTarget(element);
            target = "" != target ? target : source;
            append(source, target);
        }
    }

    private String getSource(Element element) throws ConverterException {
        if (0 == element.getElementsByTagName(config.getSourceNodeName()).getLength()) {
            throw new ConverterException("There is no source in node");
        }
        return element.getElementsByTagName(config.getSourceNodeName()).item(0).getTextContent();
    }

    private String getTarget(Element element) {
        if (0 != element.getElementsByTagName(config.getTargetNodeName()).getLength()) {
            return element.getElementsByTagName(config.getTargetNodeName()).item(0).getTextContent();
        }
        return "";
    }

    private NodeList getTranslationNodes() throws ParserConfigurationException {
        Document document = this.getXml();
        return document.getElementsByTagName(config.getTransUnitNodeName());
    }
}
