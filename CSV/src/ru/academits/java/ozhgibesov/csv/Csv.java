package ru.academits.java.ozhgibesov.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Csv {
    public static void main(String[] args) {
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
                if (s.charAt(0) == comma) {
                    isPlainTextInsideTableDetail = false;
                    i = 1;
                }

                for (; i < s.length(); i++) {
                    char c = s.charAt(i);

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
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}