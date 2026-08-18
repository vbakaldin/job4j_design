package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                String name = source.toString()
                        .replace(File.separatorChar, '/');
                zip.putNextEntry(new ZipEntry(name));
                Files.copy(source, zip);
                zip.closeEntry();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName names) {
        Path directory = Path.of(names.get("d"));
        String exclude = names.get("e");
        String output = names.get("o");
        if (!Files.exists(directory)) {
            throw new IllegalArgumentException(
                    String.format("Directory does not exist: %s", directory)
            );
        }
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(
                    String.format("This path is not a directory: %s", directory)
            );
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException(
                    String.format("Extension must start with '.': %s", exclude)
            );
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException(
                    String.format("Archive must have the .zip extension: %s", output)
            );
        }
    }

    public static void main(String[] args) throws IOException {
//        Zip zip = new Zip();
//        zip.packSingleFile(
//                new File("./pom.xml"),
//                new File("./pom.zip")
//        );
        ArgsName names = ArgsName.of(args);
        validate(names);
        Path directory = Path.of(names.get("d"));
        String exclude = names.get("e");
        List<Path> sources = Search.search(
                directory,
                path -> !path.toFile().getName().endsWith(exclude)
        );
        new Zip().packFiles(sources, new File(names.get("o")));
    }
}