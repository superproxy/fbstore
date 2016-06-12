package faststore.framework.processor.biz.response;


import faststore.framework.processor.biz.common.CommonHeader;

public class CommonResponse<T> {
    protected CommonHeader responseHeader;
    protected T responseBody;

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    public CommonHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(CommonHeader responseHeader) {
        this.responseHeader = responseHeader;
    }


    @Override
    public String toString() {
        return "CommonResponse{" +
                "responseBody=" + responseBody +
                ", responseHeader=" + responseHeader +
                '}';
    }
}
