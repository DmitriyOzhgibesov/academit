package ru.academits.java.ozhgibesov.temperature.model;

public class KelvinScale implements Scale {
    @Override
    public String getName() {
        return "Кельвин";
    }

    @Override
    public double convertToCelsius(double value) {
        return value - 273.15;
    }

    @Override
    public double convertFromCelsius(double value) {
        return value + 273.15;
    }
}