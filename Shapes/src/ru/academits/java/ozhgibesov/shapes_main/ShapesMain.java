package ru.academits.java.ozhgibesov.shapes_main;

import ru.academits.java.ozhgibesov.comparators.AreaComparator;
import ru.academits.java.ozhgibesov.comparators.PerimeterComparator;
import ru.academits.java.ozhgibesov.shapes.*;

import java.util.Arrays;

public class ShapesMain {
    public static void main(String[] args) {
        Shape[] shapesArray = {new Triangle(0, 0, 3, 0, 0, 4),
                new Triangle(-5, 5, 0, 0, 5, 5),
                new Triangle(-5, 5, 0, 0, 5, 5),
                new Rectangle(2, 3),
                new Rectangle(4, 5),
                new Square(5),
                new Square(4.5),
                new Circle(3),
                new Circle(3.5)};

        System.out.println();
        System.out.println("Вывод неотсортированных фигур:");
        System.out.println();

        for (int i = 0; i < shapesArray.length; i++) {
            System.out.printf("Фигура %d: Площадь = %.2f; Периметр = %.2f%n", i + 1, shapesArray[i].getArea(), shapesArray[i].getPerimeter());
        }

        System.out.println();
        System.out.println("Вывод отсортированных фигур по площади:");
        System.out.println();

        AreaComparator areaComparator = new AreaComparator();
        Arrays.sort(shapesArray,areaComparator);

        for (int i = 0; i < shapesArray.length; i++) {
            System.out.printf("Площадь фигуры %d = %.2f%n", i + 1, shapesArray[i].getArea());
        }

        System.out.printf("%nФигура с максимальной площадью %.2f: %s%n",shapesArray[shapesArray.length-1].getArea(), shapesArray[shapesArray.length-1]);

        System.out.println();
        System.out.println("Вывод отсортированных фигур по периметру:");
        System.out.println();

        PerimeterComparator perimeterComparator = new PerimeterComparator();
        Arrays.sort(shapesArray,perimeterComparator);

        for (int i = 0; i < shapesArray.length; i++) {
            System.out.printf("Периметр отсортированной фигуры %d = %.2f%n", i + 1, shapesArray[i].getPerimeter());
        }

        System.out.printf("%nФигура со вторым по величине периметром %.2f: %s%n",shapesArray[shapesArray.length-2].getPerimeter(), shapesArray[shapesArray.length-2]);

        System.out.println();
        System.out.println("Вывод результата переопределенных методов toString():");
        System.out.println();

        for (Shape shape : shapesArray) {
            System.out.println(shape.toString());
        }

        System.out.println();
        System.out.println("Вывод результата переопределенных методов equals():");
        System.out.println();

        for (int i = 1; i < shapesArray.length; i++) {
            System.out.printf("Результат сравнения %s и %s - %b%n",
                    shapesArray[i - 1].toString(), shapesArray[i].toString(), shapesArray[i - 1].equals(shapesArray[i]));
        }

        System.out.println();
        System.out.println("Вывод результата переопределенных методов equals():");
        System.out.println();

        for (Shape shape : shapesArray) {
            System.out.println(shape.hashCode());
        }
    }
}