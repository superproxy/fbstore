package faststore.framework.processor.biz.request;


import faststore.framework.processor.biz.common.CommonHeader;

public class CommonRequest<T> {
    CommonHeader commonHeader;
    T body;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public CommonHeader getCommonHeader() {
        return commonHeader;
    }

    public void setCommonHeader(CommonHeader commonHeader) {
        this.commonHeader = commonHeader;
    }
}
