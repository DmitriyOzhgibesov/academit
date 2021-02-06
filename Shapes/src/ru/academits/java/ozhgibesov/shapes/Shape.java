package ru.academits.java.ozhgibesov.shapes;

public interface Shape {
    double EPSILON = 1e-10;

    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();
}