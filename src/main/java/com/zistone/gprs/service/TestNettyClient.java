package com.zistone.gprs.service;

import com.zistone.gprs.util.MyPropertiesUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class TestNettyClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    static {
        PORT = Integer.parseInt(MyPropertiesUtil.GetValueProperties().getProperty("PORT_SOCKET1"));
        HOST = String.valueOf(MyPropertiesUtil.GetValueProperties().getProperty("IP_WEB"));
    }

    private static final String HOST;
    private static final int PORT;

    private EventLoopGroup _eventLoopGroup;
    private Bootstrap _bootstrap;
    private ChannelFutureListener _channelFutureListener;

    class EchoClientHandler extends SimpleChannelInboundHandler {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            LOGGER.info("通道" + ctx.channel().localAddress() + "激活");
            String sendInfo = "武汉123火车ABC站~！@#￥%……&**（）-+";
            ctx.writeAndFlush(Unpooled.copiedBuffer(sendInfo, CharsetUtil.UTF_8));
            LOGGER.info("已发送数据至服务端");
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            LOGGER.info("通道" + ctx.channel().localAddress() + "关闭");
            Connect();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            //异常关闭
            ctx.close();
            LOGGER.error("异常退出：\n" + cause.getMessage());
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
//            LOGGER.info(channelHandlerContext.channel().remoteAddress());
            String str = (String) o;
            LOGGER.info("收到：" + str);
        }
    }

    public void Init() {
        _channelFutureListener = new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                //连接成功
                if (channelFuture.isSuccess()) {
                    LOGGER.info("成功连接服务端");
                } else {
                    LOGGER.error("连接服务端失败，10秒后尝试再次连接...");
                    channelFuture.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            Connect();
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            }
        };
    }

    public void Connect() {
        LOGGER.info("开始连接服务端...");
        ChannelFuture channelFuture = _bootstrap.connect();
        channelFuture.addListener(_channelFutureListener);
    }

    public void Start() throws InterruptedException {
        _eventLoopGroup = new NioEventLoopGroup();
        _bootstrap = new Bootstrap();
        //绑定线程池
        _bootstrap.group(_eventLoopGroup);
        //通道标识，表示服务端
        _bootstrap.channel(NioSocketChannel.class);
        //绑定端口
        _bootstrap.remoteAddress(new InetSocketAddress(HOST, PORT));
        _bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                //心跳检测
                socketChannel.pipeline().addLast(new IdleStateHandler(0, 0, 50));
                //编码解码
                socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                socketChannel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                //业务处理
                socketChannel.pipeline().addLast(new EchoClientHandler());
            }
        });
        //连接服务端
        Connect();
    }

    public static void main(String[] args) throws InterruptedException {
        TestNettyClient testNettyClient = new TestNettyClient();
        testNettyClient.Init();
        testNettyClient.Start();
    }

}
