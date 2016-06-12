package faststore.net.thrift.server;

import faststore.framework.processor.biz.handler.DefaultRequestHandlerChains;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;
import faststore.net.thrift.api.request.DataServerService;
import faststore.net.thrift.api.request.Request;
import faststore.net.thrift.api.request.Response;
import faststore.net.thrift.protocol.Thrift2BizConvert;
import org.apache.thrift.TException;

public class ServerServiceHandler implements DataServerService.Iface {

    private DefaultRequestHandlerChains defaultRequestHandlerChains;

    @Override
    public Response doHandler(Request request) throws TException {
        CommonRequest commonRequest = Thrift2BizConvert.convertRequest(request);
        CommonResponse commonResponse = defaultRequestHandlerChains.handler(commonRequest);
//        Response response = new Response();
//        response.setCmdCode(1);
//        response.setVersion("v1.0");
        return Thrift2BizConvert.convertResponse(commonResponse);

    }

    @Override
    public Response doHandler2(Request request) throws TException {
        System.out.println(request);
        Response response = new Response();
        response.setCmdCode(2);
        response.setVersion("v1.0");
        return response;
    }

    public void setRequestHandlerChains(DefaultRequestHandlerChains defaultRequestHandlerChains) {
        this.defaultRequestHandlerChains = defaultRequestHandlerChains;
    }

    public DefaultRequestHandlerChains getRequestHandlerChains() {
        return defaultRequestHandlerChains;
    }
}