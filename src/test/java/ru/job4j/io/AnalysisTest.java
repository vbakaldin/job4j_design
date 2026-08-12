package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {

    @Test
    void whenTwoPeriods(@TempDir Path tempDir) throws IOException {
        Path source = tempDir.resolve("server.log");
        Path target = tempDir.resolve("target.csv");
        Files.write(source, List.of(
                "200 10:56:01",
                "500 10:57:01",
                "400 10:58:01",
                "300 10:59:01",
                "500 11:01:02",
                "200 11:02:02"
        ));
        new Analysis().unavailable(source.toString(), target.toString());
        assertThat(target).exists();
        assertThat(Files.readAllLines(target)).containsExactly(
                "10:57:01;10:59:01;",
                "11:01:02;11:02:02;"
        );
    }

    @Test
    void whenZeroPeriods(@TempDir Path tempDir) throws IOException {
        Path source = tempDir.resolve("server.log");
        Path target = tempDir.resolve("target.csv");
        Files.write(source, List.of(
                "200 10:56:01",
                "300 10:57:01",
                "200 10:58:01"
        ));
        new Analysis().unavailable(source.toString(), target.toString());
        assertThat(target).exists();
        assertThat(Files.readAllLines(target)).isEmpty();
    }
}