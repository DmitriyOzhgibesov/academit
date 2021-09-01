package ru.academits.java.ozhgibesov.lambdas;

public class Person {
    private final String name;
    private final int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person(String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть < 0. Указанный возраст: " + age);
        }

        this.name = name;
        this.age = age;
    }
}
