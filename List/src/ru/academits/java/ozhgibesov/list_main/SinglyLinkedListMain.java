package ru.academits.java.ozhgibesov.list_main;

import ru.academits.java.ozhgibesov.list.SinglyLinkedList;

public class SinglyLinkedListMain {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> integerList1 = new SinglyLinkedList<>();
        System.out.println("Заполнение списка integerList1:");
        integerList1.addFirst(0);

        for (int i = integerList1.getCount() - 1; i < 10; i++) {
            integerList1.addByIndex(i, i);
        }

        for (int i = 0; i < integerList1.getCount(); i++) {
            System.out.printf("Элемент списка №%d = %d%n", i, integerList1.getDataByIndex(i));
        }

        System.out.println("Размер списка = " + integerList1.getCount());

        System.out.println("Первый элемент списка до выполнения removeFirst(): " + integerList1.getFirst());
        integerList1.removeFirst();
        System.out.println("Первый элемент списка после выполнения removeFirst(): " + integerList1.getFirst());

        for (int i = 0; i < integerList1.getCount(); i++) {
            System.out.printf("Элемент списка №%d = %d%n", i, integerList1.getDataByIndex(i));
        }

        System.out.println("Размер списка = " + integerList1.getCount());

        System.out.println("Элемент с индексом 5 = " + integerList1.getDataByIndex(5));
        System.out.println("Удаляем элемент с индексом 5 равным " + integerList1.removeByIndex(5));
        System.out.println("Элемент с индексом 5 после выполнения removeByIndex(5) = " + integerList1.getDataByIndex(5));
        System.out.printf("Элемент списка равный 6 удален - %b%n", integerList1.removeByData(4));

        integerList1.setDataByIndex(100, 3);
        System.out.println("Печать списка после удаления элментов и установки элементу с индексом 3 значения 100:");

        for (int i = 0; i < integerList1.getCount(); i++) {
            System.out.printf("Элемент списка №%d = %d%n", i, integerList1.getDataByIndex(i));
        }

        SinglyLinkedList<Integer> integerList2 = new SinglyLinkedList<>();
        integerList2.addFirst(10);

        for (int i = 11; i <= 20; i++) {
            integerList2.addByIndex(i, i - 10);
        }

        System.out.println("Размер списка integerList2 = " + integerList2.getCount());
        SinglyLinkedList<Integer> integerList2Copy = integerList2.copy();
        integerList2.reverse();

        System.out.println("Печать копии списка integerList2:");

        for (int i = 0; i < integerList2Copy.getCount(); i++) {
            System.out.printf("Элемент списка integerList2Copy №%d = %d%n", i, integerList2Copy.getDataByIndex(i));
        }

        System.out.println("Печать перевернутого списка:");

        for (int i = 0; i < integerList2.getCount(); i++) {
            System.out.printf("Элемент списка integerList2.reverseList №%d = %d%n", i, integerList2.getDataByIndex(i));
        }
    }
}