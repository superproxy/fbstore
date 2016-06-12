package faststore.net.netty.common.faststoreprotocol.handler;

import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Date;

public class EchoServerHandler extends SimpleChannelUpstreamHandler {
    private static final Logger logger = LoggerFactory.getLogger(
            EchoServerHandler.class.getName());

    @Override
    public void handleUpstream(
            ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        if (e instanceof ChannelStateEvent) {
            logger.info(e.toString());
        }
        super.handleUpstream(ctx, e);
    }

    @Override
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        // Send greeting for a new connection.
        e.getChannel().write(
                "Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        e.getChannel().write("It is " + new Date() + " now.\r\n");
    }


    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        // Print out the line received from the faststore.dataserver.server.
        System.err.println(e.getMessage());  //buffer

        e.getChannel().write(e.getMessage());
        e.getChannel().write("\r\n");
        //  解析处理
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
        logger.error(e.toString(), e.getCause());
        e.getChannel().close();
    }
}
