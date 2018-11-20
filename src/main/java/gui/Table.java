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
    }


}
