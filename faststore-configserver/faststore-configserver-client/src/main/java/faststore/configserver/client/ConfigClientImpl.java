package faststore.configserver.client;

import faststore.configserver.api.client.ConfigClient;
import faststore.configserver.api.leader.LeaderListener;
import faststore.configserver.api.leader.LeaderService;
import faststore.configserver.api.node.NodeInfo;
import faststore.configserver.api.node.NodeService;
import faststore.configserver.api.node.RouteInfo;
import faststore.configserver.api.node.RouteService;
import faststore.configserver.service.leader.LeaderServiceImpl;
import faststore.configserver.service.node.NodeServiceImpl;
import faststore.configserver.service.node.RouteServiceImpl;
import faststore.configserver.service.zk.ClientFactory;
import faststore.configserver.service.zk.ZkClient;
import org.apache.curator.framework.CuratorFramework;

public class ConfigClientImpl implements ConfigClient {

    private RouteService routeService;
    private String zkConnection;

    private ZkClient zkClient = ClientFactory.getZkClient();
    private CuratorFramework client = zkClient.getClient();
    private String group;

    public ConfigClientImpl(String zk, String group) {
        this.zkConnection = zk;
        this.group = group;
    }

    public void start() throws Exception {
//        client.start();
        routeService = new RouteServiceImpl(client, group);
    }

    @Override
    public void stop() throws Exception {
        zkClient.getClient().close();
    }

    @Override
    public void startLeaderService(LeaderListener leaderListener, NodeInfo nodeInfo) throws Exception {
        LeaderService leaderService = new LeaderServiceImpl(client, leaderListener, nodeInfo);
        leaderService.start();
    }

    @Override
    public void registerNodeService(NodeInfo nodeInfo) throws Exception {
        NodeService nodeService = new NodeServiceImpl(client, nodeInfo);
        nodeService.registerNode();
    }

    @Override
    public void registerMasterNodeService(NodeInfo nodeInfo) throws Exception {
        NodeService nodeService = new NodeServiceImpl(client, nodeInfo);
        nodeService.registerMaster();
    }


    @Override
    public RouteInfo getRouteInfo() throws Exception {
        return routeService.getRouteInfo();
    }

}
