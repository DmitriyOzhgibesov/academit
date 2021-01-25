package ru.academits.java.ozhgibesov.range;

import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите начальную границу диапазона: ");
        double startRange = scanner.nextDouble();

        System.out.print("Введите конечную границу диапазона: ");
        double endRange = scanner.nextDouble();

        System.out.print("Введите число для проверки входимости в диапазон: ");
        double number = scanner.nextDouble();

        Range range1 = new Range(startRange, endRange);

        System.out.println("Длина диапазона составляет " + range1.getLength());

        if (range1.isInside(number)) {
            System.out.printf("Число %.2f входит в диапазон между %.2f и %.2f", number, startRange, endRange);
        } else {
            System.out.printf("Число %.2f не входит в диапазон между %.2f и %.2f", number, startRange, endRange);
        }
    }
}