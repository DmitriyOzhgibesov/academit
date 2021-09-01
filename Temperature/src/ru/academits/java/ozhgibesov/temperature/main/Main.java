package ru.academits.java.ozhgibesov.temperature.main;

import ru.academits.java.ozhgibesov.temperature.model.*;
import ru.academits.java.ozhgibesov.temperature.view.MainWindow;
import ru.academits.java.ozhgibesov.temperature.view.MyMainWindow;

public class Main {
    public static void main(String[] args) {
        ScaleConverterModel model = new MyScaleConverterModel(
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()
        );

        MainWindow mainWindow = new MyMainWindow(model);

        mainWindow.run();
    }
}