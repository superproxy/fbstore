package faststore.net.netty.common.faststoreprotocol.response;

public class Response {
   protected ResponseHeader requestHeaer;
    protected  ResponseBody requestBody;

    public ResponseBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(ResponseBody requestBody) {
        this.requestBody = requestBody;
    }

    public ResponseHeader getRequestHeaer() {
        return requestHeaer;
    }

    public void setRequestHeaer(ResponseHeader requestHeaer) {
        this.requestHeaer = requestHeaer;
    }


    @Override
    public String toString() {
        return "Response{" +
                "requestBody=" + requestBody +
                ", requestHeaer=" + requestHeaer +
                '}';
    }
}
