package ru.academits.java.ozhgibesov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size = " + size + ". Size can't be <= 0");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array length = 0. Array length can't be = 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size = " + size + ". Size can't be <= 0");
        }

        components = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return components.length;
    }

    public Vector add(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }

        return this;
    }

    public Vector subtract(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }

        return this;
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }

        return this;
    }

    public Vector reverse() {
        multiplyByScalar(-1);
        return this;
    }

    public double getLength() {
        double sum = 0;

        for (double c : components) {
            sum += c * c;
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
        StringBuilder stringBuilder = new StringBuilder("{");

        for (double component : components) {
            stringBuilder.append(String.format("%.2f", component)).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}");
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

        Vector vector = (Vector) o;
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 3;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(vector1);
        return newVector.add(vector2);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(vector1);
        return newVector.subtract(vector2);
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