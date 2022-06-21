package kg.dispatcher.info.centre.prices.UI;

import kg.dispatcher.info.centre.prices.UI.document.CostPrice;
import kg.dispatcher.info.centre.prices.UI.document.FactData;
import kg.dispatcher.info.centre.prices.planning.reportDocument.OriginalData;
import kg.dispatcher.info.centre.prices.planning.reportDocument.Output;
import kg.dispatcher.info.centre.prices.planning.model.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
public class Document extends JPanel {

    private JPanel panelOriginalData;
    private JPanel panelDataWithPrice;
    private JTabbedPane tabbedPane = new JTabbedPane();

    public Document(OriginalData currentData, Output outputData){

        setSize(800,600);
        setLayout(null);

        panelDataWithPrice = new CostPrice(outputData);
        panelOriginalData = new FactData(currentData);

        tabbedPane.addTab("Себестоимость",panelDataWithPrice);
        tabbedPane.addTab("Затраты", panelOriginalData);

        GroupLayout contentPaneLayout = new GroupLayout(this);
        setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                .addGap(10))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(tabbedPane)
        );
    }
}
