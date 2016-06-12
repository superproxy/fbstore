package faststore.framework.processor.biz.handler;


import faststore.framework.processor.biz.common.RequestCode;
import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.response.CommonResponse;

public class DefaultResponse extends CommonResponse<String> {

    public DefaultResponse() {
        this.setResponseHeader(new CommonHeader(RequestCode.HELLO.value()));
        this.setResponseBody("default");
    }
}
