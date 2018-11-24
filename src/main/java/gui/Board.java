package gui;

import service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;


public class Board extends JPanel {
    private int width;
    private int height;

    private HashMap<Integer, int[]> mapOfPointInCircle;
    private DrawingService drawingService;
    private NumberGenerator numberGenerator;
    private GeneratorType generatorType;


    public Board(Settings settings, GeneratorType generatorType) {
        this.width = settings.getWidth() - 25;
        this.height = settings.getHeight();
        this.generatorType = generatorType;
        this.drawingService = new DrawingService(settings, generatorType);
        this.numberGenerator = new NumberGenerator(generatorType);
        this.mapOfPointInCircle = getPointsInRange(settings);
    }

    @Override
    public void paint(Graphics graphics) {
        Image img = createImageInitialImage();
        graphics.drawImage(img, 10, 10, this);
    }


    public void cleanBoard(Graphics graphics) {
        createImageInitialImage();
        paint(graphics);
    }

    public void paintPoint(Graphics graphics) {
        int key = numberGenerator.nextNumber(mapOfPointInCircle.size());

        int x = mapOfPointInCircle.get(key)[0];
        int y = mapOfPointInCircle.get(key)[1];

        drawingService.createPoint((Graphics2D) graphics, x, y);
    }

    public String getRation() {
        return String.format("%.5f", drawingService.getCounter().getRation());
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
        return String.format("%.5f", drawingService.getCounter().getVariance());
    }

    public GeneratorType getGeneratorType() {
        return generatorType;
    }

    public void resetCounter() {
        drawingService.setCounter(new Counter());
    }

    private Image createImageInitialImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

        drawingService.cleanEverything(graphics);
        drawingService.userCircle(graphics);

        return bufferedImage;
    }

    private HashMap<Integer, int[]> getPointsInRange(Settings settings) {
        mapOfPointInCircle = new HashMap<>();
        int centerX = settings.getWidth() / 2;
        int heightY = settings.getHeight() / 2;
        int h = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((Math.hypot(centerX - i + 10, heightY - j + 10) < settings.getRadius())) {
                    int[] xy = {i, j};
                    mapOfPointInCircle.put(h, xy);
                    h++;
                }
            }
        }
        return mapOfPointInCircle;
    }
}