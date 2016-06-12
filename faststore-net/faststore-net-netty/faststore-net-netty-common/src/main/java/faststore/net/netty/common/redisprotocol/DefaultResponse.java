package faststore.net.netty.common.redisprotocol;

import faststore.net.netty.common.redisprotocol.common.CommonBody;
import faststore.net.netty.common.redisprotocol.response.Response;

public class DefaultResponse extends Response {

    public DefaultResponse() {
        CommonBody body = new CommonBody();
        body.setBody("defalut error");
        this.setRequestBody(body);
    }
}
