package com.example.ChakriHub.config.chat;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.cloudinary.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/websocket")
@Component
public class RawWebSocketHandler {


    private static final Map<Integer, Session> userSessions = new ConcurrentHashMap<>();
    private static final Map<Session, Integer> sessionToUser = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(" JavaFX client connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            JSONObject json = new JSONObject(message);
            int senderId = json.getInt("senderId");
            int receiverId = json.getInt("receiverId");
            String content = json.getString("content");

            userSessions.put(senderId, session);
            sessionToUser.put(session, senderId);

            JSONObject response = new JSONObject()
                    .put("senderId", senderId)
                    .put("receiverId", receiverId)
                    .put("content", content);

            Session receiverSession = userSessions.get(receiverId);
            if (receiverSession != null && receiverSession.isOpen()) {
                receiverSession.getBasicRemote().sendText(response.toString());
            }


            if (session.isOpen()) {
                session.getBasicRemote().sendText(response.toString());
            }

        } catch (Exception e) {
            System.out.println(" Error parsing message: " + e.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session) {
        Integer uid = sessionToUser.remove(session);
        if (uid != null) {
            userSessions.remove(uid);
            System.out.println(" Client disconnected: userId=" + uid);
        }
    }

    @OnError
    public void onError(Session session, Throwable err) {
        System.out.println("️ WebSocket error: " + err.getMessage());
    }
}

