package redis.server.netty;

import com.google.common.base.Charsets;
import faststore.framework.processor.biz.handler.DefaultRequestHandlerChains;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;
import faststore.net.netty.common.faststoreprotocol.Convert;
import faststore.net.netty.common.faststoreprotocol.request.FSRequest;
import faststore.net.netty.common.faststoreprotocol.response.FSResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import redis.netty4.Command;
import redis.netty4.InlineReply;
import redis.netty4.Reply;

import java.net.InetAddress;
import java.util.Date;

import static redis.netty4.ErrorReply.NYI_REPLY;
import static redis.netty4.StatusReply.QUIT;

public class RedisCommandHandler extends SimpleChannelInboundHandler<Command> {
    public RedisCommandHandler() {
    }

    private static final byte LOWER_DIFF = 'a' - 'A';

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send greeting for a new connection.
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.write("It is " + new Date() + " now.\r\n");
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Command msg) throws Exception {
        byte[] name = msg.getName();
        for (int i = 0; i < name.length; i++) {
            byte b = name[i];
            if (b >= 'A' && b <= 'Z') {
                name[i] = (byte) (b + LOWER_DIFF);  // 大编程小写
            }
        }

        String methodName = new String(name, Charsets.US_ASCII);

        FSRequest FSRequest = new FSRequest();
        CommonRequest commonRequest = Convert.converRequest(FSRequest);
        CommonResponse commonResponse = DefaultRequestHandlerChains.getInstance().handler(commonRequest);
        FSResponse FSResponse = Convert.convertRequest(commonResponse);
        Reply reply = null;
//        if (wrapper == null) {
//            reply = new ErrorReply("unknown command '" + new String(name, Charsets.US_ASCII) + "'");
//        } else {
//            reply = wrapper.execute(msg);
//        }


        if (reply == QUIT) {
            ctx.close();
        } else {
            if (msg.isInline()) {
                if (reply == null) {
                    reply = new InlineReply(null);
                } else {
                    reply = new InlineReply(reply.data());
                }
            }
            if (reply == null) {
                reply = NYI_REPLY;
            }
            ctx.write(reply);
        }
    }
}
