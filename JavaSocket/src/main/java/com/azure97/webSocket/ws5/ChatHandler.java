package com.azure97.webSocket.ws5;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/9/24 周二 10:24
 * @Version 1.0
 **/
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 自定义处理器
 * @author 二货
 * <pre>
 *     继承SimpleChannelInboundHandler<TextWebSocketFrame>并实现channelRead0（通道读取）
 * </pre>
 */

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 创建ChannelGroup对象存储所有连接的用户
     */
    private static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 有新消息时会调用这个方法
     *
     * @param channelHandlerContext 上下文处理器
     * @param textWebSocketFrame    文本
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        //获取客户端发送的消息内容
        String text = textWebSocketFrame.text();
        System.out.println("收到客户端发送来的消息:  " + text);
        //遍历出所有连接的通道
        for (Channel channel : clients) {
            //推送给所有的通道
            channel.writeAndFlush(new TextWebSocketFrame("服务器: 收到客户端发送来的消息: " + text));
        }
    }

    /**
     * 有新的连接建立时
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //加入通道组
        clients.add(ctx.channel());
    }

    /**
     * 不活跃时会调用这个方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //移除出通道组
        clients.remove(ctx.channel());
    }

}
