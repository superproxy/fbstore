package faststore.net.netty.common.faststoreprotocol.handler;

import faststore.net.netty.common.faststoreprotocol.request.Request;
import faststore.net.netty.common.faststoreprotocol.request.RequestCode;
import faststore.net.netty.common.faststoreprotocol.request.RequestHeader;
import faststore.net.netty.common.faststoreprotocol.response.Response;

public class DefaultRequestHandler implements RequestHandler {
    RequestHeader supported = new RequestHeader(RequestCode.HELLO);

    @Override
    public Response handler(Request request) {
        return new DefaultResponse();
    }

    @Override
    public RequestHeader getRequestHeader() {
        return supported;
    }
}
