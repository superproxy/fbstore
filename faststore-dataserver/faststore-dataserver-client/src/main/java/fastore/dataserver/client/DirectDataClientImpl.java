package fastore.dataserver.client;

import faststore.common.serializer.FastStoreSerializable;
import faststore.dataserver.api.vo.GetCmd;
import faststore.dataserver.api.vo.SetCmd;
import faststore.framework.client.Client;
import faststore.framework.client.ClientContext;
import faststore.framework.processor.biz.common.RequestCode;
import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;

import java.util.ServiceLoader;

public class DirectDataClientImpl implements DataClient {
    private  Client client;
    public  DirectDataClientImpl(String  remoteIp, int port, String clientType,String protocolType) {
        this.client = init(remoteIp, port, clientType, protocolType);
    }
    private Client init(String remoteIp, int port, String clientType, String protocolType) {
        ClientContext clientContext = new ClientContext();

        clientContext.setClientType(clientType);
        clientContext.setProtocolType(protocolType);
        clientContext.setRemoteIp(remoteIp);
        clientContext.setRemotePort(port);
        ServiceLoader<Client> serviceLoader = ServiceLoader.load(Client.class);
        for (Client c : serviceLoader) {
            if (c.getClass().getName().contains(clientType.toLowerCase())) {
                try {
                    c.init(clientContext);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return c;
            }
        }
        return null;
    }

    @Override
    public void put(String key, Object value) throws Exception {
        CommonRequest<SetCmd> commonRequest = new CommonRequest<SetCmd>();
        commonRequest.setCommonHeader(new CommonHeader(RequestCode.SET.value()));
        SetCmd setCmd = new SetCmd();
        setCmd.setKey(key);
        setCmd.setValue(FastStoreSerializable.toJson(value));
        commonRequest.setBody(setCmd);
        CommonResponse response = client.send(commonRequest);

        if (response != null && response.getResponseHeader().getResultCode() == 0) {
            return;
        } else {
            throw new RuntimeException("error");
        }
    }

    @Override
    public Object get(String key) throws Exception {
        CommonRequest<GetCmd> commonRequest = new CommonRequest<GetCmd>();
        commonRequest.setCommonHeader(new CommonHeader(RequestCode.GET.value()));
        GetCmd getCmd = new GetCmd();
        getCmd.setKey(key);
        commonRequest.setBody(getCmd);
        CommonResponse response = client.send(commonRequest);

        if (response != null && response.getResponseHeader().getResultCode() == 0) {
            return response.getResponseBody();
        } else {
            throw new RuntimeException("error");
        }

    }

    @Override
    public void stop() throws Exception {
    }



}
