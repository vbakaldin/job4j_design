package ru.job4j.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Random random = new Random();
        boolean stopped = false;
        boolean run = true;
        while (run) {
            String message = scanner.nextLine();
            log.add(message);
            if (OUT.equals(message)) {
                run = false;
            } else if (STOP.equals(message)) {
                stopped = true;
            } else if (CONTINUE.equals(message)) {
                stopped = false;
            } else if (!stopped) {
                String answer = phrases.get(random.nextInt(phrases.size()));
                System.out.println(answer);
                log.add(answer);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        try {
            return Files.readAllLines(Path.of(botAnswers));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void saveLog(List<String> log) {
        try {
            Files.write(Path.of(path), log);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/chat.log", "data/answers.txt");
        consoleChat.run();
    }
}