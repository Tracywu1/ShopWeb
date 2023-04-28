package com.cc.service;

public interface ChatService {
    void start(int port);

    void stop();

    void broadcast(String message);
}
