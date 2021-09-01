package ru.academits.java.ozhgibesov.lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(
                new Person("Антон", 23),
                new Person("Евгений", 26),
                new Person("Александра", 24),
                new Person("Иван", 28),
                new Person("Маргарита", 30),
                new Person("Илья", 32),
                new Person("Павел", 32),
                new Person("Дмитрий", 15),
                new Person("Виталий", 17),
                new Person("Наталья", 47),
                new Person("Арина", 60),
                new Person("Анна", 12),
                new Person("Денис", 13)
        );

        String uniqueNamesString = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesString);

        OptionalDouble teensAverageAge = personsList.stream()
                .filter(x -> x.getAge() < 18)
                .mapToInt(Person::getAge)
                .average();

        if (teensAverageAge.isPresent()) {
            System.out.println("Средний возраст людей до 18 лет составляет " + teensAverageAge.getAsDouble());
        } else {
            System.out.println("В списке нет людей моложе 18 лет");
        }

        Map<String, Double> personsMap = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Группы людей по имени и их средний возраст: ");

        for (Map.Entry<String, Double> n : personsMap.entrySet()) {
            System.out.println(n.getKey() + " " + n.getValue());
        }

        List<String> chosenAgePeopleNameList = personsList.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("Список людей 20-45 лет в порядке убывания:");

        for (String e : chosenAgePeopleNameList) {
            System.out.println(e);
        }
    }
}