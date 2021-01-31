package ru.academits.java.ozhgibesov.shapes;

import java.util.Objects;

public class Triangle implements Shape {

    private double x1, x2, x3;
    private double y1, y2, y3;

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getX3() {
        return x3;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public double getY3() {
        return y3;
    }

    public void setX1(double x1) {
        if (Math.abs(((y3 - y1) * (x2 - x1)) - ((x3 - x1) * (y2 - y1))) <= epsilon) {
            System.out.println("Невозможно задать компоненту т.к. точки выстраиваются в линию. Компонента осталась без изменений.");
        } else {
            this.x1 = x1;
        }
    }

    public void setX2(double x2) {
        if (Math.abs(((y3 - y1) * (x2 - x1)) - ((x3 - x1) * (y2 - y1))) <= epsilon) {
            System.out.println("Невозможно задать компоненту т.к. точки выстраиваются в линию. Компонента осталась без изменений.");
        } else {
            this.x2 = x2;
        }
    }

    public void setX3(double x3) {
        if (Math.abs(((y3 - y1) * (x2 - x1)) - ((x3 - x1) * (y2 - y1))) <= epsilon) {
            System.out.println("Невозможно задать компоненту т.к. точки выстраиваются в линию. Компонента осталась без изменений.");
        } else {
            this.x3 = x3;
        }
    }

    public void setY1(double y1) {
        if (Math.abs(((y3 - y1) * (x2 - x1)) - ((x3 - x1) * (y2 - y1))) <= epsilon) {
            System.out.println("Невозможно задать компоненту т.к. точки выстраиваются в линию. Компонента осталась без изменений.");
        } else {
            this.y1 = y1;
        }
    }

    public void setY2(double y2) {
        if (Math.abs(((y3 - y1) * (x2 - x1)) - ((x3 - x1) * (y2 - y1))) <= epsilon) {
            System.out.println("Невозможно задать компоненту т.к. точки выстраиваются в линию. Компонента осталась без изменений.");
        } else {
            this.y2 = y2;
        }
    }

    public void setY3(double y3) {
        if (Math.abs(((y3 - y1) * (x2 - x1)) - ((x3 - x1) * (y2 - y1))) <= epsilon) {
            System.out.println("Невозможно задать компоненту т.к. точки выстраиваются в линию. Компонента осталась без изменений.");
        } else {
            this.y3 = y3;
        }
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (Math.abs(((y3 - y1) * (x2 - x1)) - ((x3 - x1) * (y2 - y1))) <= epsilon) {
            System.out.println("Невозможно построить треуегольник т.к. точки расположены на одной прямой.");
        } else {
            this.x1 = x1;
            this.y1 = y1;

            this.x2 = x2;
            this.y2 = y2;

            this.x3 = x3;
            this.y3 = y3;
        }
    }

    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    public double getArea() {
        double aBLength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double bCLength = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double aCLength = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        double perimeterHalf = (aBLength + bCLength + aCLength) / 2;

        return Math.sqrt(perimeterHalf * (perimeterHalf - aBLength) * (perimeterHalf - bCLength) * (perimeterHalf - aCLength));
    }

    public double getPerimeter() {
        double aBLength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double bCLength = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double aCLength = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        return aBLength + bCLength + aCLength;
    }

    @Override
    public String toString() {
        return "Triangle {" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                ", y3=" + y3 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.x1, x1) == 0 && Double.compare(triangle.x2, x2) == 0 && Double.compare(triangle.x3, x3) == 0 && Double.compare(triangle.y1, y1) == 0 && Double.compare(triangle.y2, y2) == 0 && Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 14;
        int hash = 4;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}