package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            String start = null;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String status = data[0];
                String time = data[1];
                if (start == null && ("400".equals(status) || "500".equals(status))) {
                    start = time;
                } else if (start != null && ("200".equals(status) || "300".equals(status))) {
                    writer.println(start + ";" + time + ";");
                    start = null;
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}