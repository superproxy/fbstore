package fastore.dataserver.client;

import faststore.configserver.api.node.RouteInfo;
import faststore.framework.client.Client;

public interface ClusterDataClient {

    void init(RouteInfo routeInfo);

    Client getClient();

    Client getMasterClient();

    void stop();
}
