package gui;

import service.GeneratorType;
import service.Settings;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Runnable {
    private int height;
    private int width;

    private Board board;
    private Board board2;
    private Board board3;

    private Table table1;
    private Table table2;
    private Table table3;

    private boolean running = false;

    public MainFrame(int width, int height, String title) {
        this.width = width;
        this.height = height;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);
        setSize(width, height);
        setResizable(false);
        setIgnoreRepaint(false);
        setFocusable(true);

        SwingUtilities.invokeLater(() -> {
            SwingUtilities.invokeLater(() -> {
                SwingUtilities.invokeLater(() -> {
                    SwingUtilities.invokeLater(this::initFrame);
                });
            });
        });
        setVisible(true);
    }

    private void initFrame() {
        Settings boardsSettings = new Settings(width / 4, height / 2, 200);
        Dimension dimension = new Dimension(width / 4, height / 2);

        board = new Board(boardsSettings, GeneratorType.PRVNI);
        board2 = new Board(boardsSettings, GeneratorType.DRUHY);
        board3 = new Board(boardsSettings, GeneratorType.TRETI);

        board.setPreferredSize(dimension);
        board2.setPreferredSize(dimension);
        board3.setPreferredSize(dimension);

        // menu
        JPanel userPanel = new JPanel();
        userPanel.setPreferredSize(new Dimension(width / 4, height / 2));

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(20, 0));

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

        userPanel.setLayout(new GridLayout(0, 3));

        //The plonk panel
        JPanel left = new JPanel();
        userPanel.add(left);

        userPanel.add(right);


        // TABLES

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 0));
        panel1.add(board);

        table1 = new Table(board);
        table1.setPreferredSize(new Dimension(width / 5, height / 5));

        panel1.add(table1);
        panel1.setBackground(Color.darkGray);

        /////////////////////////////////

        JPanel panel2 = new JPanel();
        panel2.add(board2);
        panel2.setLayout(new GridLayout(2, 0));

        table2 = new Table(board2);
        table2.setPreferredSize(new Dimension(width / 5, height / 5));
        panel2.add(table2);
        panel2.setBackground(Color.darkGray);

        /////////////////////////////////

        JPanel panel3 = new JPanel();
        panel3.add(board3);
        panel3.setLayout(new GridLayout(2, 0));

        table3 = new Table(board3);
        table3.setPreferredSize(new Dimension(width / 5, height / 5));

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
        while (running) {
            board.paintPoint(board.getGraphics());
            board2.paintPoint(board2.getGraphics());
            board3.paintPoint(board3.getGraphics());

            updateTables();
        }
    }

    private void start() {
        running = !running;
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
}
