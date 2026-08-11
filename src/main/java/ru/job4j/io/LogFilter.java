package ru.job4j.io;

import java.io.IOException;
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

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}
