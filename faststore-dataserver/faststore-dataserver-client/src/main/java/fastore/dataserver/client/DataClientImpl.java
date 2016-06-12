package fastore.dataserver.client;

import faststore.common.serializer.FastStoreSerializable;
import faststore.configserver.api.client.ConfigClient;
import faststore.configserver.api.node.RouteInfo;
import faststore.configserver.client.ConfigClientImpl;
import faststore.dataserver.api.vo.GetCmd;
import faststore.dataserver.api.vo.SetCmd;
import faststore.framework.client.Client;
import faststore.framework.processor.biz.common.RequestCode;
import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;

public class DataClientImpl implements DataClient {
    //    private final static Logger logger = LoggerFactory.getLogger(DataClientImpl.class);
    private String zookeeperConnectionString = "127.0.0.1:2181";
    private String group = "group0";
//    private String zookeeperConnectionString = "10.27.113.40:2181";


    private ConfigClient configClient;

    private ClusterDataClient clusterClient;

    public DataClientImpl(String zk, String group) throws Exception {
        this.zookeeperConnectionString = zk;
        this.group = group;
        initConfigClient();

        RouteInfo routeInfo = configClient.getRouteInfo();
        clusterClient = new ClusterDataClientImpl();
        clusterClient.init(routeInfo);
    }

    public void initConfigClient() throws Exception {
        configClient = new ConfigClientImpl(zookeeperConnectionString, group);
        configClient.start();
    }


    @Override
    public void put(String key, Object value) throws Exception {
        CommonRequest<SetCmd> commonRequest = new CommonRequest<SetCmd>();
        commonRequest.setCommonHeader(new CommonHeader(RequestCode.SET.value()));
        SetCmd setCmd = new SetCmd();
        setCmd.setKey(key);
        setCmd.setValue(FastStoreSerializable.toJson(value));
        commonRequest.setBody(setCmd);
        CommonResponse response = getClient().send(commonRequest);

        if (response != null && response.getResponseHeader().getResultCode() == 0) {
            return;
        } else {
            throw new RuntimeException("error");
        }
    }

    private Client getClient() {
        return clusterClient.getClient();
    }


    private Client getMasterClient() {
        return clusterClient.getMasterClient();
    }

    @Override
    public Object get(String key) throws Exception {
        CommonRequest<GetCmd> commonRequest = new CommonRequest<GetCmd>();
        commonRequest.setCommonHeader(new CommonHeader(RequestCode.GET.value()));
        GetCmd getCmd = new GetCmd();
        getCmd.setKey(key);
        commonRequest.setBody(getCmd);
        CommonResponse response = getClient().send(commonRequest);

        if (response != null && response.getResponseHeader().getResultCode() == 0) {
            return response.getResponseBody();
        } else {
            throw new RuntimeException("error");
        }

    }

    @Override
    public void stop() throws Exception {
        clusterClient.stop();
    }
}
