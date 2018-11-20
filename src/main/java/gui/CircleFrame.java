package gui;

import service.Settings;

import javax.swing.*;
import java.awt.*;

public class CircleFrame extends JFrame implements Runnable {
    private int height;
    private int width;
    private Board board;
    private Settings settings;
    private boolean running = false;

    public CircleFrame(int width, int height, String title) {
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
                    SwingUtilities.invokeLater(() -> {
                        initFrame();
                    });
                });
            });
        });
        setVisible(true);
    }

    private void initFrame() {
        JPanel main = new JPanel();

        settings = new Settings(width / 5 * 4, height, 150);
        board = new Board(settings);
        JPanel panel = new JPanel();


        board.setPreferredSize(new Dimension(width / 5 * 4 + 20, height + 50));

        // menu
        panel.setBackground(Color.darkGray);
        panel.setPreferredSize(new Dimension(width / 5, height));

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(20, 0));

        right.add(new JTextField());
        right.add(new JTextField());
        right.add(new JTextField());

        JButton run = new JButton("RUN");
        run.addActionListener(e -> start());
        right.add(run);


        JPanel left = new JPanel();
        left.setLayout(new GridLayout(20, 0));

        left.add(new Label("Value_X"));
        left.add(new Label("Value_A"));
        left.add(new Label("Value_Y"));

        panel.setLayout(new GridLayout(0, 2));

        panel.add(left);
        panel.add(right);


        main.setLayout(new BorderLayout());
        main.add(board, BorderLayout.WEST);
        main.add(panel, BorderLayout.EAST);
        main.setBackground(Color.darkGray);
        setContentPane(main);
    }

    @Override
    public void run() {
        while (running) {
            board.paintPoint(board.getGraphics());
            System.out.println(board.updateRation());
        }
    }

    private void start() {
        running = !running;
        Thread thread = new Thread(this, "Creating");
        thread.start();
    }
}
