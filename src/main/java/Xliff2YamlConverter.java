import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by kopankom on 30.01.17.
 */
public class Xliff2YamlConverter extends ConverterAbstract {
    @Override
    public void convert() throws Exception {
        Document document = this.getXml();
        NodeList translationItems = document.getElementsByTagName("trans-unit");
        for(int i = 0, c = translationItems.getLength(); i < c; i++) {
            Element element = (Element)translationItems.item(i);
            if (0 == element.getElementsByTagName("source1").getLength()) {
                throw new ConverterException("There is no source in node");
            }
            System.out.println(element.getElementsByTagName("source1").getLength());
            System.out.println(element.getElementsByTagName("source").item(0).getTextContent());
            System.out.println(element.getElementsByTagName("target").item(0).getTextContent());
        }
    }
}
