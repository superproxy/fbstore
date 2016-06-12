package faststore.net.thrift.protocol;

import faststore.common.serializer.FastStoreSerializable;
import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;
import faststore.net.thrift.api.request.Request;
import faststore.net.thrift.api.request.Response;

public class Thrift2BizConvert {

    public static <T> Request convertRequest(CommonRequest<T> commonRequest) {
        Request request = new Request();
        request.setVersion("1.0");
        request.setCmdCode(commonRequest.getCommonHeader().getCmdCode());
        request.setBody(FastStoreSerializable.toJson(commonRequest.getBody(), true));
        return request;
    }

    public static CommonRequest<String> convertRequest(Request request) {
        CommonRequest<String> commonRequest = new CommonRequest<String>();
        request.setVersion("1.0");
        commonRequest.setCommonHeader(new CommonHeader(request.getCmdCode()));
//        commonRequest.setBody(FastStoreSerializable.fromJson(request.body));
        commonRequest.setBody(request.getBody());
        return commonRequest;
    }

    public static CommonResponse convertResponse(Response response) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setResponseHeader(new CommonHeader(response.getCmdCode()));
        commonResponse.setResponseBody(response.getBody());
        return commonResponse;
    }

    public static Response convertResponse(CommonResponse commonResponse) {
        Response response = new Response();
        response.setBody(FastStoreSerializable.toJson(commonResponse.getResponseBody(), true));
        response.setCmdCode(commonResponse.getResponseHeader().getCmdCode());
        return response;
    }
}
