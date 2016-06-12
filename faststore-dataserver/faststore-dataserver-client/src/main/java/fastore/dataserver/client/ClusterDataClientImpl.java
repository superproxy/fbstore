package fastore.dataserver.client;

import faststore.configserver.api.node.NodeInfo;
import faststore.configserver.api.node.RouteInfo;
import faststore.framework.client.Client;
import faststore.framework.client.ClientContext;
import faststore.framework.protocol.TransportType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 集群client
 */
public class ClusterDataClientImpl implements ClusterDataClient {
    private final static Logger logger = LoggerFactory.getLogger(ClusterDataClientImpl.class);

    private List<Client> clientList;

    private Client masterClient;

    private Client initDataClient(NodeInfo nodeInfo) throws Exception {
        ClientContext clientContext = new ClientContext();
        clientContext.setRemoteIp(nodeInfo.getIp());
        clientContext.setRemotePort(nodeInfo.getPort());
        clientContext.setClientType(TransportType.THRIFT.name());

        Client client = newClient(clientContext);
        if (client == null) {
            logger.error("no client found");
        }

        client.init(clientContext);

        return client;
    }


    private Client newClient(ClientContext clientContext) {
        String serverType = clientContext.getClientType();
//        // 单例
//        ServiceLoader<Client> serviceLoader = ServiceLoader.load(Client.class);
//
//        for (Client c : serviceLoader) {
//            if (c.getClass().getName().contains(serverType)) {
//                return c;
//            }
//        }
        try {
            return (Client) Class.forName("thrift").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void init(RouteInfo routeInfo) {
        NodeInfo nodeInfo = routeInfo.getMaster();
        try {
            Client masterClient = initDataClient(nodeInfo);
            this.masterClient = masterClient;
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Client> clientList = new ArrayList<Client>();
        if (masterClient != null) {
            clientList.add(masterClient);
        }

        for (Map.Entry<String, NodeInfo> entry : routeInfo.getSlaves()) {
            Client client = null;
            try {
                client = initDataClient(entry.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (client != null) {
                clientList.add(client);
            }
        }
        this.clientList = clientList;
    }

    private AtomicInteger currentCount = new AtomicInteger(0);

    @Override
    public Client getClient() {
        if (clientList.size() == 0) {
            return null;
        }

        int index = currentCount.incrementAndGet() % clientList.size();
        return clientList.get(index);
    }

    @Override
    public Client getMasterClient() {
        return masterClient;
    }

    @Override
    public void stop() {
    }
}
