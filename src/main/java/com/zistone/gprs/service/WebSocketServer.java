package com.zistone.gprs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

//将WebSocket服务端运行在ws://[ServerIP]:[ServerPort]/项目名/WebSocket的访问节点
@ServerEndpoint(value = "/WebSocket")
@Component
public class WebSocketServer
{
    public static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    private Logger _logger = LoggerFactory.getLogger(WebSocketServer.class);
    private Session _session;
    //当前在线连接数
    private static int _connectCount = 0;

    public static synchronized int getConnectCount()
    {
        return _connectCount;
    }

    public static synchronized void addConnectCount()
    {
        _connectCount++;
    }

    public static synchronized void subConnectCount()
    {
        _connectCount--;
    }

    /**
     * 连接建立成功
     *
     * @param session
     */
    @OnOpen
    public void Open(Session session)
    {
        _session = session;
        webSocketSet.add(this);
        addConnectCount();
        _logger.info(String.format("有新连接加入,当前在线人数为:%s", getConnectCount()));
        try
        {
            SendMessage("连接成功...!!!");
        }
        catch (IOException e)
        {
            _logger.error("WebSocket IO异常");
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void Close()
    {
        webSocketSet.remove(this);
        subConnectCount();
        _logger.info(String.format("有一个连接关闭,当前在线人数:", getConnectCount()));
    }

    /**
     * 收到客户端的消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void OnMessage(String message, Session session) throws IOException
    {
        _logger.info(String.format("收到来自客户端的信息:%s", message));
        //群发信息
        for (WebSocketServer item : webSocketSet)
        {
            item.SendMessage(message);
        }
    }

    /**
     * 发生错误
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void OnError(Session session, Throwable throwable)
    {
        _logger.info(String.format("%s连接时发生错误!", session.getId()));
        throwable.printStackTrace();
    }

    public void SendMessage(String message) throws IOException
    {
        _session.getBasicRemote().sendText(message);
    }

}
