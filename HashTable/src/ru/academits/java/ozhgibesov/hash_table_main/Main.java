package ru.academits.java.ozhgibesov.hash_table_main;

import ru.academits.java.ozhgibesov.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>(10);

        for (int i = 0; i < 20; i++) {
            hashTable.add(i);
        }

        System.out.println(hashTable);

        System.out.println("Удалим элементы 17, 19. Результат операции:");
        hashTable.removeAll(Arrays.asList(17, 19));

        System.out.println(hashTable);

        System.out.println("Добавим элементы обратно:");
        hashTable.addAll(Arrays.asList(17, 19));

        System.out.println(hashTable);

        System.out.println("Удалим все, что не принадлежит значениям: 3, 5, 6. Результат операции:");
        hashTable.retainAll(Arrays.asList(3, 5, 6));

        System.out.println(hashTable);

        System.out.println("Преобразуем хештаблицу в массив. Элемент с индексом 0 равен:");

        System.out.println(hashTable.toArray()[0]);
    }
}