package ru.academits.java.ozhgibesov.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSV {
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter(
                    "E:/MyPrograms/ITAcadem/CourseJavaOOP/Homework/CSV/CSVhtml.html");
            Scanner scanner = new Scanner(new FileInputStream(
                    "E:/MyPrograms/ITAcadem/CourseJavaOOP/Homework/CSV/CSVInput.txt"));

            writer.println("<table border=\"1\"");
            writer.print("<tr><td>");

            boolean isPlainTextInsideTableDetail = false;
            boolean isNewLine = false;

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (isNewLine) {
                    writer.print("<br/><tr><td>");
                }

                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    char quote = '"';
                    char comma = ',';

                    if (c == quote && s.charAt(i - 1) == comma) {
                        isPlainTextInsideTableDetail = true;
                        continue;
                    } else if (c == comma && s.charAt(i - 1) == quote) {
                        isPlainTextInsideTableDetail = false;
                    } else if (c == quote && isPlainTextInsideTableDetail) {
                        isPlainTextInsideTableDetail = false;
                        continue;
                    } else if (c == quote && s.charAt(i - 1) == quote) {
                        isPlainTextInsideTableDetail = true;
                    }

                    if (c == '<') {
                        writer.print("&lt");
                    } else if (c == '>') {
                        writer.print("&gt");
                    } else if (c == '&') {
                        writer.print("&amp");
                    } else if (c == comma && s.charAt(i - 1) == quote
                            && s.charAt(i - 2) == quote
                            && s.charAt(i - 3) != quote) {
                        writer.print(c);
                    } else if (c == comma) {
                        writer.print("</td> <td>");
                    } else {
                        writer.print(c);
                    }
                }

                if (isPlainTextInsideTableDetail) {
                    writer.print("<br/>");
                } else {
                    writer.print("</td></tr>");
                    isNewLine = true;
                }
            }
            writer.println("</table>");
            writer.close();

        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}