package com.example.funwithstatistics2;

import java.util.Arrays;

public class Statistics {
    private int[] values;

    /**
     * Initializes a new MeasuresOfCentralTendency object.
     *
     * @param valuesIn an array of values.
     */
    public Statistics(int[] valuesIn) {
        if (valuesIn == null || valuesIn.length == 0) {
            throw new IllegalArgumentException();
        }
        values = valuesIn;
    }

    /**
     * Returns the values array.
     */
    public int[] getValuesArray() {
        return values;
    }

    /**
     * Calculates the mean of the values in the values array.
     */
    public double mean() {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return (double) (sum) / values.length;
    }

    /**
     * Calculates the median of the values in the values array.
     */
    public double median() {
        int[] copyOfValues = Arrays.copyOf(values, values.length);
        Arrays.sort(copyOfValues);
        if (copyOfValues.length % 2 != 0) {
            return copyOfValues[(copyOfValues.length - 1) / 2];
        }
        else {
            int leftValue = copyOfValues[copyOfValues.length / 2 - 1];
            int rightValue = copyOfValues[copyOfValues.length / 2];
            return (double) ((leftValue + rightValue)) / 2;
        }
    }

    /**
     * Calculates the range of the values in the values array.
     */
    public int range() {
        int smallestValue = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] <= smallestValue) {
                smallestValue = values[i];
            }
        }
        int greatestValue = values[0];
        for (int j = 1; j < values.length; j++) {
            if (values[j] >= greatestValue) {
                greatestValue = values[j];
            }
        }
        return greatestValue - smallestValue;
    }

    /**
     * Calculates the standard deviation of the values in the values array.
     */
    public double standardDeviation() {
        /* double[] squaredDifferences = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            squaredDifferences[i] = Math.pow((values[i] - mean()), 2);
        }
        double sumOfSquaredDifferences = 0;
        for (double squaredDifference : squaredDifferences) {
            sumOfSquaredDifferences += squaredDifference;
        }
        double meanOfSquaredDifferences = sumOfSquaredDifferences / squaredDifferences.length;
        return Math.sqrt(meanOfSquaredDifferences); */

        double mean = mean();
        double sumOfSquaredDifferences = 0;
        for (int i = 0; i < values.length; i++) {
            sumOfSquaredDifferences += Math.pow((values[i] - mean), 2);
        }
        return Math.sqrt(sumOfSquaredDifferences / values.length);
    }

    /**
     * Adds a value to the value array.
     *
     * @param valueToAdd value to add in the value array.
     */
    public void addValue(int valueToAdd) {
        values = Arrays.copyOf(values, values.length + 1);
        values[values.length - 1] = valueToAdd;
    }

    /**
     * Deletes the first occurence of valueToDelete.
     *
     * @param valueToDelete value to remove from the value array.
     */
    public boolean deleteValue(int valueToDelete) {
        if (values.length == 1 && values[0] == valueToDelete) {
            throw new IllegalStateException();
        }
        for (int i = 0; i < values.length; i++) {
            if (values[i] == valueToDelete) {
                for (int j = i; j < values.length - 1; j++) {
                    values[j] = values[j + 1];
                }
                values = Arrays.copyOf(values, values.length - 1);
                return true;
            }
        }
        return false;
    }
}
