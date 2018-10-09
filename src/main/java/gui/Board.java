package gui;

import service.DrawingService;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Board extends JPanel {
    private BufferedImage bufferedImage;

    private int width;
    private int height;


    public Board(int width, int height) {
        this.width = width - 30;
        this.height = height - 50;
    }

    @Override
    public void paint(Graphics graphics) {
        Image img = createImageInitialImage();
        graphics.drawImage(img, 10, 10, this);
    }

    private Image createImageInitialImage() {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        cleanBoard(graphics);

        DrawingService.initialCircle(graphics, width, height);

        DrawingService.initialCross(graphics, width, height);

        DrawingService.line(graphics, width, height);

        return bufferedImage;
    }

    private void cleanBoard(Graphics2D graphics) {
        DrawingService.clean(graphics, width, height);
    }


}