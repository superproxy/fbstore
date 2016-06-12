package faststore.configserver.service.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZkClient {
    private CuratorFramework client;
    String zookeeperConnectionString = "127.0.0.1:2181";

    public ZkClient() {
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ZkClient(String zk) {
        this.zookeeperConnectionString = zk;
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CuratorFramework getClient() {
        return client;
    }

    private void start() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // slector leader有延时的
        int sessionTimeoutMs = 30000;
        int connectionTimeoutMs = 30000;
        client = CuratorFrameworkFactory.builder()
//                .sessionTimeoutMs(sessionTimeoutMs)
                .connectString(zookeeperConnectionString)
                .retryPolicy(retryPolicy)
//                .namespace(NAME_SPACE)
                .build();
        client.start();
    }

    public void stop() throws Exception {
        client.close();
    }
}
