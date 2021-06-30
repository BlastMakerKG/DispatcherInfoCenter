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

        NodeList books = ((Element) root).getElementsByTagName("ReliefItems");

        for(int i = 0; i < books.getLength(); i++) {
            Element book =  (Element)books.item(i);
            ReliefItems reliefItems = new ReliefItems();
            if(book.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if(book.hasChildNodes()){
                NodeList booksInto = book.getElementsByTagName("Point");

                for (int j = 0; j < booksInto.getLength(); j++) {
                    Element bookInto = (Element) booksInto.item(i);
                    if(book.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

                    reliefItems.setId(Long.parseLong(book.getAttribute("Id")));
                    reliefItems.setX(Double.parseDouble(bookInto.getAttribute("x")));
                    reliefItems.setY(Double.parseDouble(bookInto.getAttribute("y")));
                    reliefItems.setZ(Double.parseDouble(bookInto.getAttribute("z")));

                }
            }else {
                reliefItems.setId(Long.parseLong(book.getAttribute("Id")));
                reliefItems.setX(Double.parseDouble(book.getAttribute("x")));
                reliefItems.setY(Double.parseDouble(book.getAttribute("y")));
                reliefItems.setZ(Double.parseDouble(book.getAttribute("z")));
            }

            this.reliefItems.add(reliefItems);
        }
    }
}
