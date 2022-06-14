package kg.dispatcher.info.centre.prices.xmlFile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class ImportDataInXML {

    public void save(List<String[]> data){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            Document doc = factory.newDocumentBuilder().newDocument();


            Element root = doc.createElement("SaveData");
            root.setAttribute("xmlns", "http://www.javacore.ru/schemas/");

            int i=0;
            doc.appendChild(root);
            for (String[] item :
                    data) {
                root.appendChild(getLanguage(doc, String.valueOf(i), item[0],item[1],item[2],item[3],item[4],item[5],item[6]));
                i++;
            }


            File file = new File("src\\main\\resources\\test.xml");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(file));

        } catch (ParserConfigurationException | TransformerException e) {
        e.printStackTrace();
        }
    }


    // метод для создания нового узла XML-файла
    private static Node getLanguage(Document doc, String id, String venichle,String date, String longitude, String langitude, String absolutlyHigh, String distance,String speed) {
        Element language = doc.createElement("item");

        // устанавливаем атрибут id
        language.setAttribute("id", id);
        language.setAttribute("venihle", venichle);
        language.setAttribute("date", date);
        language.setAttribute("absolytlyHigh", absolutlyHigh);
        language.setAttribute("longitude", longitude);
        language.setAttribute("langitude", langitude);
        language.setAttribute("distance", distance);
        language.setAttribute("speed", speed);

        return language;
    }


    // утилитный метод для создание нового узла XML-файла
    private static Node getLanguageElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}
