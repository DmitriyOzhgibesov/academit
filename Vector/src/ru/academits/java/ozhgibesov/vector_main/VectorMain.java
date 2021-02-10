package ru.academits.java.ozhgibesov.vector_main;

import ru.academits.java.ozhgibesov.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        Vector vector1 = new Vector(4);
        System.out.printf("Результат создания вектора Vector(5): %s%n", vector1);

        Vector vector2 = new Vector(vector1);
        System.out.printf("Результат создания вектора Vector(vector1): %s%n", vector2);

        Vector vector3 = new Vector(new double[]{5, 4, 3, 2, 1});
        System.out.printf("Результат создания вектора Vector(new double[]{5, 4, 3, 2, 1}): %s%n", vector3);

        Vector vector4 = new Vector(5, new double[]{1, 2, 3});
        System.out.printf("Результат создания вектора Vector(4,new double[]{1,2,3}): %s%n", vector4);

        System.out.printf("Проверка метода vector3.getSize(): %s%n", vector3.getSize());

        Vector addition = vector4.getVectorAddition(vector3);
        System.out.printf("Прибавление vector3 к vector4: %s%n", addition);

        Vector difference = vector2.getVectorDifference(vector3);
        System.out.printf("Вычитание vector3 из vector2: %s%n", difference);

        Vector scalarMultiplication = vector4.getScalarMultiplication(2);
        System.out.printf("Умножение vector4 на 2: %s%n", vector4);
        System.out.printf("Умножение vector4 на 2: %s%n", scalarMultiplication);

        Vector reverse = vector3.getReverse();
        System.out.printf("Разворот vector3: %s%n", reverse);

        System.out.printf("Длина vector4 составляет: %s%n", vector4.getVectorLength());

        vector4.setComponent(4, 5.5);
        System.out.printf("Результат установки элемента 4 в vector4 : %s%n", vector4);

        System.out.printf("Элемент по индексу 0 в vector4: %s%n", vector4.getComponent(0));

        Vector vector5 = new Vector(new double[]{10, 20, 30, 40, 50});
        Vector vector6 = new Vector(new double[]{10, 20, 30, 40, 50});
        System.out.printf("vector5 соответствует vector6 - %b%n", vector5.equals(vector6));
        System.out.printf("vector5HashCode = %d;%n", vector5.hashCode());
        System.out.printf("vector5HashCode = %d;%n", vector6.hashCode());

        System.out.printf("vector5 соответствует vector3 - %b%n", vector5.equals(vector3));
        System.out.printf("vector5HashCode = %d;%n", vector5.hashCode());
        System.out.printf("vector3HashCode = %d;%n", vector3.hashCode());

        System.out.println("Результат сложения vector3 + vector5 через статическую функцию = " + Vector.Sum(vector3, vector5));
        System.out.println("Результат вычитения vector1 - vector3 через статическую функцию = " + Vector.Difference(vector1, vector3));
        System.out.println("Результат скалярного произведения vector1 * vector3 через статическую функцию = " + Vector.multiplicate(vector1, vector3));  }
}