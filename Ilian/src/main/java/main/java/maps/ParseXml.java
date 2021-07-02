package main.java.maps;

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

public class ParseXml {

    private List<ReliefItems> reliefItems = new ArrayList<>();
    private String filePath ;

    public ParseXml(String filePath) {
        this.filePath = filePath;
    }

    public List<ReliefItems> getReliefItems(){
        return this.reliefItems;
    }

    public void loadLinks() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        Document document = documentBuilderFactory.newDocumentBuilder().parse(this.filePath);
        document.getDocumentElement().normalize();

        Node root = document.getDocumentElement();

        NodeList relief = ((Element) root).getElementsByTagName("RI");


        for (int k = 0; k < relief.getLength(); k++) {
            Element book = (Element) relief.item(k);
            ReliefItems reliefItems = new ReliefItems();
            if (book.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (book.hasChildNodes()) {
                NodeList booksInto = book.getElementsByTagName("P");

                for (int j = 0; j < booksInto.getLength(); j++) {
                    Element bookInto = (Element)booksInto.item(j);
                    if (book.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

                    reliefItems.setId(Long.parseLong(book.getAttribute("ID")));
                    reliefItems.setX(Double.parseDouble(bookInto.getAttribute("X")));
                    reliefItems.setY(Double.parseDouble(bookInto.getAttribute("Y")));
                    reliefItems.setZ(Double.parseDouble(bookInto.getAttribute("Z")));

                }
            } else {
                reliefItems.setId(Long.parseLong(book.getAttribute("ID")));
                reliefItems.setX(Double.parseDouble(book.getAttribute("X")));
                reliefItems.setY(Double.parseDouble(book.getAttribute("Y")));
                reliefItems.setZ(Double.parseDouble(book.getAttribute("Z")));
            }

            this.reliefItems.add(reliefItems);
        }
    }
}
