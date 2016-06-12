package faststore.configserver.service.leader;

import faststore.configserver.api.common.Path;
import faststore.configserver.api.leader.LeaderListener;
import faststore.configserver.api.leader.LeaderService;
import faststore.configserver.api.node.NodeInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

public class LeaderServiceImpl extends LeaderSelectorListenerAdapter implements LeaderService {
    private CuratorFramework client;

    private LeaderListener leaderListener;
    private String group;
    private String shard;

    public LeaderServiceImpl(CuratorFramework client, LeaderListener leaderListener, NodeInfo nodeInfo) {
        this.client = client;
        this.leaderListener = leaderListener;
        this.group = nodeInfo.getGroupName();
        this.shard = nodeInfo.getShard().getName();
    }


    public void start() {
        String path = Path.buildSelector(group, shard);
        LeaderSelector selector = new LeaderSelector(client, path, this);
        selector.setId("ip" + System.currentTimeMillis());
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();  //  内部实现添加了一个listener
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        leaderListener.onEvent();

        // nerver release master
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


    @Override
    public void close() throws Exception {
        client.close();
    }


}
