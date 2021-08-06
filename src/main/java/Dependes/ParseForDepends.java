package Dependes;

import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ParseForDepends {

    private String filePath;
    private List<Integer[]> temp = new ArrayList<>();

    public ParseForDepends(String filePath) {
        this.filePath = filePath;
    }

    public void loadLinks() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        Document document = documentBuilderFactory.newDocumentBuilder().parse(this.filePath);
        document.getDocumentElement().normalize();

        Node root = document.getDocumentElement();

        NodeList test = ((Element) root).getElementsByTagName("test");


        for (int k = 0; k < test.getLength(); k++) {
            Element book = (Element) test.item(k);
            if (book.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            temp.add(new Integer[]{Integer.parseInt(book.getAttribute("way")), Integer.parseInt(book.getAttribute("time"))});
        }
    }
}
