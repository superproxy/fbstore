package faststore.framework.server;

import faststore.framework.processor.biz.handler.DefaultRequestHandlerChains;

public class ServerContext {
    private ServerConfig serverConfig;
    private DefaultRequestHandlerChains defaultRequestHandlerChains;

    public DefaultRequestHandlerChains getRequestHandlerChains() {
        return defaultRequestHandlerChains;
    }

    public void setRequestHandlerChains(DefaultRequestHandlerChains defaultRequestHandlerChains) {
        this.defaultRequestHandlerChains = defaultRequestHandlerChains;
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }
}
