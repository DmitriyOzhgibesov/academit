package ru.academits.java.ozhgibesov.vector;

import java.util.Arrays;

public class Vector {

    private double[] vector;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Dimension can't be <= 0");
        } else {
            vector = new double[n];
            for (int i = 0; i < n; i++) {
                vector[i] = 0;
            }
        }
    }

    public Vector(Vector vector) {
        this.vector = new double[vector.getSize()];

        for (int i = 0; i < vector.getSize(); i++) {
            this.vector[i] = vector.getComponent(i);
        }
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Dimension cannot be <= 0");
        } else {
            vector = Arrays.copyOf(array,array.length);
        }
    }

    public Vector(int n, double[] array) {
        if (n <= 0 && array.length <= 0) {
            throw new IllegalArgumentException("Dimension cannot be <= 0");
        } else {
            vector = new double[n];

            for (int i = 0; i < n; i++) {
                if (i < array.length) {
                    vector[i] = array[i];
                } else {
                    vector[i] = 0;
                }
            }
        }
    }

    public int getSize() {
        return vector.length;
    }

    public Vector getVectorAddition(Vector vector) {
        double[] buffer = new double[Math.max(this.vector.length, vector.getSize())];

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = 0;
            if (i < this.vector.length) {
                buffer[i] = this.vector[i] + vector.getComponent(i);
            } else {
                buffer[i] += vector.getComponent(i);
            }
        }

        return new Vector(buffer);
    }

    public Vector getVectorDifference(Vector vector) {
        double[] buffer = new double[Math.max(this.vector.length, vector.getSize())];

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = 0;
            if (i < this.vector.length) {
                buffer[i] = this.vector[i] - vector.getComponent(i);
            } else {
                buffer[i] -= vector.getComponent(i);
            }
        }

        return new Vector(buffer);
    }

    public Vector getScalarMultiplication(double scalar) {
        double[] result = new double[vector.length];

        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] * scalar;
        }

        return new Vector(result);
    }

    public Vector getReverse() {
        double[] buffer = new double[this.vector.length];

        for (int i = 0; i < this.vector.length; i++) {
            buffer[i] = this.vector[i] * -1;
        }

        return new Vector(buffer);
    }

    public double getVectorLength() {
        double sum = 0;

        for (double vectorComponent : vector) {
            sum += vectorComponent * vectorComponent;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        return vector[index];
    }

    public void setComponent(int index, double component) {
        vector[index] = component;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (int i = 0; i < vector.length; i++) {
            if (i < vector.length - 1) {
                result.append(String.format("%.2f", vector[i]));
                result.append(", ");
            } else {
                result.append(String.format("%.2f", vector[i]));
            }
        }
        return result.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector1 = (Vector) o;
        return Arrays.equals(vector, vector1.vector) && vector.length == vector1.getSize();
    }

    @Override
    public int hashCode() {
        final int prime = 3;
        int hash = 5;
        for (double vectorComponent : vector) {
            hash = prime * hash + Double.hashCode(vectorComponent);
        }

        return hash;
    }

    public static Vector Sum(Vector vector1, Vector vector2) {
        double[] buffer = new double[Math.max(vector1.getSize(), vector2.getSize())];

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = 0;

            if (i < vector1.getSize()) {
                buffer[i] = vector1.getComponent(i) + vector2.getComponent(i);
            } else {
                buffer[i] += vector2.getComponent(i);
            }
        }

        return new Vector(buffer);
    }

    public static Vector Difference(Vector vector1, Vector vector2) {
        double[] buffer = new double[Math.max(vector1.getSize(), vector2.getSize())];

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = 0;

            if (i < vector1.getSize()) {
                buffer[i] = vector1.getComponent(i) - vector2.getComponent(i);
            } else {
                buffer[i] -= vector2.getComponent(i);
            }
        }

        return new Vector(buffer);
    }

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        double[] buffer = new double[Math.max(vector1.getSize(), vector2.getSize())];

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = 0;

            if (i < vector1.getSize() && i < vector2.getSize()) {
                buffer[i] = vector1.getComponent(i) * vector2.getComponent(i);
            } else if (i < vector2.getSize()) {
                buffer[i] *= vector2.getComponent(i);
            } else {
                buffer[i] *= vector1.getComponent(i);
            }
        }

        return Arrays.stream(buffer).sum();
    }
}