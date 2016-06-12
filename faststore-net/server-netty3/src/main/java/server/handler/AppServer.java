package faststore.net.netty.common.faststoreprotocol.handler;

import faststore.dataserver.server.Server;
import faststore.dataserver.server.ServerConfig;
import faststore.dataserver.server.ServerImpl;

public class AppServer {
    public static void main(String[] args) throws Exception {
        ServerConfig serverConfig = new ServerConfig(5999);
        Server server = new ServerImpl(serverConfig);
        server.start();
    }
}
