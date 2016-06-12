package faststore.framework.server;

public interface Server {

    void init(ServerContext serverContext) throws Exception;

    void start() throws Exception;

    void stop() throws Exception;

    ServerInfo getServerInfo() throws Exception;
}
