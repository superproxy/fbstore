package faststore.framework.client;

import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;

public interface Client {
    void init(ClientContext clientContext) throws Exception;

    void stop() throws Exception;

    <T> CommonResponse send(CommonRequest<T> commonRequest) throws Exception;
}
