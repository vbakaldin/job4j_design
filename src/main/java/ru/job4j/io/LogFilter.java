package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> result;
        try {
            result = Files.readAllLines(Path.of(file)).stream()
                    .filter(line -> {
                        String[] values = line.split(" ");
                        return "404".equals(values[values.length - 2]);
                    })
                    .toList();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter writer = new PrintWriter(out)) {
            data.forEach(writer::println);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
