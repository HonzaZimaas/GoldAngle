package service;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingService {
    private static final int CROSS_SIZE = 10;
    private static final double GOLD_ANGLE = 137.51;
    private static final double COS2 = -Math.cos(GOLD_ANGLE);
    private static final double SIN2 = Math.sin(GOLD_ANGLE);

    private Settings settings;

    private int widthCenter;
    private int heightCenter;
    private int userRadius;
    private int xPointOnRound;
    private int yPointOnRound;

    private int lineConstant;
    private int vectorX;
    private int vectorY;

    private Counter counter = new Counter();

    public DrawingService(Settings settings) {
        this.settings = settings;

        this.widthCenter = settings.getWidth() / 2;
        this.heightCenter = settings.getHeight() / 2;
        this.userRadius = settings.getRadius();
    }

    public void userCircle(Graphics2D graphics, int radius) {
        if (radius == 0) {
            radius = settings.getRadius();
        }

        int diameter = radius * 2;

        // Circle
        graphics.setColor(Color.gray);
        graphics.fillOval(widthCenter - radius, heightCenter - radius, diameter, diameter);

        graphics.setColor(Color.black);
        graphics.drawOval(widthCenter - radius, heightCenter - radius, diameter, diameter);

        //Center cross
        graphics.setColor(Color.red);

        graphics.drawLine(widthCenter - CROSS_SIZE, heightCenter, widthCenter + CROSS_SIZE, heightCenter);
        graphics.drawLine(widthCenter, heightCenter - CROSS_SIZE, widthCenter, heightCenter + CROSS_SIZE);

        //Up line
        graphics.drawLine(widthCenter, heightCenter, widthCenter, heightCenter - radius);
        graphics.drawLine(widthCenter + 1, heightCenter, widthCenter + 1, heightCenter - radius);

        //Angle line
        xPointOnRound = (int) Math.round(((0 * (COS2)) - ((radius) * SIN2)));
        yPointOnRound = (int) Math.round(((0 * SIN2) + ((radius) * (COS2))));

        graphics.drawLine(widthCenter, heightCenter, xPointOnRound + widthCenter, settings.getHeight() - yPointOnRound - heightCenter);

        graphics.drawLine(widthCenter + 1, heightCenter, xPointOnRound + widthCenter + 1,
                settings.getHeight() - yPointOnRound - heightCenter);
        graphics.drawLine(widthCenter + -1, heightCenter, xPointOnRound + widthCenter + -1,
                settings.getHeight() - yPointOnRound - heightCenter);

        graphics.setColor(Color.black);
    }

    public void createPoint(Graphics2D graphics, int x, int y, BufferedImage bufferedImage) {


        if (true) {
            if (Math.hypot(widthCenter - x + 10, heightCenter - y + 10) <
                    userRadius) {
                graphics.setColor(getColorByLocations(x, y));

                graphics.drawRect(x, y, 0, 0);
            }
        }
    }

    private Color getColorByLocations(int x, int y) {
        if ((widthCenter < x - 10) && (y - 10 <= heightCenter)) {
            counter.setCountGreen(counter.getCountGreen() + 1);
            return Color.green;
        }
        if ((y - 10 > heightCenter)) {
            if (findingTheCorner(x, y)) {
                counter.setCountGreen(counter.getCountGreen() + 1);
                return Color.green;
            }
        }
        counter.setCountBlue(counter.getCountBlue() + 1);
        return Color.blue;
    }

    private boolean findingTheCorner(int a, int b) {

        if (lineConstant == 0) {
            vectorX = settings.getHeight() - yPointOnRound - heightCenter - heightCenter;
            vectorY = (xPointOnRound ) * -1;

            // general line
            lineConstant = ((-vectorX * widthCenter) - vectorY * heightCenter);
        }

        int xOnLine = (-vectorY * b) / vectorX - lineConstant / vectorX;
        return (a - xOnLine) > 0;
    }

    public float getRation() {
        return counter.getRation();
    }


    public static void cleanEverything(Graphics2D graphics, int width, int height) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);

    }

    private static boolean drawingIntoCirce(int x, int y, BufferedImage bufferedImage) {
        int rbg = 0;
        try {
            rbg = bufferedImage.getRGB(x - 10, y - 10);
        } catch (IndexOutOfBoundsException ignored) {
            System.out.println("Generated point is out of image");
        }
        return rbg != Color.red.getRGB() && rbg != Color.black.getRGB();
    }

}
