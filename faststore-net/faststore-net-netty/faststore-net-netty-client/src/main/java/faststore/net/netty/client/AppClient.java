package faststore.net.netty.client;

import faststore.framework.client.Client;
import faststore.framework.client.ClientContext;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;


public class AppClient implements Client {
    private NettyClient nettyClient;

    @Override
    public void init(ClientContext clientContext) throws Exception {
        nettyClient = new NettyClient();
        nettyClient.init(clientContext.getRemoteIp(), clientContext.getRemotePort());
    }

    @Override
    public void stop() throws Exception {
        nettyClient.stop();

    }

    @Override
    public <T> CommonResponse send(CommonRequest<T> commonRequest) throws Exception {
//        Request request = Convert.convertRequest(commonRequest);
//        Response response = doAction(request);
//        return Convert.convertResponse(response);

        nettyClient.writeAndFlush(commonRequest);

        return null;
    }

}
