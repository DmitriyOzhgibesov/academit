package ru.academits.java.ozhgibesov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        if (dimension < 0) {
            throw new IllegalArgumentException("dimension can't be < 0");
        }

        components = new double[dimension];
    }

    public Vector(Vector otherVector) {
        components = Arrays.copyOf(otherVector.components, otherVector.components.length);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (dimension < 0) {
            throw new IllegalArgumentException("dimension can't be < 0");
        }
        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        components = Arrays.copyOf(array, dimension);
    }

    public int getSize() {
        return components.length;
    }

    public Vector addVector(Vector vector) {
        int maxSize = Math.max(components.length, vector.components.length);

        components = Arrays.copyOf(components, maxSize);

        for (int i = 0; i < maxSize; i++) {
            components[i] += vector.components[i];
        }

        return this;
    }

    public Vector substractVector(Vector vector) {
        int maxSize = Math.max(components.length, vector.components.length);

        components = Arrays.copyOf(components, maxSize);

        for (int i = 0; i < maxSize; i++) {
            components[i] -= vector.components[i];
        }

        return this;
    }

    public Vector multiplyScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }

        return this;
    }

    public Vector reverse() {
        multiplyScalar(-1);
        return this;
    }

    public double getLength() {
        double sum = 0;

        for (double vectorComponent : components) {
            sum += vectorComponent * vectorComponent;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double component) {
        components[index] = component;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{ ");

        for (double component : components) {
            stringBuilder.append(String.format("%.2f", component)).append(", ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector otherVector = (Vector) o;
        return Arrays.equals(components, otherVector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 3;
        int hash = 5;
        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(vector1);
        return newVector.addVector(vector2);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(vector1);
        return newVector.substractVector(vector2);
    }

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.components.length, vector2.components.length);
        double scalarMultiplication = 0;

        for (int i = 0; i < minSize; i++) {
            scalarMultiplication += vector1.components[i] * vector2.components[i];
        }

        return scalarMultiplication;
    }
}