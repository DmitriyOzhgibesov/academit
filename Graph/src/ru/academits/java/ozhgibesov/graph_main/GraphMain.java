package ru.academits.java.ozhgibesov.graph_main;

import ru.academits.java.ozhgibesov.graph.Graph;

public class GraphMain {
    public static void main(String[] args) {
        int[][] connections = {
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0, 0, 1},
                {0, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1, 0},
        };

        Graph graph = new Graph(connections);

        System.out.println("Обход графа в ширину:");
        graph.walkInWidth(System.out::println);

        System.out.println("Обход графа по глубине:");
        graph.walkInDepth(System.out::println);

        System.out.println("Рекурсивный обход графа в глубину:");
        graph.walkInDepthRecursively(System.out::println);
    }
}