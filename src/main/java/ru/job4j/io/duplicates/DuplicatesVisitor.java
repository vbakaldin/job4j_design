package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty property = new FileProperty(
                attributes.size(), file.getFileName().toString()
        );
        files.computeIfAbsent(property, key -> new ArrayList<>()).add(file);
        return super.visitFile(file, attributes);
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : files.entrySet()) {
            List<Path> paths = entry.getValue();
            if (paths.size() > 1) {
                FileProperty property = entry.getKey();
                System.out.printf("%s - %d%n", property.getName(), property.getSize());
                for (Path path : paths) {
                    System.out.printf("    %s%n", path.toAbsolutePath());
                }
            }
        }
    }
}