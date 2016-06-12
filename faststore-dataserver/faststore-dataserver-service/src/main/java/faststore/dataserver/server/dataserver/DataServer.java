package faststore.dataserver.server.dataserver;

import faststore.configserver.api.client.ConfigClient;
import faststore.configserver.api.group.Group;
import faststore.configserver.api.leader.LeaderListener;
import faststore.configserver.api.node.NodeInfo;
import faststore.configserver.api.shard.Shard;
import faststore.configserver.client.ConfigClientImpl;
import faststore.framework.processor.biz.handler.DefaultRequestHandlerChains;
import faststore.framework.protocol.ProtocolType;
import faststore.framework.protocol.TransportType;
import faststore.framework.server.Server;
import faststore.framework.server.ServerConfig;
import faststore.framework.server.ServerContext;
import faststore.framework.storage.StorageType;
import org.apache.commons.lang3.StringUtils;
import faststore.dataserver.server.dataserver.config.DataServerConfig;
import faststore.dataserver.server.dataserver.handler.GetCmdRequestHandler;
import faststore.dataserver.server.dataserver.handler.SetCmdRequestHandler;

import java.util.Date;
import java.util.ServiceLoader;

public class DataServer {

    private static int port = 5999;
    private static String nodeName = "dataserver1";
    private static String nodeIp = "127.0.0.1";
    private static String zk ="";// "127.0.0.1:2181";
    private static String groupName = "group0";
    private static String shardName = "shard0";
    private static String dbPath = "/data/leveldb1";


    private static ServerContext buildServerContext() {
        ServerContext serverContext = new ServerContext();
        ServerConfig serverConfig = new ServerConfig(port);
        serverConfig.setPort(port);
        serverConfig.setServerType(TransportType.NETTY.name());
        serverConfig.setProtocol(ProtocolType.FASTSTORE.name());
        serverContext.setServerConfig(serverConfig);
        DataServerConfig.getInstance().setStorageType(StorageType.LEVEL_DB);
        DataServerConfig.getInstance().setPath(dbPath);

        DefaultRequestHandlerChains defaultRequestHandlerChains = DefaultRequestHandlerChains.getInstance();
        defaultRequestHandlerChains.register(new SetCmdRequestHandler());
        defaultRequestHandlerChains.register(new GetCmdRequestHandler());
        serverContext.setRequestHandlerChains(defaultRequestHandlerChains);
        return serverContext;
    }

    public static void main(String[] args) throws Exception {
        ServerContext serverContext = buildServerContext();
        ServiceLoader<Server> serviceLoader = ServiceLoader.load(Server.class);

        String serverType = serverContext.getServerConfig().getServerType();
        Server server = null;
        for (Server s : serviceLoader) {
            if (s.getClass().getName().contains(serverType)) {
                server = s;
                break;
            }
        }

        if (server != null) {
            server.init(serverContext);
            server.start();
        }

        if (StringUtils.isNotEmpty(zk)) {
            initConfigClient();
        }

    }

    private static void initConfigClient() throws Exception {
        final NodeInfo nodeInfo = buildNodeInfo();
        // 启动注册服务

        final ConfigClient configClient = new ConfigClientImpl(zk, groupName);
        configClient.start();


        configClient.startLeaderService(new LeaderListener() {
            @Override
            public void onEvent() {
                // do leader;
                System.out.println("i am leader");
                try {
                    configClient.registerMasterNodeService(nodeInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, nodeInfo);

        configClient.registerNodeService(nodeInfo);
    }

    private static NodeInfo buildNodeInfo() {
        Group group = new Group();
        group.setName(groupName);
        Shard shard = new Shard();
        shard.setGroup(group);
        shard.setName(shardName);
        final NodeInfo nodeInfo = new NodeInfo(groupName, shardName, nodeName, nodeIp, port, new Date());
        nodeInfo.setShard(shard);
        nodeInfo.setGroupName(groupName);
        return nodeInfo;
    }


}
