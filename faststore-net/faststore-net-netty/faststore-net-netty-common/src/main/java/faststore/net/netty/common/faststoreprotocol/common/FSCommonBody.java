package faststore.net.netty.common.faststoreprotocol.common;

import java.nio.charset.Charset;

public class FSCommonBody {

    public FSCommonBody() {
    }

    public FSCommonBody(byte[] body) {
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
