package faststore.framework.server;

public class ServerConfig {
    private int port;
    private String serverType;

    private String protocol;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

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

        public Builder setProtocol(String protocol) {
            serverConfig.setProtocol(protocol);
            return this;
        }

        public ServerConfig build() {
            return serverConfig;
        }
    }
}
