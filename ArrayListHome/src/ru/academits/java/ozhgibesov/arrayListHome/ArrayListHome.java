package ru.academits.java.ozhgibesov.arrayListHome;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream(args[0]))) {
            ArrayList<String> strings = new ArrayList<>();

            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }

            System.out.println("Решение 1й части задачи ArrayListHome: " + strings);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                --i;
            }
        }

        System.out.println("Решение 2й части задачи ArrayListHome: " + numbers);

        ArrayList<Integer> inputNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 2, 6, 1, 7, 5, 8, 9, 2, 3, 10, 2, 9));
        ArrayList<Integer> outputNumbers = new ArrayList<>();

        for (Integer inputNumber : inputNumbers) {
            if (!outputNumbers.contains(inputNumber)) {
                outputNumbers.add(inputNumber);
            }
        }

        System.out.println("Решение 3й части задачи ArrayListHome: " + outputNumbers);
    }
}