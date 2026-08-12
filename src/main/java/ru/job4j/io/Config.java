package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    int separator = line.indexOf('=');
                    if (separator <= 0 || separator == line.length() - 1) {
                        throw new IllegalArgumentException("Invalid line: " + line);
                    }
                    String key = line.substring(0, separator).trim();
                    String value = line.substring(separator + 1).trim();
                    if (key.isEmpty() || value.isEmpty()) {
                        throw new IllegalArgumentException("Invalid line: " + line);
                    }
                    values.put(key, value);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String value(String key) {
        // throw new UnsupportedOperationException("Don't impl this method yet!");
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}