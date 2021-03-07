package ru.academits.java.ozhgibesov.arrayListHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        printFileStrings("E:\\MyPrograms\\ITAcadem\\CourseJavaOOP\\Homework\\ArrayListHome\\strings.txt");

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        removeEvenNumbers(numbers);
        System.out.println("Решение 2й части задачи ArrayListHome: " + numbers);

        ArrayList<Integer> inputIntegerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 2, 6, 1, 7, 5, 8, 9, 2, 3, 10, 2, 9));
        ArrayList<Integer> outputIntegerList = removeDuplicateNumbers(inputIntegerList);
        System.out.println("Решение 3й части задачи ArrayListHome: " + outputIntegerList);
    }

    public static void printFileStrings(String path) {
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            ArrayList<String> strings = new ArrayList<>();

            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }

            System.out.println("Решение 1й части задачи ArrayListHome: " + strings);
        } catch (FileNotFoundException e) {
            System.out.println("Файл с исходныи данными не обнаружен: " + e.getMessage());
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> list){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                --i;
            }
        }
    }

    public static ArrayList<Integer> removeDuplicateNumbers(ArrayList<Integer> list){
        ArrayList<Integer> outputList = new ArrayList<>(5);

        for (Integer number : list) {
            if (!outputList.contains(number)) {
                outputList.add(number);
            }
        }
        return outputList;
    }
}