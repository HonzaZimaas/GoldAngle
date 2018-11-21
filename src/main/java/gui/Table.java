package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Table extends JPanel {
    private Board board;

    public Table(Board board) {
        this.board = board;


        LineBorder lineBorder = new LineBorder(Color.darkGray, 15);
        setBorder(lineBorder);

        setLayout(new BorderLayout());


        JPanel panel = new JPanel();
        panel.setSize(new Dimension(100, 100));
        panel.setBackground(Color.white);

        add(panel, BorderLayout.CENTER);


        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        Component b1 = new JButton("Button 1");
        Component b2 = new JButton("Button 2");

        panel.add(b1);
        panel.add(b2);
        layout.putConstraint(SpringLayout.WEST, b1, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, b2, 10, SpringLayout.NORTH, panel);
    }


}
