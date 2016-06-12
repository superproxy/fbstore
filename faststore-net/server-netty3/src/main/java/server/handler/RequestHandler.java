package faststore.net.netty.common.faststoreprotocol.handler;

import faststore.net.netty.common.faststoreprotocol.request.Request;
import faststore.net.netty.common.faststoreprotocol.request.RequestHeader;
import faststore.net.netty.common.faststoreprotocol.response.Response;

public interface RequestHandler {
    Response handler(Request request);
    RequestHeader getRequestHeader();
}
