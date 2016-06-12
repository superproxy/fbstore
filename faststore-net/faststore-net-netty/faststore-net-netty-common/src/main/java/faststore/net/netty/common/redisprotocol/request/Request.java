package faststore.net.netty.common.redisprotocol.request;

import faststore.net.netty.common.redisprotocol.common.CommonBody;
import faststore.net.netty.common.redisprotocol.common.CommonHeader;

public class Request {
    CommonHeader commonHeader;
    CommonBody commonBody;

    public CommonBody getCommonBody() {
        return commonBody;
    }

    public void setCommonBody(CommonBody commonBody) {
        this.commonBody = commonBody;
    }

    public CommonHeader getCommonHeader() {
        return commonHeader;
    }

    public void setCommonHeader(CommonHeader commonHeader) {
        this.commonHeader = commonHeader;
    }
}
