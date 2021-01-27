package ru.academits.java.ozhgibesov.range;

import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите начальную границу диапазона 1: ");
        double startRange1 = scanner.nextDouble();

        System.out.print("Введите конечную границу диапазона 1: ");
        double endRange1 = scanner.nextDouble();

        System.out.print("Введите число для проверки входимости в диапазон 1: ");
        double number = scanner.nextDouble();

        Range range1 = new Range(startRange1, endRange1);

        System.out.println("Длина диапазона составляет " + range1.getLength());

        if (range1.isInside(number)) {
            System.out.printf("Число %.2f входит в диапазон между %.2f и %.2f\n", number, startRange1, endRange1);
        } else {
            System.out.printf("Число %.2f не входит в диапазон между %.2f и %.2f\n", number, startRange1, endRange1);
        }

        System.out.print("Введите начальную границу диапазона 2: ");
        double startRange2 = scanner.nextDouble();

        System.out.print("Введите конечную границу диапазона 2: ");
        double endRange2 = scanner.nextDouble();

        Range range2 = new Range(startRange2, endRange2);

        Range[] unitedRange;
        unitedRange = range1.getUnion(range2);

        System.out.printf("Диапазон 1 = [%.2f;%.2f]\n", range1.getFrom(), range1.getTo());
        System.out.printf("Диапазон 2 = [%.2f;%.2f]\n", range2.getFrom(), range2.getTo());

        StringBuilder unionResult = new StringBuilder("Результат объединения =");
        for (Range range : unitedRange) {
            if (range != null) {
                unionResult.append(String.format(" [%.2f;%.2f]", range.getFrom(), range.getTo()));
            } else {
                unionResult.append(" [null]");
            }
        }

        System.out.println(unionResult);

        Range intersectionRange = range1.getIntersection(range2);
        if (intersectionRange != null) {
            System.out.printf("Результат пересечения: [%.2f;%.2f]\n", intersectionRange.getFrom(), intersectionRange.getTo());
        } else {
            System.out.println("Пересечение отсутствует");
        }

        Range[] subtractedRange;
        subtractedRange = range1.getSubtraction(range2);

        StringBuilder subtractedResult = new StringBuilder("Результат вычитания =");
        for (Range range : subtractedRange) {
            if (range != null) {
                subtractedResult.append(String.format(" [%.2f;%.2f]", range.getFrom(), range.getTo()));
            } else {
                subtractedResult.append(" [null]");
            }
        }

        System.out.println(subtractedResult);
    }
}