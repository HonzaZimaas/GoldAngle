package service;

public class Counter {

    private float countBlue = 0;
    private float countGreen = 0;


    public float getCountBlue() {
        return countBlue;
    }

    public void setCountBlue(float countBlue) {
        this.countBlue = countBlue;
    }

    public float getCountGreen() {
        return countGreen;
    }

    public void setCountGreen(float countGreen) {
        this.countGreen = countGreen;
    }

    public float getRation() {
        float ration = 0;
        try {
            ration = countBlue / countGreen ;
        } catch (ArithmeticException ignoreThat) {
            System.out.println("It is not possible ");
        }
        return ration;
    }
}
