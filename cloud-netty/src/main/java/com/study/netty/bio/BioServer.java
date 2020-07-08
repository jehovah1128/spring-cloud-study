package com.study.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("socket服务启动了.....");
        while (true) {
            final Socket socket = serverSocket.accept();
            System.out.println("new socket client port :" + socket.getPort());
            executorService.execute(new Runnable() {
                public void run() {
                    socketHandler(socket);
                }
            });

        }
    }

    public static void socketHandler(Socket client) {
        try {
            System.out.println("socket " + client.getPort());
            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();
            while (true) {
                int available = inputStream.available();
                if (available > 0) {
                    byte[] bytes = new byte[available];
                    int read = inputStream.read(bytes);
                    String message = new String(bytes);
                    System.out.println("client" + client.getPort() + " send :" + message);
                    message = "Received message :" + message;
                    outputStream.write(message.getBytes());
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
