package faststore.framework.processor.biz.handler;

import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;

public interface RequestHandler {
    CommonResponse handler(CommonRequest commonRequest);
    CommonHeader getRequestHeader();
}
