package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class Table extends JPanel {
    private Board board;

    private JLabel lvCountA;
    private JLabel lvCountB;
    private JLabel lvTotal;
    private JLabel lvRation;
    private JLabel lvVariance;


    Table(Board board) {
        this.board = board;

        //LABELS

        LineBorder lineBorder = new LineBorder(Color.darkGray, 15);
        setBorder(lineBorder);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        Font headLineFont = new Font("Arial", Font.BOLD, 25);
        Font labelText = new Font("Arial", Font.PLAIN, 15);

        JLabel generatorName = new JLabel(board.getGeneratorType().getGeneratorName());
        generatorName.setFont(headLineFont);
        add(generatorName);

        JLabel countOfPointsA = new JLabel("Počet bodů A");
        countOfPointsA.setFont(labelText);
        add(countOfPointsA);

        JLabel countOfPointsB = new JLabel("Počet bodů B");
        countOfPointsB.setFont(labelText);
        add(countOfPointsB);

        JLabel totalPoints = new JLabel("Počet bodů celkem");
        totalPoints.setFont(labelText);
        add(totalPoints);

        JLabel ration = new JLabel("Poměr bodů");
        ration.setFont(labelText);
        add(ration);

        JLabel variance = new JLabel("Odchylka");
        variance.setFont(labelText);
        add(variance);

        /// LABELS VALUE

        lvCountA = new JLabel("0");
        lvCountA.setFont(labelText);
        add(lvCountA);

        lvCountB = new JLabel("0");
        lvCountB.setFont(labelText);
        add(lvCountB);

        lvTotal = new JLabel("0");
        lvTotal.setFont(labelText);
        add(lvTotal);

        lvRation = new JLabel("0");
        lvRation.setFont(labelText);
        add(lvRation);

        lvVariance = new JLabel(board.getVariance());
        lvVariance.setFont(labelText);
        add(lvVariance);

        //LABELS POSITION

        layout.putConstraint(SpringLayout.WEST, generatorName, 100, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, generatorName, 50, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, countOfPointsA, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, countOfPointsA, 20, SpringLayout.SOUTH, generatorName);

        layout.putConstraint(SpringLayout.WEST, countOfPointsB, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, countOfPointsB, 20, SpringLayout.SOUTH, countOfPointsA);

        layout.putConstraint(SpringLayout.WEST, totalPoints, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, totalPoints, 20, SpringLayout.SOUTH, countOfPointsB);

        layout.putConstraint(SpringLayout.WEST, ration, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, ration, 20, SpringLayout.SOUTH, totalPoints);

        layout.putConstraint(SpringLayout.WEST, variance, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, variance, 20, SpringLayout.SOUTH, ration);

        //LABELS VALUE POSITION

        layout.putConstraint(SpringLayout.WEST, lvCountA, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvCountA, 20, SpringLayout.SOUTH, generatorName);

        layout.putConstraint(SpringLayout.WEST, lvCountB, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvCountB, 20, SpringLayout.SOUTH, lvCountA);

        layout.putConstraint(SpringLayout.WEST, lvTotal, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvTotal, 20, SpringLayout.SOUTH, lvCountB);

        layout.putConstraint(SpringLayout.WEST, lvRation, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvRation, 20, SpringLayout.SOUTH, lvTotal);

        layout.putConstraint(SpringLayout.WEST, lvVariance, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvVariance, 20, SpringLayout.SOUTH, lvRation);
    }

    void updateTable() {
        lvCountA.setText(board.getCountA());
        lvCountB.setText(board.getCountB());
        lvTotal.setText(board.getTotal());
        lvRation.setText(board.getRation());
        lvVariance.setText(board.getVariance());
    }

    void cleanTableValue() {
        board.resetCounter();
        updateTable();
    }
}
