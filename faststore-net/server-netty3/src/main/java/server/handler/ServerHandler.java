package faststore.net.netty.common.faststoreprotocol.handler;

import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import faststore.net.netty.common.faststoreprotocol.request.Request;
import faststore.net.netty.common.faststoreprotocol.request.RequestCode;
import faststore.net.netty.common.faststoreprotocol.request.RequestHeader;
import faststore.net.netty.common.faststoreprotocol.response.Response;

import java.net.InetAddress;
import java.util.Date;

public class ServerHandler extends SimpleChannelUpstreamHandler {
    private static final Logger logger = LoggerFactory.getLogger(
            ServerHandler.class.getName());

    //    private ServerRequestHandlerChains handlerChains = new ServerRequestHandlerChains();
    private ServerRequestHandlerChains handlerChains = ServerRequestHandlerChains.getInstance();

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
        String msg = String.valueOf(e.getMessage());
        char c = msg.charAt(0);
        Request request = new Request();
        RequestCode requestCode = RequestCode.value(Integer.valueOf(String.valueOf(c)));
        if (requestCode == null) {
            e.getChannel().write("unsupported cmd");
            e.getChannel().write("\r\n");
            return;
        }
        request.setRequestCode(requestCode);
        RequestHeader requestHeader = new RequestHeader(requestCode);
        request.setRequestHeader(requestHeader);

        Response response = handlerChains.handler(request);
        e.getChannel().write(response.toString());
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
