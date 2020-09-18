package com.zistone.gprs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器主动向客户端发送消息
 */
@ServerEndpoint(value = "/WebSocket")
@Component
public class WebSocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    //线程安全的Set，用来存放每个客户端对应的WebSocket对象
    public static volatile Set<WebSocketServer> _webSocketSet = ConcurrentHashMap.newKeySet();

    private Session _session;

    /**
     * 主动推送消息
     *
     * @param message
     * @throws IOException
     */
    public static void InitiativeSendMessage(String message) throws IOException {
        //群发信息
        for (WebSocketServer item : _webSocketSet) {
            item.SendMessage(message);
        }
    }

    public void SendMessage(String message) throws IOException {
        LOGGER.info("WebSocket服务端发送：" + message);
        _session.getBasicRemote().sendText(message);
    }

    /**
     * 连接建立成功
     *
     * @param session
     */
    @OnOpen
    public void Open(Session session) {
        _session = session;
        _webSocketSet.add(this);
        LOGGER.info(String.format("有新连接加入，当前在线人数为:%s", _webSocketSet.size()));
        try {
            SendMessage("连接成功...!!!");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void Close() {
        _webSocketSet.remove(this);
        LOGGER.info(String.format("有一个连接关闭,当前在线人数:%s", _webSocketSet.size()));
    }

    /**
     * 收到客户端的消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void OnMessage(String message, Session session) throws IOException {
        LOGGER.info(String.format("收到来自客户端的信息:%s", message));
        //群发信息
        for (WebSocketServer item : _webSocketSet) {
            item.SendMessage("我是WebSocket发送过来的消息");
        }
    }

    /**
     * 发生错误
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void OnError(Session session, Throwable throwable) {
        LOGGER.info(String.format("[%s]连接时发生错误!", session.getId()));
        throwable.printStackTrace();
    }

}
