package com.eternal;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Function {
    private BigDecimal minX;
    private double maxX;
    private BigDecimal deltaX;
    private double[] yValues;
    private double[] xValues;

    public Function(double minX, double maxX, double deltaX) {
        this.minX = BigDecimal.valueOf(minX);
        this.maxX = maxX;
        this.deltaX = BigDecimal.valueOf(deltaX);
        fillArrays();
    }

    public int getIterations() {
        return (int) ((maxX - minX.doubleValue()) / deltaX.doubleValue());
    }

    public double getFuncValue(double x) {
        float a = 2.4f;
        if (x > a)
            return x * Math.sqrt(x - a);
        else if (x == a)
            return x * Math.sin(a * x);
        else
            return Math.pow(Math.E, -a * x) * Math.cos(a * x);
    }

    private void fillArrays() {
        int iterations = getIterations();
        yValues = new double[iterations];
        xValues = new double[iterations];
        BigDecimal xValue = minX;
        for (int i = 0; i < iterations; i++) {
            yValues[i] = getFuncValue(xValue.doubleValue());
            xValues[i] = xValue.doubleValue();
            xValue = xValue.add(deltaX);
        }
    }

    public String getMaxFuncValue() {
        int indexOfFuncMaxValue = IntStream.range(0, getIterations()).reduce((i, j) -> yValues[i] > yValues[j] ? i : j).getAsInt();
        return "№" + indexOfFuncMaxValue + " y = " + getYValueByIndex(indexOfFuncMaxValue) + " when x = " + getXValueByIndex(indexOfFuncMaxValue);
    }

    public String getMinFuncValue() {
        int indexOfFuncMinValue = IntStream.range(0, getIterations()).reduce((i, j) -> yValues[i] < yValues[j] ? i : j).getAsInt();
        return "№" + indexOfFuncMinValue + " y = " + getYValueByIndex(indexOfFuncMinValue) + " when x = " + getXValueByIndex(indexOfFuncMinValue);
    }

    public double getSumOfFuncValues() {
        return Arrays.stream(yValues).sum();
    }

    public double getAvgOfFuncValues() {
        return Arrays.stream(yValues).average().getAsDouble();
    }

    public double getXValueByIndex(int index) {
        return xValues[index];
    }

    public double getYValueByIndex(int index) {
        return yValues[index];
    }
}
