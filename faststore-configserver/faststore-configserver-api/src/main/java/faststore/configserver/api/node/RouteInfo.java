package faststore.configserver.api.node;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RouteInfo implements Serializable {

    private Map<String, NodeInfo> masterNodeMap = new ConcurrentHashMap<String, NodeInfo>();
    private Map<String, NodeInfo> slaveNodeMap = new ConcurrentHashMap<String, NodeInfo>();

    public void addMaster(NodeInfo nodeInfo) {
        nodeInfo.setRole(1);
        masterNodeMap.put(nodeInfo.getNodeName(), nodeInfo);
    }

    public void addSlaves(NodeInfo nodeInfo) {
        nodeInfo.setRole(0);
        slaveNodeMap.put(nodeInfo.getNodeName(), nodeInfo);
    }

    public void addSlaves(List<NodeInfo> nodeInfoList) {
        if (nodeInfoList == null) {
            return;
        }
        for (NodeInfo nodeInfo : nodeInfoList) {
            nodeInfo.setRole(0);
            slaveNodeMap.put(nodeInfo.getNodeName(), nodeInfo);
        }
    }

    public NodeInfo getMaster() {
        for (Map.Entry<String, NodeInfo> node : masterNodeMap.entrySet()) {
//            if (node.getValue().getRole() == 1) {
                return node.getValue();
//            }
        }
        return null;
    }

    public Set<Map.Entry<String, NodeInfo>> getSlaves() {
        return Collections.unmodifiableSet(slaveNodeMap.entrySet());
    }

    @Override
    public String toString() {
        return "RouteInfo{" +
                "masterNodeMap=" + masterNodeMap +
                ", slaveNodeMap=" + slaveNodeMap +
                '}';
    }

    public void clearSlaves() {
        slaveNodeMap.clear();
    }

    public void clearMasters() {
        masterNodeMap.clear();
    }

    public void addMasters(List<NodeInfo> nodeInfoList) {
        if (nodeInfoList == null) {
            return;
        }
        for (NodeInfo nodeInfo : nodeInfoList) {
            nodeInfo.setRole(0);
            masterNodeMap.put(nodeInfo.getNodeName(), nodeInfo);
        }
    }
}
