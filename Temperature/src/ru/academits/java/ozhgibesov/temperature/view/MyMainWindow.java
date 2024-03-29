package ru.academits.java.ozhgibesov.temperature.view;

import ru.academits.java.ozhgibesov.temperature.model.Scale;
import ru.academits.java.ozhgibesov.temperature.model.ScaleConverterModel;

import javax.swing.*;
import java.awt.*;

public class MyMainWindow implements MainWindow {
    private final ScaleConverterModel model;

    public MyMainWindow(ScaleConverterModel model) {
        this.model = model;
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Температура");

            mainFrame.setVisible(true);
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setSize(500, 150);
            mainFrame.setMinimumSize(mainFrame.getSize());
            mainFrame.setLocationRelativeTo(null);

            JPanel inputScalePanel = new JPanel();
            mainFrame.add(inputScalePanel, BorderLayout.PAGE_START);

            JLabel fromLabel = new JLabel("Из:");
            inputScalePanel.add(fromLabel);

            ButtonGroup inputScaleGroup = new ButtonGroup();

            for (Scale scale : model.getScalesList()) {
                JRadioButton scaleButton = new JRadioButton(scale.getName());
                scaleButton.addActionListener(e -> model.setInputScale(scale));

                inputScaleGroup.add(scaleButton);
                inputScalePanel.add(scaleButton);
            }

            JPanel outputScalePanel = new JPanel();
            mainFrame.add(outputScalePanel, BorderLayout.PAGE_END);

            JLabel outputLabel = new JLabel("В:");
            outputScalePanel.add(outputLabel);

            ButtonGroup outputScaleGroup = new ButtonGroup();

            for (Scale scale : model.getScalesList()) {
                JRadioButton scaleButton = new JRadioButton(scale.getName());
                scaleButton.addActionListener(e -> model.setOutScale(scale));

                outputScaleGroup.add(scaleButton);
                outputScalePanel.add(scaleButton);
            }

            JPanel inputTextPanel = new JPanel();
            mainFrame.add(inputTextPanel, BorderLayout.LINE_START);

            JTextField inputText = new JTextField(10);

            inputTextPanel.add(inputText);

            JPanel outputTextPanel = new JPanel();
            mainFrame.add(outputTextPanel, BorderLayout.LINE_END);

            JTextField outputText = new JTextField(10);
            outputText.setEditable(false);

            outputTextPanel.add(outputText);

            JPanel convertPanel = new JPanel();
            mainFrame.add(convertPanel);

            JButton convertButton = new JButton("перевести");
            convertPanel.add(convertButton);

            convertButton.addActionListener(e -> {
                try {
                    double output = model.convert(Double.parseDouble(inputText.getText()));

                    outputText.setText(String.format("%.2f", output));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(mainFrame, "Входные данные должны быть числовыми!");
                } catch (NullPointerException exception) {
                    JOptionPane.showMessageDialog(mainFrame, "Вы должны выбрать масштаб ввода и вывода");
                }
            });
        });
    }
}