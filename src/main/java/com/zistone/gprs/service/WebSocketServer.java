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
    private Logger m_logger = LoggerFactory.getLogger(WebSocketServer.class);
    private Session m_session;
    //当前在线连接数
    private static int m_connectCount = 0;

    public static synchronized int getConnectCount()
    {
        return m_connectCount;
    }

    public static synchronized void addConnectCount()
    {
        m_connectCount++;
    }

    public static synchronized void subConnectCount()
    {
        m_connectCount--;
    }

    /**
     * 连接建立成功
     *
     * @param session
     */
    @OnOpen
    public void Open(Session session)
    {
        m_session = session;
        webSocketSet.add(this);
        addConnectCount();
        m_logger.info(String.format("有新连接加入,当前在线人数为:%s", getConnectCount()));
        try
        {
            SendMessage("连接成功...!!!");
        }
        catch (IOException e)
        {
            m_logger.error("WebSocket IO异常");
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
        m_logger.info(String.format("有一个连接关闭,当前在线人数:", getConnectCount()));
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
        m_logger.info(String.format("收到来自客户端的信息:%s", message));
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
        m_logger.info(String.format("%s连接时发生错误!", session.getId()));
        throwable.printStackTrace();
    }

    public void SendMessage(String message) throws IOException
    {
        m_session.getBasicRemote().sendText(message);
    }
}
