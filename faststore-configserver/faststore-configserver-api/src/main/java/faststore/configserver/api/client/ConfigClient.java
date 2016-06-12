package faststore.configserver.api.client;

import faststore.configserver.api.leader.LeaderListener;
import faststore.configserver.api.node.NodeInfo;
import faststore.configserver.api.node.RouteInfo;

public interface ConfigClient {

    void start() throws Exception;

    void stop() throws Exception;

    void startLeaderService(LeaderListener leaderListener, NodeInfo nodeInfo) throws Exception;

    /**
     * 注册节点信息
     * 1. 默认注册slave
     * 2. onLeader的时候注册master
     *
     * @throws Exception
     */
    void registerNodeService(NodeInfo nodeInfo) throws Exception;

    void registerMasterNodeService(NodeInfo nodeInfo) throws Exception;

    RouteInfo getRouteInfo() throws Exception;
}
