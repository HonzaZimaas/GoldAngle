package gui;

import service.GeneratorType;
import service.Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static javax.swing.SwingUtilities.invokeLater;

public class MainFrame extends JFrame implements Runnable {
    private int height;
    private int width;

    private Board board;
    private Board board2;
    private Board board3;

    private Table table1;
    private Table table2;
    private Table table3;

    private JTextField fieldCountOfIteration;

    private boolean running = false;
    private int countOfIteration = 1000000;

    public MainFrame(int width, int height, String title) {
        this.width = width;
        this.height = height;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        setSize(width, height);
        setIgnoreRepaint(false);
        setFocusable(true);
        setResizable(false);

        invokeLater(() -> {
            invokeLater(() -> {
                invokeLater(() -> {
                    invokeLater(() -> {
                        invokeLater(() -> {
                            invokeLater(this::initFrame);
                        });
                    });
                });
            });
        });
        setVisible(true);
    }

    private void initFrame() {
        Settings boardsSettings = new Settings(width / 4 - 10, height / 2 - 30, 200);
        Dimension dimension = new Dimension(width / 4 - 10, height / 2 - 30);

        board = new Board(boardsSettings, GeneratorType.XORSHIFT);
        board2 = new Board(boardsSettings, GeneratorType.LCGSHIFT);
        board3 = new Board(boardsSettings, GeneratorType.MT19937);

        board.setPreferredSize(dimension);
        board2.setPreferredSize(dimension);
        board3.setPreferredSize(dimension);

        // menu
        JPanel userPanel = new JPanel();
        userPanel.setPreferredSize(new Dimension(width / 4, height / 2));

        JPanel userTopPanel = new JPanel();
        userTopPanel.setLayout(new GridLayout(0, 3));

        LineBorder lineBorder = new LineBorder(Color.darkGray, 10);
        userPanel.setBorder(lineBorder);

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(10, 0));

        right.add(new Label());

        fieldCountOfIteration = new JTextField();
        fieldCountOfIteration.setHorizontalAlignment(0);
        fieldCountOfIteration.setText(Integer.toString(countOfIteration));
        right.add(fieldCountOfIteration);

        JButton countOfIteration = new JButton("Set iteration level");
        countOfIteration.addActionListener(e -> setIteration());
        right.add(countOfIteration);

        JButton infinite = new JButton("Infinite iteration level");
        infinite.addActionListener(e -> setInfinite());
        right.add(infinite);

        right.add(new Label());

        JButton run = new JButton("Start");
        run.addActionListener(e -> start());
        right.add(run);


        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> stop());
        right.add(stop);


        JButton clean = new JButton("Clean");
        clean.addActionListener(e -> clean());
        right.add(clean);

        right.add(new Label());
        right.add(new Label());

        userPanel.setLayout(new GridLayout(2, 0));
        userPanel.add(userTopPanel);

        JPanel userTextPanel = new JPanel();
        /// TEXT?
        userPanel.add(userTextPanel);

        //The plonk panel
        JPanel left = new JPanel();
        userTopPanel.add(left);

        userTopPanel.add(right);


        // TABLES
        Dimension tableDimension1 = new Dimension(width / 4 - 10, height / 2);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 0));
        panel1.add(board);

        table1 = new Table(board);
        table1.setPreferredSize(tableDimension1);

        panel1.add(table1);
        panel1.setBackground(Color.darkGray);

        /////////////////////////////////

        JPanel panel2 = new JPanel();
        panel2.add(board2);
        panel2.setLayout(new GridLayout(2, 0));

        table2 = new Table(board2);
        table2.setPreferredSize(tableDimension1);
        panel2.add(table2);
        panel2.setBackground(Color.darkGray);

        /////////////////////////////////

        JPanel panel3 = new JPanel();
        panel3.add(board3);
        panel3.setLayout(new GridLayout(2, 0));

        table3 = new Table(board3);
        table3.setPreferredSize(tableDimension1);

        panel3.add(table3);
        panel3.setBackground(Color.darkGray);

        // MAIN PANEL
        JPanel main = new JPanel();

        main.setLayout(new GridLayout());
        main.add(panel1);
        main.add(panel2);
        main.add(panel3);
        main.add(userPanel);

        setContentPane(main);
    }

    @Override
    public void run() {
        running = !running;
        int iteration = 0;

        try {
            countOfIteration = Integer.parseInt(fieldCountOfIteration.getText());
        } catch (NumberFormatException ignored) {
            setInfinite();
        }


        while (running && iteration < countOfIteration) {
            board.paintPoint(board.getGraphics());
            board2.paintPoint(board2.getGraphics());
            board3.paintPoint(board3.getGraphics());

            updateTables();
            iteration++;
        }
    }

    private void start() {
        Thread thread = new Thread(this, "Creating");
        thread.start();
    }

    private void clean() {
        stop();

        board.cleanBoard(board.getGraphics());
        board2.cleanBoard(board2.getGraphics());
        board3.cleanBoard(board3.getGraphics());

        table1.cleanTableValue();
        table2.cleanTableValue();
        table3.cleanTableValue();
    }

    private void stop() {
        running = false;
    }

    private void updateTables() {
        table1.updateTable();
        table2.updateTable();
        table3.updateTable();
    }

    private void setIteration() {
        int count = 0;
        try {
            count = Integer.parseInt(fieldCountOfIteration.getText());
        } catch (NumberFormatException ignored) {
            fieldCountOfIteration.setText("0");
        }
        countOfIteration = count + 100000;
        fieldCountOfIteration.setText(Integer.toString(countOfIteration));
    }

    private void setInfinite() {
        fieldCountOfIteration.setText("Infinity ∞");
        countOfIteration = (int) Double.POSITIVE_INFINITY;
    }
}
