package main.java.maps;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Maps  extends JPanel{

    private JButton MyPosition;
    private JButton Cell;
    private JButton zoomPlus;
    private JButton zoomMinus;

    public Maps() {
        MyPosition = new JButton();
        Cell = new JButton();
        zoomPlus = new JButton();
        zoomMinus = new JButton();

        setPreferredSize(new Dimension(500, 500));

        //---- zoomPlus ----
        zoomPlus.setText("+");
        zoomPlus.addActionListener(e -> zoomPlusActionPerformed(e));

        //---- zoomMinus ----
        zoomMinus.setText("-");
        zoomMinus.addActionListener(e -> zoomMinusActionPerformed(e));

        GroupLayout mapsLayout = new GroupLayout(this);
        setLayout(mapsLayout);
        mapsLayout.setHorizontalGroup(
                mapsLayout.createParallelGroup()
                        .addGroup(mapsLayout.createSequentialGroup()
                                .addGroup(mapsLayout.createParallelGroup()
                                        .addGroup(mapsLayout.createSequentialGroup()
                                                .addGap(1059, 1059, 1059)
                                                .addGroup(mapsLayout.createParallelGroup()
                                                        .addComponent(MyPosition)
                                                        .addComponent(Cell)))
                                        .addGroup(mapsLayout.createSequentialGroup()
                                                .addGap(1076, 1076, 1076)
                                                .addGroup(mapsLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(zoomPlus, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(zoomMinus, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))))
                                .addGap(33, 33, 33))
        );
        mapsLayout.setVerticalGroup(
                mapsLayout.createParallelGroup()
                        .addGroup(mapsLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(MyPosition)
                                .addGap(18, 18, 18)
                                .addComponent(Cell)
                                .addGap(18, 18, 18)
                                .addComponent(zoomPlus)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(zoomMinus)
                                .addContainerGap(377, Short.MAX_VALUE))
        );
    }

    private void zoomPlusActionPerformed(ActionEvent e) {

    }

    private void zoomMinusActionPerformed(ActionEvent e) {

    }
}
