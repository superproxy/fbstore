package faststore.net.netty.client;

import faststore.framework.processor.biz.common.RequestCode;
import faststore.net.netty.common.faststoreprotocol.codec.ResponseEncoder;
import faststore.net.netty.common.faststoreprotocol.common.FSCommonBody;
import faststore.net.netty.common.faststoreprotocol.common.FSCommonHeader;
import faststore.net.netty.common.faststoreprotocol.netty.FastStoreNettyChannelHandler;
import faststore.net.netty.common.faststoreprotocol.response.FSResponse;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class ClientFastStoreNettyChannelHandler extends FastStoreNettyChannelHandler {

    private static Logger logger = LoggerFactory.getLogger(ClientFastStoreNettyChannelHandler.class);

    /**
     * 连接成功后，向server发送消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    public void channelActive2(ChannelHandlerContext ctx) throws Exception {
        logger.info("HelloClientIntHandler.channelActive");
        FSResponse FSResponse = new FSResponse();
        FSCommonHeader header = new FSCommonHeader(RequestCode.HELLO.value());
        FSResponse.setFsRequestHeaer(header);
        FSCommonBody responseBody = new FSCommonBody();
        responseBody.setBody("hello");
        FSResponse.setFsRequestBody(responseBody);
        try {
            ByteBuffer headerBuffer = ResponseEncoder.encode(FSResponse);
            ctx.write(headerBuffer.array());
            byte[] body = FSResponse.getFsRequestBody().getBody();
            if (body != null) {
                ctx.write(body);
            }
            ctx.flush();
        } catch (Exception e) {
////            log.error("encode exception, " + RemotingHelper.parseChannelRemoteAddr(ctx.channel()), e);
//            if (faststore.framework.processor.response != null) {
//                log.error(faststore.framework.processor.response.toString());
//            }
//            RemotingUtil.closeChannel(ctx.channel());
        }
    }
}
