package gui;

import service.Counter;
import service.DrawingService;
import service.GeneratorType;
import service.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Board extends JPanel {
    private int width;
    private int height;

    private DrawingService drawingService;
    private GeneratorType type;


    public Board(Settings settings, GeneratorType type) {
        this.width = settings.getWidth() - 25;
        this.height = settings.getHeight();
        this.drawingService = new DrawingService(settings, type);
        this.type = type;
    }

    @Override
    public void paint(Graphics graphics) {
        Image img = createImageInitialImage();
        graphics.drawImage(img, 10, 10, this);
    }

    private Image createImageInitialImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

        drawingService.cleanEverything(graphics);
        drawingService.userCircle(graphics);

        return bufferedImage;
    }

    void cleanBoard(Graphics graphics) {
        createImageInitialImage();
        paint(graphics);
    }

    public void paintPoint(Graphics graphics) {

//        NumberGenerator numberGenerator = new NumberGenerator();
//        int x = numberGenerator.getX;
//        int y = numberGenerator.getY;

        Random random = new Random();
        drawingService.createPoint((Graphics2D) graphics, random.nextInt(500), random.nextInt(500));
    }

    public String getRation() {
        return Double.toString(drawingService.getCounter().getRation());
    }

    public String getCountA() {
        return Integer.toString(drawingService.getCounter().getCountA());
    }

    public String getCountB() {
        return Integer.toString(drawingService.getCounter().getCountB());
    }

    public String getTotal() {
        return Integer.toString(drawingService.getCounter().getTotal());
    }

    public String getVariance() {
        return Double.toString(drawingService.getCounter().getVariance());
    }

    public GeneratorType getGeneratorType() {
        return type;
    }

    public void resetCounter() {
        drawingService.setCounter(new Counter());
    }


}