package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();

        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            int number = Integer.parseInt(line);
            if (number % 2 == 0) {
                System.out.printf("Число %s четное%n", number);
            } else {
                System.out.printf("Число %s нечетное%n", number);
            }
        }
    }
}