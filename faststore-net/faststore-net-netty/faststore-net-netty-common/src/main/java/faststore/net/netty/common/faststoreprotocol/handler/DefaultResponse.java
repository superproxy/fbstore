package faststore.net.netty.common.faststoreprotocol.handler;

import faststore.net.netty.common.faststoreprotocol.common.FSCommonBody;
import faststore.net.netty.common.faststoreprotocol.response.FSResponse;

public class DefaultResponse extends FSResponse {

    public DefaultResponse() {
        FSCommonBody body = new FSCommonBody();
        body.setBody("defalut error");
        this.setFsRequestBody(body);
    }
}
