package gui;

import javax.swing.*;
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

        Color colorA = board.getColorA();
        Color colorB = board.getColorB();

        //LABELS
        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 0, Color.darkGray));

        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        Font headLineFont = new Font("Arial", Font.BOLD, 25);
        Font labelText = new Font("Arial", Font.PLAIN, 15);
        Font varianceText = new Font("Arial", Font.BOLD, 18);

        JLabel generatorName = new JLabel(board.getGeneratorType().getGeneratorName());
        generatorName.setFont(headLineFont);
        add(generatorName);

        JLabel countOfPointsA = new JLabel("Počet bodů A:");
        countOfPointsA.setFont(labelText);
        countOfPointsA.setForeground(colorA);
        add(countOfPointsA);

        JLabel countOfPointsB = new JLabel("Počet bodů B:");
        countOfPointsB.setFont(labelText);
        countOfPointsB.setForeground(colorB);
        add(countOfPointsB);

        JLabel totalPoints = new JLabel("Počet bodů celkem:");
        totalPoints.setFont(labelText);
        add(totalPoints);

        JLabel def = new JLabel("Zlatý řez:");
        def.setFont(labelText);
        add(def);

        JLabel ration = new JLabel("Poměr bodů A : B:");
        ration.setFont(labelText);
        add(ration);

        JLabel variance = new JLabel("Odchylka:");
        variance.setFont(varianceText);
        add(variance);

        /// LABELS VALUE

        lvCountA = new JLabel("0");
        lvCountA.setFont(labelText);
        lvCountA.setForeground(colorA);
        add(lvCountA);

        lvCountB = new JLabel("0");
        lvCountB.setFont(labelText);
        lvCountB.setForeground(colorB);
        add(lvCountB);

        lvTotal = new JLabel("0");
        lvTotal.setFont(labelText);
        add(lvTotal);

        JLabel lvDef = new JLabel(board.getVariance());
        lvDef.setFont(labelText);
        add(lvDef);

        lvRation = new JLabel("0");
        lvRation.setFont(labelText);
        add(lvRation);

        lvVariance = new JLabel(board.getVariance());
        lvVariance.setFont(varianceText);
        add(lvVariance);

        //LABELS POSITION

        layout.putConstraint(SpringLayout.WEST, generatorName, 100, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, generatorName, 50, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, countOfPointsA, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, countOfPointsA, 30, SpringLayout.SOUTH, generatorName);

        layout.putConstraint(SpringLayout.WEST, countOfPointsB, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, countOfPointsB, 20, SpringLayout.SOUTH, countOfPointsA);

        layout.putConstraint(SpringLayout.WEST, totalPoints, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, totalPoints, 20, SpringLayout.SOUTH, countOfPointsB);

        layout.putConstraint(SpringLayout.WEST, def, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, def, 50, SpringLayout.SOUTH, totalPoints);

        layout.putConstraint(SpringLayout.WEST, ration, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, ration, 20, SpringLayout.SOUTH, def);

        layout.putConstraint(SpringLayout.WEST, variance, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, variance, 20, SpringLayout.SOUTH, ration);

        //LABELS VALUE POSITION

        layout.putConstraint(SpringLayout.WEST, lvCountA, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvCountA, 30, SpringLayout.SOUTH, generatorName);

        layout.putConstraint(SpringLayout.WEST, lvCountB, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvCountB, 20, SpringLayout.SOUTH, lvCountA);

        layout.putConstraint(SpringLayout.WEST, lvTotal, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvTotal, 20, SpringLayout.SOUTH, lvCountB);

        layout.putConstraint(SpringLayout.WEST, lvDef, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvDef, 50, SpringLayout.SOUTH, lvTotal);

        layout.putConstraint(SpringLayout.WEST, lvRation, 200, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lvRation, 20, SpringLayout.SOUTH, lvDef);

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
