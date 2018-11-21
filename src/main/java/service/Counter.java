package service;

public class Counter {
    private static final double GOLD_CUT = 1.61803;
    private int countA = 0;
    private int countB = 0;

    public int getCountA() {
        return countA;
    }

    public void setCountA(int countA) {
        this.countA = countA;
    }

    public int getCountB() {
        return countB;
    }

    public void setCountB(int countB) {
        this.countB = countB;
    }

    public double getRation() {
        double ration = 0;
        try {
            ration = (double) countA / countB;
        } catch (ArithmeticException ignoreThat) {
            System.out.println("It is not possible ");
        }
        return (double) Math.round(ration * 100000) / 100000;
    }

    public int getTotal() {
        return getCountA() + getCountB();
    }

    public double getVariance() {
        double variance = Math.abs(GOLD_CUT - getRation());
        return (double) Math.round(variance * 100000) / 100000;
    }
}
