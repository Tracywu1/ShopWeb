package com.cc.utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ChatServer {
    private ExecutorService threadPool = new ThreadPoolExecutor(
            0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new CustomThreadFactory("my-chat-app"));

    private List<ClientHandler> clients = new ArrayList<>();

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器启动成功，监听端口：" + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端[" + socket.getPort() + "]已连接");
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                threadPool.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message, ClientHandler excludeClient) {
        for (ClientHandler client : clients) {
            if (client != excludeClient) {
                client.send(message);
            }
        }
    }

    public void stop() {
        try {
            Thread.sleep(10000);
            threadPool.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private class ClientHandler implements Runnable {
        private Socket socket;
        private String username;
        private BufferedReader reader;
        private BufferedWriter writer;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write("欢迎进入聊天室，请输入您的用户名：\n");
                writer.flush();

                String input;
                while ((input = reader.readLine()) != null) {
                    if (username == null) {
                        username = input;
                        broadcast("欢迎【" + username + "】进入聊天室！", this);
                    } else {
                        broadcast("【" + username + "】：" + input, this);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    writer.close();
                    socket.close();
                    clients.remove(this);
                    broadcast("【" + username + "】已离开聊天室！", null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void send(String message) {
            try {
                writer.write(message + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
