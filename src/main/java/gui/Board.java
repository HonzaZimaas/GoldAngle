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
    private DrawingService drawingService;


    public Board(Settings settings) {
        this.width = settings.getWidth() - 30;
        this.height = settings.getHeight() - 50;
        this.settings = settings;

        this.drawingService = new DrawingService(settings);
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


        drawingService.userCircle(graphics, 150);

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
        drawingService.createPoint((Graphics2D) graphics, random.nextInt(1500), random.nextInt(1000),
                bufferedImage);
    }

    public String updateRation() {
        return Float.toString(drawingService.getRation());
    }
}