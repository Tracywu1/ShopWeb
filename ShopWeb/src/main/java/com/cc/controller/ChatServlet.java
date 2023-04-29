package com.cc.controller;

import com.cc.utils.CustomThreadFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChatServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final long KEEP_ALIVE_TIMEOUT = 30000; // 30 s
    private final Map<String, AsyncContext> users = new HashMap<>();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("login".equals(action)) {
            handleLogin(req, resp);
        } else if ("logout".equals(action)) {
            handleLogout(req, resp);
        } else if ("send".equals(action)) {
            handleSend(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String userId = req.getSession().getId(); // use session ID as user ID

        synchronized (users) {
            if (users.containsKey(userId)) {
                resp.sendError(HttpServletResponse.SC_CONFLICT, "User already logged in");
                return;
            }
            users.put(userId, null); // register user
        }

        resp.getWriter().write(userId); // send user ID back to client
        resp.getWriter().flush();

        System.out.println("User " + username + " logged in, ID: " + userId);
    }

    private void handleLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getSession().getId();
        if (userId == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not logged in");
            return;
        }

        AsyncContext userContext;
        synchronized (users) {
            userContext = users.remove(userId); // unregister user
        }

        if (userContext != null) {
            userContext.complete(); // complete pending request
        }

        req.getSession().invalidate(); // invalidate session
        resp.getWriter().write("OK");
        resp.getWriter().flush();

        System.out.println("User " + userId + " logged out");
    }

    private void handleSend(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getSession().getId();
        if (userId == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not logged in");
            return;
        }

        String recipientId = req.getParameter("recipientId");
        String message = req.getParameter("message");

        AsyncContext recipientContext;
        synchronized (users) {
            recipientContext = users.get(recipientId); // get recipient's context
        }

        if (recipientContext != null) {
            HttpServletResponse recipientResponse = (HttpServletResponse) recipientContext.getResponse();
            recipientResponse.getWriter().write(message); // send message to recipient
            recipientResponse.getWriter().flush();
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Recipient not found");
            return;
        }

        resp.getWriter().write("OK");
        resp.getWriter().flush();

        System.out.println("User " + userId + " sent message to " + recipientId + ": " + message);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getSession().getId();
        if (userId == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未登录");
            return;
        }
        AsyncContext userContext;
        synchronized (users) {
            userContext = users.remove(userId); // unregister user
        }

        if (userContext != null) {
            userContext.complete(); // complete pending request
        }

        req.getSession().invalidate(); // invalidate session
        resp.getWriter().write("OK");
        resp.getWriter().flush();

        System.out.println("User " + userId + " logged out");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // Create custom thread pool and thread factory
        int numThreads = Runtime.getRuntime().availableProcessors() * 2;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(numThreads, numThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new CustomThreadFactory("chat-servlet"));

        // Start keep-alive thread using the custom thread pool
        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(KEEP_ALIVE_TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (users) {
                    for (AsyncContext context : users.values()) {
                        if (context != null) {
                            HttpServletResponse response = (HttpServletResponse) context.getResponse();
                            try {
                                response.getWriter().write("KEEP_ALIVE");
                                response.getWriter().flush();
                            } catch (IOException e) {
                                // ignore
                            }
                        }
                    }
                }
            }
        });
    }

}