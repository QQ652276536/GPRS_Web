package com.zistone.gprs.service;

import com.zistone.gprs.util.MyPropertiesUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class TestNettyServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);
    private static final int PORT;

    static {
        PORT = Integer.parseInt(MyPropertiesUtil.GetValueProperties().getProperty("PORT_SOCKET1"));
    }

    class NettyServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (ctx instanceof IdleStateEvent) {
                IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
                switch (idleStateEvent.state()) {
                    //读超时
                    case READER_IDLE:
                        ctx.close();
                        LOGGER.info("服务端读取超时，关闭与该客户端的连接");
                        break;
                    //写超时
                    case WRITER_IDLE:
                        LOGGER.info("服务端发送超时");
                        break;
                    //所有类型的超时，包括读和写
                    case ALL_IDLE:
                        break;
                }
            } else {
                super.userEventTriggered(ctx, evt);
            }
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            LOGGER.info("通道" + ctx.channel().localAddress() + "激活");
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            LOGGER.info("通道" + ctx.channel().localAddress() + "关闭");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            String str = (String) msg;
            LOGGER.info("收到：" + str);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            LOGGER.info("数据接收完毕");
            //刷新写出区域，完成后关闭通道连接
//            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
            LOGGER.info("已发送数据至客户端");
            String sendInfo = "~！@#￥%……&**（）-+深圳123北ABC站\n";
            ctx.writeAndFlush(Unpooled.copiedBuffer(sendInfo, CharsetUtil.UTF_8));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            //发生异常就关闭连接
            LOGGER.error("异常信息：\n" + cause.getMessage());
            ctx.close();
        }
    }

    public String GetMessage(ByteBuf byteBuf) throws UnsupportedEncodingException {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        return new String(bytes, "UTF-8");
    }

    public void Start() {
        EventLoopGroup eventLoopGroup1 = new NioEventLoopGroup();
        EventLoopGroup eventLoopGroup2 = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            //最大连接数
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            //绑定线程池
            serverBootstrap.group(eventLoopGroup2, eventLoopGroup1);
            //通道标识，表示服务端
            serverBootstrap.channel(NioServerSocketChannel.class);
            //绑定端口
            serverBootstrap.localAddress(PORT);
            //客户端连接时触发
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    InetSocketAddress address = socketChannel.localAddress();
                    LOGGER.info("\n客户端" + address + "连接");
                    //心跳检测
                    socketChannel.pipeline().addLast(new IdleStateHandler(10, 5, 0, TimeUnit.SECONDS));
                    //编码解码
                    socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    socketChannel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                    //业务处理
                    socketChannel.pipeline().addLast(new NettyServerHandler());
                }
            });
            //异步绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            //关闭通道，这行代码的意思是
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
//            eventLoopGroup1.shutdownGracefully();
//            eventLoopGroup2.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TestNettyServer().Start();
    }

}
