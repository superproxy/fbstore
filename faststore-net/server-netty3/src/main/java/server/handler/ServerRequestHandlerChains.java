package faststore.net.netty.common.faststoreprotocol.handler;

import faststore.net.netty.common.faststoreprotocol.request.Request;
import faststore.net.netty.common.faststoreprotocol.request.RequestHeader;
import faststore.net.netty.common.faststoreprotocol.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ServerRequestHandlerChains {
    private static ServerRequestHandlerChains instance = new ServerRequestHandlerChains();

    public static ServerRequestHandlerChains getInstance() {
        return instance;
    }

    private RequestHandler errorHandler = new DefaultRequestHandler();
    private Map<RequestHeader, RequestHandler> handlerMap = new HashMap<RequestHeader, RequestHandler>();

    public void register(RequestHandler requestHandler) {
        handlerMap.putIfAbsent(requestHandler.getRequestHeader(), requestHandler);
    }

    public Response handler(Request request) {
        RequestHandler handler = handlerMap.get(request.getRequestHeader());
        if (handler == null) {
            handler = errorHandler;
        }

        return handler.handler(request);
    }
}
