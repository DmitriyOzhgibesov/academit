package ru.academits.java.ozhgibesov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Csv {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Программа выполняет преобразование файла в формате CSV в файл формата HTML");
            System.out.println("CSV ИСТОЧНИК \\A ИСТОЧНИК \\B");
            System.out.println("A файл-источник для входных данных");
            System.out.println("B файл для записи результата работы программы");
            return;
        }

        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"utf-8\">");
            writer.println("        <title>Перевод CSV в HTML</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <table border=\"1\">");
            writer.print("              <tr><td>");

            boolean isPlainTextInsideTableDetail = false;
            boolean isNewLine = false;

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();

                if (s.length() == 0) {
                    continue;
                }

                if (isNewLine && !isPlainTextInsideTableDetail) {
                    writer.println("");
                    writer.print("              <tr><td>");
                }

                char quote = '"';
                char comma = ',';
                int i = 0;

                if (s.charAt(0) == quote) {
                    isPlainTextInsideTableDetail = true;
                    i = 1;
                }

                for (; i < s.length(); i++) {
                    char c = s.charAt(i);

                    if (i == 0 && s.charAt(0) == comma) {
                        writer.print("</td> <td>");
                        isPlainTextInsideTableDetail = false;
                        continue;
                    } else if (c == quote && s.charAt(i - 1) == comma) {
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
                        writer.print("&lt;");
                    } else if (c == '>') {
                        writer.print("&gt;");
                    } else if (c == '&') {
                        writer.print("&amp;");
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

            writer.println("");
            writer.println("        </table>");
            writer.println("    </body>");
            writer.println("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл для чтения не найден. Проверьте путь к файлу и его расширение");
            System.out.println("Также для корректной работы программы необходимо задать два аргумента:");
            System.out.println("Первый аргумент должен содержать путь к файлу для чтения с указанием его имени и расширения в формате \\fileName.txt");
            System.out.println("Второй аргумент должен содержать путь к файлу для записи с указанием его имени и расширения в формате \\fileName.html");
        }
    }
}