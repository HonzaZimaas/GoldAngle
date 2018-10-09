package service;

import java.awt.*;

public class DrawingService {
    private static final int CROSS_SIZE = 10;
    private static final int INITIAL_RADIUS = 400;
    private static final double GOLD_ANGLE = 137.51;


    static public void nextPoint() {
        int a = 0;
        while (a < 10) {
            a = a + 1;
            System.out.println("-------Generace:" + a + "-------");
        }
    }


//    static public void drawCircle(JFrame jp, int height, int width, int radius) {
//
//        Graphics2D graphics = (Graphics2D) jp.getGraphics();
//
//        graphics.setColor(Color.red);
//        graphics.drawOval(width / 5 * 4 / 2, height / 2, radius, radius);
//
//    }

    static public void initialCircle(Graphics2D graphics, int width, int height) {
        int heightN = height / 2;
        int widthN = width / 2;
        int diameter = INITIAL_RADIUS * 2;

        graphics.setColor(Color.gray);
        graphics.fillOval(widthN - INITIAL_RADIUS, heightN - INITIAL_RADIUS, diameter, diameter);

        graphics.setColor(Color.black);
        graphics.drawOval(widthN - INITIAL_RADIUS, heightN - INITIAL_RADIUS, diameter, diameter);

        graphics.drawLine(widthN, heightN, widthN, heightN - INITIAL_RADIUS);


    }

    public static void initialCross(Graphics2D graphics, int width, int height) {
        int heightN = height / 2;
        int widthN = width / 2;

        graphics.setBackground(Color.cyan);
        graphics.setColor(Color.red);

        graphics.drawLine(widthN - CROSS_SIZE, heightN, widthN + CROSS_SIZE, heightN);
        graphics.drawLine(widthN, heightN - CROSS_SIZE, widthN, heightN + CROSS_SIZE);
    }

    public static void clean(Graphics2D graphics, int width, int height) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);
    }

    public static void line(Graphics2D graphics, int width, int height) {
        int heightN = height / 2;
        int widthN = width / 2;

        int x2 = (int) Math.round((widthN * 0.675462) - (heightN * 0.737395));
        int y2 = (int) Math.round((widthN * 0.737395) + (heightN * 0.675462));

        System.out.println(widthN);
        System.out.println(heightN);

        System.out.println(x2);
        System.out.println(y2);

        graphics.drawLine(widthN, heightN,   widthN - x2,   y2  );


    }


}
