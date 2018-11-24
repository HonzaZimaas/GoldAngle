package service;

import java.awt.*;

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
        int innerRadius = userRadius / 4;

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
        graphics.setColor(getColorByLocations(x, y));
        graphics.drawRect(x, y, 0, 0);
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
                color1 = Color.blue;
                color2 = Color.green;
                break;
            case LCGSHIFT:
                color1 = Color.magenta;
                color2 = Color.red;
                break;
            case MT19937:
                color1 = Color.cyan;
                color2 = Color.orange;
                break;
            default:
                throw new IllegalStateException(type.toString());
        }
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }


}
