package faststore.configserver.service.node;

import faststore.configserver.api.common.Path;
import faststore.configserver.api.node.NodeInfo;
import faststore.configserver.api.node.NodeService;
import faststore.configserver.service.zk.ClientFactory;
import faststore.configserver.service.zk.ZkService;
import org.apache.curator.framework.CuratorFramework;

public class NodeServiceImpl implements NodeService {
    private NodeInfo nodeInfo;

    private ZkService zkService = ClientFactory.getZkService();

    public NodeServiceImpl(CuratorFramework client, NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public void registerNode() throws Exception {
        // 创建节点
        // group 下面是shard可以做扩容、分片等应用
        // shard
        //  faststore/ group/nodes/masters/
        //  faststore/ group/nodes/slaves/
        // path需要存在

        try {
            String nodeName = nodeInfo.getNodeName();
            String groupName = nodeInfo.getShard().getGroup().getName();
            String shardName = nodeInfo.getShard().getName();
            zkService.delete(Path.buildMasterNode(groupName, shardName, nodeName));
            zkService.add(Path.buildSlaveNode(groupName, shardName, nodeName), nodeInfo, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void registerMaster() throws Exception {
        String nodeName = nodeInfo.getNodeName();
        String groupName = nodeInfo.getShard().getGroup().getName();
        String shardName = nodeInfo.getShard().getName();
        zkService.delete(Path.buildSlaveNode(groupName, shardName, nodeName));
        zkService.add(Path.buildMasterNode(groupName, shardName, nodeName), nodeInfo, false);
    }

    @Override
    public void unRegister() throws Exception {
        try {
            String nodeName = nodeInfo.getNodeName();
            String groupName = nodeInfo.getShard().getGroup().getName();
            String shardName = nodeInfo.getShard().getName();
            zkService.delete(Path.buildMasterNode(groupName, shardName, nodeName));
            zkService.delete(Path.buildSlaveNode(groupName, shardName, nodeName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
