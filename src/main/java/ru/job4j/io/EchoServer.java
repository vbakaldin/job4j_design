package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    String request = input.readLine();
                    boolean exit = request != null && request.contains("msg=Exit");

                    if (request != null && request.contains("msg=Hello")) {
                        output.write("Hello".getBytes());
                    } else if (request != null && request.contains("msg=Any")) {
                        output.write("What".getBytes());
                    }

                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();

                    if (exit) {
                        server.close();
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Server error", e);
        }
    }
}