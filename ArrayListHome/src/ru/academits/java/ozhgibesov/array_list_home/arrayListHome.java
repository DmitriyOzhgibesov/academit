package ru.academits.java.ozhgibesov.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class arrayListHome {
    public static void main(String[] args) {
        ArrayList<String> strings = getFileStrings("E:\\MyPrograms\\ITAcadem\\CourseJavaOOP\\Homework\\ArrayListHome\\strings.txt");
        System.out.println("Решение 1й части задачи ArrayListHome: " + strings);

        ArrayList<Integer> integerNumbers = new ArrayList<>(Arrays.asList(-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        removeEvenNumbers(integerNumbers);
        System.out.println("Решение 2й части задачи ArrayListHome: " + integerNumbers);

        ArrayList<Integer> integersList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 2, 6, 1, 7, 5, 8, 9, 2, 3, 10, 2, 9));
        ArrayList<Integer> integerListWithoutDuplicates = getListWithoutDuplicatesIntegers(integersList);
        System.out.println("Решение 3й части задачи ArrayListHome: " + integerListWithoutDuplicates);
    }

    public static ArrayList<String> getFileStrings(String path) {
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            ArrayList<String> strings = new ArrayList<>();

            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }

            return strings;
        } catch (FileNotFoundException e) {
            System.out.println("Файл с исходныи данными не обнаружен: " + e.getMessage());
        }

        return null;
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                --i;
            }
        }
    }

    public static ArrayList<Integer> getListWithoutDuplicatesIntegers(ArrayList<Integer> list) {
        ArrayList<Integer> outputList = new ArrayList<>(list.size());

        for (Integer number : list) {
            if (!outputList.contains(number)) {
                outputList.add(number);
            }
        }

        return outputList;
    }
}