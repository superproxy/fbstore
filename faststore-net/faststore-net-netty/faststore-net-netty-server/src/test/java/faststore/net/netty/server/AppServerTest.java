package faststore.net.netty.server;

import faststore.framework.processor.biz.handler.DefaultRequestHandlerChains;
import faststore.framework.protocol.ProtocolType;
import faststore.framework.protocol.TransportType;
import faststore.framework.server.Server;
import faststore.framework.server.ServerConfig;
import faststore.framework.server.ServerContext;
import org.junit.Test;

/**
 * Created by 14120295 on 2016/6/8.
 */


public class AppServerTest {

    @Test
    public void test() throws Exception {

        ServerContext serverContext = buildServerContext();


        Server server = new AppServer();
        if (server != null) {
            server.init(serverContext);
            server.start();
        }

    }

    private static ServerContext buildServerContext() {
        ServerContext serverContext = new ServerContext();

        ServerConfig serverConfig = new ServerConfig(5999);
        serverConfig.setServerType(TransportType.NETTY.name());
        serverConfig.setProtocol(ProtocolType.FASTSTORE.name());
        serverContext.setServerConfig(serverConfig);

        DefaultRequestHandlerChains defaultRequestHandlerChains = DefaultRequestHandlerChains.getInstance();

        //  mock
        serverContext.setRequestHandlerChains(defaultRequestHandlerChains);
        return serverContext;
    }

}