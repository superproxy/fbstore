package faststore.net.thrift.server;

import faststore.framework.processor.biz.handler.DefaultRequestHandlerChains;
import faststore.framework.server.Server;
import faststore.framework.server.ServerConfig;
import faststore.framework.server.ServerContext;
import org.testng.annotations.Test;

import java.util.ServiceLoader;

/**
 * Created by 14120295 on 2016/6/8.
 */
public class AppServerTest {

    @Test
    public void test() throws Exception {
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
    }

    private static ServerContext buildServerContext() {
        ServerContext serverContext = new ServerContext();

        ServerConfig serverConfig = new ServerConfig(5999);
        serverContext.setServerConfig(serverConfig);

        DefaultRequestHandlerChains defaultRequestHandlerChains = DefaultRequestHandlerChains.getInstance();
        serverContext.setRequestHandlerChains(defaultRequestHandlerChains);
        return serverContext;
    }

}