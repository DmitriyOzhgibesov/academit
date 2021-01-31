package ru.academits.java.ozhgibesov.shapes;

import java.util.Comparator;

public interface Shape {
    double epsilon = 1e-10;

    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();
}