package faststore.framework.processor.biz.handler;


import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;

import java.util.HashMap;
import java.util.Map;

public class AbstractRequestHandlerChains {
    private RequestHandler errorHandler = new DefaultRequestHandler();
    private Map<CommonHeader, RequestHandler> handlerMap = new HashMap<CommonHeader, RequestHandler>();

    public synchronized void register(RequestHandler requestHandler) {
        handlerMap.put(requestHandler.getRequestHeader(), requestHandler);
    }

    public CommonResponse handler(CommonRequest commonRequest) {
        RequestHandler handler = handlerMap.get(commonRequest.getCommonHeader());
        if (handler == null) {
            handler = errorHandler;
        }

        return handler.handler(commonRequest);
    }
}
