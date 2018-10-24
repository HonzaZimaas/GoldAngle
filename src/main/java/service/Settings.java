package service;

public class Settings {
    private int height;
    private int width;
    private int radius;

    public Settings(int width, int height, int radius) {
        this.width = width;
        this.height = height;
        this.radius = radius;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getRadius() {
        return radius;
    }
}
