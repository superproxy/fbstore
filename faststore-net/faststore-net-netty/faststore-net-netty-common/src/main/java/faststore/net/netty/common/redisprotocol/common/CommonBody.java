package faststore.net.netty.common.redisprotocol.common;

import java.nio.charset.Charset;

public class CommonBody {

    public CommonBody() {
    }

    public CommonBody(byte[] body) {
        this.body = body;
    }

    byte[] body;

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body.getBytes(Charset.forName("utf-8"));
    }
}
