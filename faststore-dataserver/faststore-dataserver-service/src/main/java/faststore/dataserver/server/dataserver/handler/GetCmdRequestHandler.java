package faststore.dataserver.server.dataserver.handler;


import faststore.common.serializer.FastStoreSerializable;
import faststore.dataserver.api.vo.GetCmd;
import faststore.framework.processor.biz.common.RequestCode;
import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.handler.RequestHandler;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;
import faststore.dataserver.server.dataserver.service.ServiceManager;
import faststore.dataserver.server.dataserver.service.cmd.CmdService;

public class GetCmdRequestHandler implements RequestHandler {
    CommonHeader supported = new CommonHeader(RequestCode.GET.value());
    CmdService cmdService = ServiceManager.get(CmdService.class);

    @Override
    public CommonResponse handler(CommonRequest commonRequest) {
        String body = (String) commonRequest.getBody();
        GetCmd getCmd = FastStoreSerializable.fromJson(body, GetCmd.class);
        try {
            String value = (String) cmdService.get(getCmd.getKey());
            getCmd.setValue(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        CommonResponse<GetCmd> commonResponse = new CommonResponse<GetCmd>();
        CommonHeader responseHeader = new CommonHeader(RequestCode.GET.value());
        commonResponse.setResponseHeader(responseHeader);
        commonResponse.setResponseBody(getCmd);
        return commonResponse;
    }

    @Override
    public CommonHeader getRequestHeader() {
        return supported;
    }
}
