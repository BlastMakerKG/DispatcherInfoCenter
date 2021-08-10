package XmlFile;

import lombok.Getter;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.*;


@Getter
public class ParseXmlPoints {

    private List<ReliefItems> reliefItems = new ArrayList<>();
    private HashMap< Integer, LinkedList<ReliefItems>> geoLines = new HashMap<>();
    private String filePath ;

    public ParseXmlPoints(String filePath) {
        this.filePath = filePath;
    }

    public List<ReliefItems> getReliefItems(){
        return this.reliefItems;
    }

    public void loadLinks() {
        int index = 0;
        NodeList relief = null;
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            Document document = documentBuilderFactory.newDocumentBuilder().parse(this.filePath);
            document.getDocumentElement().normalize();

            Node root = document.getDocumentElement();

            relief = ((Element) root).getElementsByTagName("RI");
        }catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }


        for (int k = 0; k < relief.getLength(); k++) {
            Element book = (Element) relief.item(k);
            if (book.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (book.hasChildNodes()) {
                NodeList booksInto = book.getElementsByTagName("P");
                LinkedList<ReliefItems> temp = new LinkedList<>();

                for (int j = 0; j < booksInto.getLength(); j++) {
                    ReliefItems reliefItems = new ReliefItems();
                    Element bookInto = (Element)booksInto.item(j);
                    if (bookInto.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

                    reliefItems.setId(Long.parseLong(book.getAttribute("ID")));
                    reliefItems.setX(Double.parseDouble(bookInto.getAttribute("X")));
                    reliefItems.setY(Double.parseDouble(bookInto.getAttribute("Y")));
                    reliefItems.setZ(Double.parseDouble(bookInto.getAttribute("Z")));

                    temp.add(reliefItems);
                }
                this.geoLines.put(index, temp);
                index++;
            } else {
                ReliefItems reliefItems = new ReliefItems();
                reliefItems.setId(Long.parseLong(book.getAttribute("ID")));
                reliefItems.setX(Double.parseDouble(book.getAttribute("X")));
                reliefItems.setY(Double.parseDouble(book.getAttribute("Y")));
                reliefItems.setZ(Double.parseDouble(book.getAttribute("Z")));

                this.reliefItems.add(reliefItems);
            }

        }
    }
}
