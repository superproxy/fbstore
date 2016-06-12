package faststore.net.netty.common.redisprotocol.response;

import faststore.net.netty.common.redisprotocol.common.CommonBody;
import faststore.net.netty.common.redisprotocol.common.CommonHeader;

public class Response {
    protected CommonHeader requestHeaer;
    protected CommonBody requestBody;

    public CommonBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(CommonBody requestBody) {
        this.requestBody = requestBody;
    }

    public CommonHeader getRequestHeaer() {
        return requestHeaer;
    }

    public void setRequestHeaer(CommonHeader requestHeaer) {
        this.requestHeaer = requestHeaer;
    }


    @Override
    public String toString() {
        return "Response{" +
                "fsRequestBody=" + requestBody +
                ", fsRequestHeaer=" + requestHeaer +
                '}';
    }
}
