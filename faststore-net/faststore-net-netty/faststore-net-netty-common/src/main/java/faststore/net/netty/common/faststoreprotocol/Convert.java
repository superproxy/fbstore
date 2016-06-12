package faststore.net.netty.common.faststoreprotocol;

import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;
import faststore.net.netty.common.faststoreprotocol.request.FSRequest;
import faststore.net.netty.common.faststoreprotocol.response.FSResponse;

/**
 * Created by 14120295 on 2016/6/12.
 */
public class Convert {
    public static CommonRequest converRequest(FSRequest FSRequest) {
        CommonRequest commonRequest = new CommonRequest();
        return commonRequest;
    }

    public static FSResponse convertRequest(CommonResponse commonResponse) {
        FSResponse FSResponse = new FSResponse();
        return FSResponse;
    }
}
