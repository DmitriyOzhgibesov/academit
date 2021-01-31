package ru.academits.java.ozhgibesov.shapes;

import java.util.Objects;

public class Square implements Shape {
    private double sideLength;

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double length) {
        this.sideLength = length;
    }

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getWidth() {
        return sideLength;
    }

    public double getHeight() {
        return sideLength;
    }

    public double getArea() {
        return Math.pow(sideLength, 2);
    }

    public double getPerimeter() {
        return (sideLength + sideLength) * 2;
    }

    @Override
    public String toString() {
        return "Square {" +
                "sideLength=" + sideLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(square.sideLength, sideLength) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 3;
        hash = prime * hash + Double.hashCode(sideLength);
        return hash;
    }
}