package ru.academits.java.ozhgibesov.range_main;

import ru.academits.java.ozhgibesov.range.Range;

import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите начальную границу диапазона 1: ");
        double range1From = scanner.nextDouble();

        System.out.print("Введите конечную границу диапазона 1: ");
        double range1To = scanner.nextDouble();

        System.out.print("Введите число для проверки входимости в диапазон 1: ");
        double number = scanner.nextDouble();

        Range range1 = new Range(range1From, range1To);

        System.out.println("Длина диапазона составляет " + range1.getLength());

        if (range1.isInside(number)) {
            System.out.printf("Число %.2f входит в диапазон между %.2f и %.2f%n", number, range1From, range1To);
        } else {
            System.out.printf("Число %.2f не входит в диапазон между %.2f и %.2f%n", number, range1From, range1To);
        }

        System.out.print("Введите начальную границу диапазона 2: ");
        double range2From = scanner.nextDouble();

        System.out.print("Введите конечную границу диапазона 2: ");
        double range2To = scanner.nextDouble();

        Range range2 = new Range(range2From, range2To);

        System.out.printf("Диапазон 1 = %s%n", range1);
        System.out.printf("Диапазон 2 = %s%n", range2);

        Range[] union = range1.getUnion(range2);
        System.out.println("Результат объединения: " + getRangesArrayString(union));

        Range intersection = range1.getIntersection(range2);
        if (intersection != null) {
            System.out.println("Результат пересечения: " + intersection);
        } else {
            System.out.println("Результат пересечения: []");
        }


        Range[] difference = range1.getDifference(range2);
        System.out.println("Результат разности: " + getRangesArrayString(difference));
    }

    public static String getRangesArrayString(Range[] rangesArray) {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < rangesArray.length; i++) {
            stringBuilder.append(rangesArray[i]);

            if (i < rangesArray.length - 1) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.append("]").toString();
    }
}