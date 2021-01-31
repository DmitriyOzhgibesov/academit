package ru.academits.java.ozhgibesov.shapes_main;

import ru.academits.java.ozhgibesov.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Shapes_main {
    public static void main(String[] args) {
        Triangle triangle1 = new Triangle(0, 0, 3, 0, 0, 4);
        Triangle triangle2 = new Triangle(-5, 5, 0, 0, 5, 5);
        Triangle triangle3 = new Triangle(-5, 5, 0, 0, 5, 5);

        Rectangle rectangle1 = new Rectangle(2, 3);
        Rectangle rectangle2 = new Rectangle(4, 5);

        Square square1 = new Square(5);
        Square square2 = new Square(4.5);

        Circle circle1 = new Circle(3);
        Circle circle2 = new Circle(3.5);

        Shape[] shapesArray = new Shape[]{triangle1, triangle2, triangle3, rectangle1, rectangle2, square1, square2, circle1, circle2};

        System.out.println("\nВывод неотсортированных фигур:\n");

        for (int i = 0; i < shapesArray.length; i++) {
            System.out.printf("Фигура %d: Площадь = %.2f; Периметр = %.2f   \n", i + 1, shapesArray[i].getArea(), shapesArray[i].getPerimeter());
        }

        System.out.println("\nВывод отсортированных фигур по площади:\n");

        Arrays.sort(shapesArray, new Comparator<>() {
            @Override
            public int compare(Shape shape1, Shape shape2) {
                return Double.compare(shape1.getArea(), shape2.getArea());
            }
        });

        for (int i = 0; i < shapesArray.length; i++) {
            System.out.printf("Площадь фигуры %d = %.2f\n", i + 1, shapesArray[i].getArea());
        }

        System.out.println("\nВывод отсортированных фигур по периметру:\n");

        Arrays.sort(shapesArray, new Comparator<>() {
            @Override
            public int compare(Shape shape1, Shape shape2) {
                return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
            }
        });

        for (int i = 0; i < shapesArray.length; i++) {
            System.out.printf("Периметр отсортированной фигуры %d = %.2f\n", i + 1, shapesArray[i].getPerimeter());
        }

        System.out.println("\nВывод результата переопределенных методов toString():\n");

        for (Shape shape : shapesArray) {
            System.out.println(shape.toString());
        }

        System.out.println("\nВывод результата переопределенных методов equals():\n");

        for (int i = 1; i < shapesArray.length; i++) {
            System.out.printf("Результат сравнения %s и %s - %b\n",
                    shapesArray[i - 1].toString(), shapesArray[i].toString(), shapesArray[i - 1].equals(shapesArray[i]));
        }

        System.out.println("\nВывод результата переопределенных методов equals():\n");

        for (Shape shape : shapesArray) {
            System.out.println(shape.hashCode());
        }
    }
}