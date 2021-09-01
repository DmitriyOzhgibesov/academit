package ru.academits.java.ozhgibesov.main;

import  ru.academits.java.ozhgibesov.tree.Tree;

import java.util.Arrays;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(Arrays.asList(8, 3, 10, 1, 6, 14, 4, 7, 13));

        System.out.println("Дерево содержит " + tree.getCount() + " элементов");

        System.out.println("Обход в глубину:");
        tree.walkInDepth(System.out::println);

        System.out.println("Обход в ширину:");
        tree.walkInWidth(System.out::println);

        System.out.println("Присутствует ли в дереве элемент 21? " + tree.findNode(21));

        System.out.println("Удление эжлемента 3 и обход в глубину:");
        tree.remove(3);
        tree.walkInDepth(System.out::println);

        System.out.println("Рекурсивный обход в глубину:");
        tree.walkTreeInDepthRecursively(System.out::println);

        Tree<Integer> tree2 = new Tree<>(Arrays.asList(18, 10, 4, 14, 12, 16, 13));

        System.out.println("Рекурсивный обход нового дерева в глубину:");
        tree2.walkTreeInDepthRecursively(System.out::println);

        System.out.println("Обход нового дерева после удаления элемента 10:");
        tree2.remove(10);
        tree2.walkInDepth(System.out::println);

        System.out.println("Удаление элемента 8 из первого дерева с последующим обходом:");
        tree.remove(8);
        tree.walkInDepth(System.out::println);
    }
}