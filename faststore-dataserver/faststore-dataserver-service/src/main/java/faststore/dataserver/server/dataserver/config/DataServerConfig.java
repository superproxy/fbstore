package faststore.dataserver.server.dataserver.config;

import faststore.framework.protocol.ProtocolType;
import faststore.framework.storage.StorageType;

public class DataServerConfig {
    private String group;
    private String nodeId;
    private String ip;
    private String authKey;
    private StorageType storageType;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    private ProtocolType protocolType;

    private static DataServerConfig instance = new DataServerConfig();

    public static DataServerConfig getInstance() {
        return instance;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
