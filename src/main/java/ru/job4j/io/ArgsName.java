package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        /* TODO add the necessary checks. */
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(
                    String.format("This key: '%s' is missing", key)
            );
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        /* TODO parse args to values. */
        for (String argument : args) {
            if (!argument.startsWith("-")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not start with a '-' character", argument
                ));
            }
            int separator = argument.indexOf('=');
            if (separator == -1) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain an equal sign", argument
                ));
            }
            if (separator == 1) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a key", argument
                ));
            }
            if (separator == argument.length() - 1) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a value", argument
                ));
            }
            String key = argument.substring(1, separator);
            String value = argument.substring(separator + 1);
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        /* TODO add the necessary checks. */
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}