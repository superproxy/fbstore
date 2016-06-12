package faststore.configserver.api.node;

import faststore.configserver.api.group.Group;
import faststore.configserver.api.shard.Shard;

import java.io.Serializable;
import java.util.Date;

public class NodeInfo implements Serializable {
    Shard shard;

    public Shard getShard() {
        return shard;
    }

    public void setShard(Shard shard) {
        this.shard = shard;
    }

    private String ip;
    private int port;

    private Date startTime;

    private String nodeName;
    private String groupName;

    private int role;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public NodeInfo() {
    }


    public NodeInfo(String groupName, String shardName, String nodeName, String ip, int port, Date startTime) {
        this.ip = ip;
        this.nodeName = nodeName;
        this.port = port;
        this.startTime = startTime;
        Shard shard = new Shard();
        shard.setName(shardName);
        this.setShard(shard);
        Group group = new Group();
        this.setGroupName(groupName);
        this.getShard().setGroup(group);
    }

    public NodeInfo(String ip, String nodeName, int port, Date startTime) {
        this.ip = ip;
        this.nodeName = nodeName;
        this.port = port;
        this.startTime = startTime;

        Shard shard = new Shard();
        shard.setName("shard0");
        this.setShard(shard);
        Group group = new Group();
        String groupName = "group0";
        this.setGroupName(groupName);
        this.getShard().setGroup(group);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private NodeInfo bean = new NodeInfo();

        public Builder setPort(int port) {
            bean.setPort(port);
            return this;
        }

        public NodeInfo build() {
            return bean;
        }
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
