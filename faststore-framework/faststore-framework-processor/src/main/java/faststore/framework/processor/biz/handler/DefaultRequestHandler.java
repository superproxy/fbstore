package faststore.framework.processor.biz.handler;


import faststore.framework.processor.biz.common.RequestCode;
import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;

public class DefaultRequestHandler implements RequestHandler {
    CommonHeader supported = new CommonHeader(RequestCode.HELLO.value());

    @Override
    public CommonResponse handler(CommonRequest commonRequest) {
        return new DefaultResponse();
    }

    @Override
    public CommonHeader getRequestHeader() {
        return supported;
    }
}
