package faststore.net.netty.common.faststoreprotocol.netty;

import faststore.framework.processor.biz.handler.DefaultRequestHandlerChains;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;
import faststore.net.netty.common.faststoreprotocol.Convert;
import faststore.net.netty.common.faststoreprotocol.request.FSRequest;
import faststore.net.netty.common.faststoreprotocol.response.FSResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FastStoreNettyChannelHandler extends SimpleChannelInboundHandler<FSRequest> {
    private static final Logger logger = LoggerFactory.getLogger(
            FastStoreNettyChannelHandler.class.getName());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FSRequest FSRequest) throws Exception {
//        RequestCode requestCode = RequestCode.value(Integer.valueOf(String.valueOf(c)));
//        faststore.framework.processor.request.setCmdCode(requestCode);
//        RequestHeader requestHeader = new RequestHeader(requestCode);
//        faststore.framework.processor.request.setRequestHeader(requestHeader);

        CommonRequest commonRequest = Convert.converRequest(FSRequest);

        CommonResponse commonResponse = DefaultRequestHandlerChains.getInstance().handler(commonRequest);
        FSResponse FSResponse = Convert.convertRequest(commonResponse);

        ctx.channel().write(FSResponse.toString());
        ctx.channel().write("\r\n");
    }
}

