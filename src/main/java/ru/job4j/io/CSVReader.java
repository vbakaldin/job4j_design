package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        String[] filters = argsName.get("filter").split(",");
        PrintStream output = System.out;
        if (!"stdout".equals(argsName.get("out"))) {
            output = new PrintStream(new FileOutputStream(argsName.get("out")));
        }
        try (Scanner scanner = new Scanner(
            new FileInputStream(argsName.get("path")))) {
            String[] columns = scanner.nextLine().split(
                    Pattern.quote(delimiter), -1
            );
            List<Integer> indexes = new ArrayList<>();
            for (String filter : filters) {
                int index = -1;
                for (int position = 0; position < columns.length; position++) {
                    if (filter.equals(columns[position])) {
                        index = position;
                        break;
                    }
                }
                indexes.add(index);
            }
            output.println(String.join(delimiter, filters));
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(
                        Pattern.quote(delimiter), -1
                );
                StringJoiner result = new StringJoiner(delimiter);
                for (Integer index : indexes) {
                    result.add(values[index]);
                }
                output.println(result);
            }
        } finally {
            if (output != System.out) {
                output.close();
            }
        }
    }

    private static void validate(ArgsName argsName) {
        File source = new File(argsName.get("path"));
        argsName.get("delimiter");
        argsName.get("out");
        argsName.get("filter");
        if (!source.exists()) {
            throw new IllegalArgumentException(
                    String.format("File does not exist: %s", source)
            );
        }
        if (!source.isFile()) {
            throw new IllegalArgumentException(
                    String.format("This path is not a file: %s", source)
            );
        }
    }

    public static void main(String[] args) throws Exception {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}