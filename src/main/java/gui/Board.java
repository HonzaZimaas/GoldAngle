package gui;

import service.DrawingService;
import service.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Board extends JPanel {
    private int width;
    private int height;

    private Settings settings;
    private BufferedImage bufferedImage;


    public Board(Settings settings) {
        this.width = settings.getWidth() - 30;
        this.height = settings.getHeight() - 50;
        this.settings = settings;
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

        DrawingService.initialCircle(graphics, settings);
        DrawingService.userCircle(graphics, settings, settings.getRadius());

        return bufferedImage;
    }

    public void cleanBoard(Graphics2D graphics) {
        DrawingService.cleanEverything(graphics, width, height);
    }

    public void paintPoint(Graphics graphics) {

//        NumberGenerator numberGenerator = new NumberGenerator();
//        int x = numberGenerator.getX;
//        int y = numberGenerator.getY;

        Random random = new Random();
        DrawingService.nextPoint((Graphics2D) graphics, random.nextInt(1500), random.nextInt(1000), settings,
                bufferedImage);
    }
}