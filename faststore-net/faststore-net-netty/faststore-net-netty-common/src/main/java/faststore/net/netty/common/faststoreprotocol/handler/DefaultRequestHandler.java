package faststore.net.netty.common.faststoreprotocol.handler;

import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.handler.RequestHandler;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;

public class DefaultRequestHandler implements RequestHandler {
    @Override
    public CommonResponse handler(CommonRequest commonRequest) {
        return null;
    }

    @Override
    public CommonHeader getRequestHeader() {
        return null;
    }
//  RequestHandler  CommonHeader supported = new CommonHeader(CmdCode.HELLO.value());
//
//    @Override
//    public Response handler(Request request) {
//        return new DefaultResponse();
//    }
//
//    @Override
//    public CommonHeader getRequestHeader() {
//        return supported;
//    }
}
