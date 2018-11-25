package service;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class DrawingService {
    private static final int CROSS_SIZE = 10;
    private static final double GOLD_ANGLE = 137.51;
    private static final double COS2 = -Math.cos(GOLD_ANGLE);
    private static final double SIN2 = Math.sin(GOLD_ANGLE);

    private int widthCenter;
    private int heightCenter;
    private int userRadius;
    private int xPointOnRound;
    private int yPointOnRound;

    private int lineConstant;
    private int vectorX;
    private int vectorY;

    private Color color1;
    private Color color2;

    private Settings settings;
    private List<Points> points = new ArrayList<>();


    private Counter counter = new Counter();

    public DrawingService(Settings settings, GeneratorType type) {
        this.settings = settings;
        this.widthCenter = settings.getWidth() / 2;
        this.heightCenter = settings.getHeight() / 2;
        this.userRadius = settings.getRadius();

        chooseColorSchema(type);
    }

    public void userCircle(Graphics2D graphics) {
        int diameter = userRadius * 2;

        // Circle
        graphics.setColor(Color.gray);
        graphics.fillOval(widthCenter - userRadius, heightCenter - userRadius, diameter, diameter);

        graphics.setColor(Color.black);
        graphics.drawOval(widthCenter - userRadius, heightCenter - userRadius, diameter, diameter);

        //Center cross
        graphics.setColor(Color.red);

        graphics.drawLine(widthCenter - CROSS_SIZE, heightCenter, widthCenter + CROSS_SIZE, heightCenter);
        graphics.drawLine(widthCenter, heightCenter - CROSS_SIZE, widthCenter, heightCenter + CROSS_SIZE);

        //Up line
        graphics.drawLine(widthCenter, heightCenter, widthCenter, heightCenter - userRadius);
        graphics.drawLine(widthCenter + 1, heightCenter, widthCenter + 1, heightCenter - userRadius);

        //Angle line
        xPointOnRound = (int) Math.round(((0 * (COS2)) - ((userRadius) * SIN2)));
        yPointOnRound = (int) Math.round(((0 * SIN2) + ((userRadius) * (COS2))));

        graphics.drawLine(widthCenter, heightCenter, xPointOnRound + widthCenter, settings.getHeight() - yPointOnRound - heightCenter);

        graphics.drawLine(widthCenter + 1, heightCenter, xPointOnRound + widthCenter + 1,
                settings.getHeight() - yPointOnRound - heightCenter);
        graphics.drawLine(widthCenter + -1, heightCenter, xPointOnRound + widthCenter + -1,
                settings.getHeight() - yPointOnRound - heightCenter);

        graphics.setColor(Color.black);
    }

    public void createPoint(Graphics2D graphics, int x, int y) {
        Color colorForBackup = getColorByLocations(x, y);
        graphics.setColor(colorForBackup);
        graphics.drawRect(x, y, 0, 0);

        points.add(new Points(x - 10, y - 10, colorForBackup));

    }

    public void cleanEverything(Graphics2D graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, settings.getWidth(), settings.getHeight());

    }

    private Color getColorByLocations(int x, int y) {
        if ((widthCenter < x - 10) && (y - 10 <= heightCenter)) {
            counter.setCountB(counter.getCountB() + 1);
            return color2;
        }
        if ((y - 10 > heightCenter)) {
            if (findingTheCorner(x, y)) {
                counter.setCountB(counter.getCountB() + 1);
                return color2;
            }
        }
        counter.setCountA(counter.getCountA() + 1);
        return color1;
    }

    private boolean findingTheCorner(int a, int b) {

        if (lineConstant == 0) {
            vectorX = settings.getHeight() - yPointOnRound - heightCenter - heightCenter;
            vectorY = (xPointOnRound) * -1;

            // general line
            lineConstant = ((-vectorX * widthCenter) - vectorY * heightCenter);
        }

        int xOnLine = (-vectorY * b) / vectorX - lineConstant / vectorX;
        return (a - xOnLine) > 3;
    }

    private void chooseColorSchema(GeneratorType type) {
        switch (type) {
            case XORSHIFT:
                color1 = new Color(0, 0, 255);
                color2 = new Color(0, 100, 0);
                break;
            case LCGSHIFT:
                color1 = new Color(255, 0, 0);
                color2 = new Color(97, 26, 10);
                break;
            case MT19937:
                color1 = new Color(255, 140, 0);
                color2 = new Color(128, 0, 128);
                break;
            default:
                throw new IllegalStateException(type.toString());
        }
    }

    public void backupPoints(Graphics2D graphics) {
        List<Points> backupList = new ArrayList<>(points);
        for (Points p : backupList) {
            try {
                graphics.setColor(p.getColor());
                graphics.drawRect(p.getX(), p.getY(), 0, 0);
            } catch (ConcurrentModificationException ignored) {
            }
        }
    }

    public void resetBackupPoints() {
        points.clear();
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    class Points {
        private int x;
        private int y;
        private Color color;

        public Points(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Color getColor() {
            return color;
        }
    }
}
