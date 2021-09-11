package ru.academits.java.ozhgibesov.array_list_main;

import ru.academits.java.ozhgibesov.array_list.NewArrayList;

import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        NewArrayList<String> list = new NewArrayList<>(Arrays.asList("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одинадцать"));

        System.out.println("Размер списка " + list.size());
        System.out.println("Спусок пуст? " + list.isEmpty());
        System.out.println("Список содержит \"пять\"? " + list.contains("пять"));
        System.out.println("Список содержит \"три\"? " + list.contains("три"));

        list.remove("пять");

        System.out.println(list);

        Collection<String> collection1 = Arrays.asList("три", "семь", "двенадцать");
        Collection<String> collection2 = Arrays.asList("три", "семь", "шесть");

        System.out.println("Содержит ли список все элементы первого списка? " + list.containsAll(collection1));
        System.out.println("Содержит ли список все элементы второго списка? " + list.containsAll(collection2));

        list.removeAll(collection2);

        System.out.println("Содержит ли список все элементы второго списка? " + list.containsAll(collection2));

        list.addAll(collection2);

        System.out.println("Содержит ли список все элементы второго списка? " + list.containsAll(collection2));

        list.retainAll(collection2);
        System.out.println("Список содержит \"один\" " + list.contains("один"));

        System.out.println("Содержит ли список все элементы второго списка? " + list.containsAll(collection2));
    }
}