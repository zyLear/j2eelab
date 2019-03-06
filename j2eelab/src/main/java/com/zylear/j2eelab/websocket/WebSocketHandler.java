package com.zylear.j2eelab.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiezongyu on 2018/7/5.
 */

public class WebSocketHandler extends TextWebSocketHandler {


    private List<WebSocketSession> webSocketSessions = new LinkedList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("accept:" + message.toString());
        session.sendMessage(new TextMessage("reply msg from server"));
    }


    public void sendMessage(WebSocketSession session, TextMessage message) {
        try {
            session.sendMessage(new TextMessage("text"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
