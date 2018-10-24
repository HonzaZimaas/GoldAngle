package service;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingService {
    private static final int CROSS_SIZE = 10;
    private static final int INITIAL_RADIUS = 400;
    private static final double GOLD_ANGLE = 137.51;
    private static final double COS2 = -Math.cos(GOLD_ANGLE);
    private static final double SIN2 = Math.sin(GOLD_ANGLE);


    static public void nextPoint(Graphics2D graphics, int x, int y, Settings settings, BufferedImage bufferedImage) {

        graphics.setColor(getColorByLocations(x, y, settings));
        if (drawingIntoCirce(x, y, bufferedImage)) {
            graphics.drawRect(x, y, 0, 0);
        }
    }

    static public void initialCircle(Graphics2D graphics, Settings settings) {
        userCircle(graphics, settings, INITIAL_RADIUS);
    }

    static public void userCircle(Graphics2D graphics, Settings settings, int radius) {
        int heightN = settings.getHeight() / 2;
        int widthN = settings.getWidth() / 2;

        if (radius == 0) {
            radius = settings.getRadius();
        }

        int diameter = radius * 2;

        // Circle
        graphics.setColor(Color.gray);
        graphics.fillOval(widthN - radius, heightN - radius, diameter, diameter);

        graphics.setColor(Color.black);
        graphics.drawOval(widthN - radius, heightN - radius, diameter, diameter);


        //Center cross
        graphics.setColor(Color.red);

        graphics.drawLine(widthN - CROSS_SIZE, heightN, widthN + CROSS_SIZE, heightN);
        graphics.drawLine(widthN, heightN - CROSS_SIZE, widthN, heightN + CROSS_SIZE);


        //Up line
        graphics.drawLine(widthN, heightN, widthN, heightN - radius);
        graphics.drawLine(widthN + 1, heightN, widthN + 1, heightN - radius);


        //Angle line
        int x2 = (int) Math.round(((0 * (COS2)) - ((radius) * SIN2)));
        int y2 = (int) Math.round(((0 * SIN2) + ((radius) * (COS2))));

        graphics.drawLine(widthN, heightN, x2 + widthN, settings.getHeight() - y2 - heightN);


        graphics.drawLine(widthN + 1, heightN, x2 + widthN + 1,
                settings.getHeight() - y2 - heightN);
        graphics.drawLine(widthN + -1, heightN, x2 + widthN + -1,
                settings.getHeight() - y2 - heightN);

        graphics.setColor(Color.black);

    }

    public static void cleanEverything(Graphics2D graphics, int width, int height) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);

    }

    private static Color getColorByLocations(int x, int y, Settings settings) {
        int heightN = settings.getHeight() / 2;
        int widthN = settings.getWidth() / 2;

        if ((widthN < x - 10) && (y - 10 < heightN))
            return Color.green;

        if ((y - 10 >= heightN)) {
            if (findingTheCorner(x, y, settings))
                return Color.green;
        }
        return Color.blue;
    }


    private static boolean findingTheCorner(int a, int b, Settings settings) {

        int x1 = settings.getWidth() / 2;
        int y1 = settings.getHeight() / 2;
        int x2 = (int) Math.round(((0 * (COS2)) - ((settings.getRadius()) * SIN2))) + x1;
        int y2 = settings.getHeight() - (int) Math.round(((0 * SIN2) + ((settings.getRadius()) * (COS2)))) - y1;


        float AB = distBetween(a, b, x1, y1);
        float BC = distBetween(x1, y1, x2, y2);
        float AC = distBetween(a, b, x2, y2);

        // Heron's formula
        float s = (AB + BC + AC) / 2;
        float area = (float) Math.sqrt(s * (s - AB) * (s - BC) * (s - AC));


        float AD = (2 * area) / BC;

        System.out.println(AD);
        return (a - AD) > x1 + 10;
    }


    private static float distBetween(float x, float y, float x1, float y1) {
        float xx = x1 - x;
        float yy = y1 - y;

        return (float) Math.sqrt(xx * xx + yy * yy);
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
