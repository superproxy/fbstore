package faststore.net.netty.common.faststoreprotocol.handler;

import faststore.net.netty.common.faststoreprotocol.response.Response;
import faststore.net.netty.common.faststoreprotocol.response.ResponseBody;

public class DefaultResponse extends Response {

    public DefaultResponse() {
        ResponseBody body = new ResponseBody();
        body.setMessage("defalut error");
        this.setRequestBody(body);
    }
}
