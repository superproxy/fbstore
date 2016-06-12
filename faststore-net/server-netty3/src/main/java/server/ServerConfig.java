package faststore.dataserver.server;

public class ServerConfig {
    int port = 5999;

    public ServerConfig(int port) {
        this.port = port;
    }

    public ServerConfig() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ServerConfig serverConfig = new ServerConfig();

        public Builder setPort(int port) {
            serverConfig.setPort(port);
            return this;
        }

        public ServerConfig build() {
            return serverConfig;
        }
    }
}
