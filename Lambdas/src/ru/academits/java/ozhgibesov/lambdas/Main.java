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
                .collect(Collectors.joining(", ", "Names: ", "."));

        System.out.println(uniqueNamesString);

        OptionalDouble teensAverageAge = personsList.stream()
                .filter(x -> x.getAge() < 18)
                .mapToInt(Person::getAge)
                .average();

        if (teensAverageAge.isPresent()) {
            System.out.println("The average age of people 18 and less year old is " + teensAverageAge.getAsDouble());
        } else {
            System.out.println("There are no people younger than 18");
        }

        Map<String, Double> personsMap = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Groups of people by name and their average age: ");

        for (Map.Entry<String, Double> n : personsMap.entrySet()) {
            System.out.println(n.getKey() + " " + n.getValue());
        }

        List<String> chosenAgePeopleNameList = personsList.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("The list of people 20-45 year old in descendant order:");

        for (String e : chosenAgePeopleNameList) {
            System.out.println(e);
        }
    }
}