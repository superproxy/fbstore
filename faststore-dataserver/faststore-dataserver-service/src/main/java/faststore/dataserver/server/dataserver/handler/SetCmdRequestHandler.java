package faststore.dataserver.server.dataserver.handler;


import faststore.common.serializer.FastStoreSerializable;
import faststore.dataserver.api.vo.SetCmd;
import faststore.framework.processor.biz.common.RequestCode;
import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.handler.RequestHandler;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;
import faststore.dataserver.server.dataserver.service.cmd.CmdService;
import faststore.dataserver.server.dataserver.service.ServiceManager;

public class SetCmdRequestHandler implements RequestHandler {
    CommonHeader supported = new CommonHeader(RequestCode.SET.value());
    CmdService cmdService = ServiceManager.get(CmdService.class);

    @Override
    public CommonResponse handler(CommonRequest commonRequest) {
        String body = (String) commonRequest.getBody();
        SetCmd setCmd = FastStoreSerializable.fromJson(body, SetCmd.class);

        try {
            cmdService.put(setCmd.getKey(), setCmd.getValue());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        CommonResponse<SetCmd> commonResponse = new CommonResponse<SetCmd>();
        CommonHeader responseHeader = new CommonHeader(RequestCode.SET.value());
        commonResponse.setResponseHeader(responseHeader);
        commonResponse.setResponseBody(new SetCmd());
        return commonResponse;
    }

    @Override
    public CommonHeader getRequestHeader() {
        return supported;
    }
}
