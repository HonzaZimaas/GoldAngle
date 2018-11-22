package gui;

import service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Board extends JPanel {
    private int width;
    private int height;

    private DrawingService drawingService;
    private NumberGenerator numberGenerator;
    private GeneratorType type;


    public Board(Settings settings, GeneratorType type) {
        this.width = settings.getWidth() - 25;
        this.height = settings.getHeight();

        this.type = type;

        this.drawingService = new DrawingService(settings, type);
        this.numberGenerator = new NumberGenerator(type);
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

        int x = numberGenerator.nextNumber(500);
        int y = numberGenerator.nextNumber(500);


        drawingService.createPoint((Graphics2D) graphics, x, y);
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