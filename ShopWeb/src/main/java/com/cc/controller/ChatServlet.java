package com.cc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/chat", asyncSupported = true)
public class ChatServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final long KEEP_ALIVE_TIMEOUT = 30000; // 30 s
    private final Map<String, AsyncContext> users = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("login".equals(action)) {
            handleLogin(req, resp);
        } else if ("send".equals(action)) {
            handleSend(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
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
            AsyncContext asyncContext = req.startAsync(req, resp);
            asyncContext.setTimeout(KEEP_ALIVE_TIMEOUT);
            users.put(userId, asyncContext); // register user
        }

        resp.getWriter().write(userId); // send user ID back to client
        resp.getWriter().flush();

        System.out.println("User " + username + " logged in, ID: " + userId);
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
}
