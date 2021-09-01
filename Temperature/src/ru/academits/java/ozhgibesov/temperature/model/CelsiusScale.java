package ru.academits.java.ozhgibesov.temperature.model;

public class CelsiusScale implements Scale {
    @Override
    public String getName() {
        return "Цельсий";
    }

    @Override
    public double convertToCelsius(double value) {
        return value;
    }

    @Override
    public double convertFromCelsius(double value) {
        return value;
    }
}