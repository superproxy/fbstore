package faststore.configserver.client;

import faststore.configserver.api.client.ConfigClient;
import faststore.configserver.api.leader.LeaderListener;
import faststore.configserver.api.node.NodeInfo;
import faststore.configserver.api.node.RouteInfo;
import org.testng.annotations.Test;

import java.util.Date;

public class ConfigClientImplTest {
    String zk = "127.0.0.1:2181";
    @Test
    public void testStart() throws Exception {
//        String ip = "127.0.0.1";
//        String nodeName = "node1";
//        int port = 12;
//        Date startTime = new Date();
//
//        NodeInfo node1 =new NodeInfo(ip, nodeName, port, startTime);
//        NodeInfo node3 = new NodeInfo("127.0.0.1", "node3", 13, new Date());
//        doStart(node3);
        NodeInfo node1 = new NodeInfo("127.0.0.1", "node1", 11, new Date());
        doStart(node1);
//        NodeInfo node2 = new NodeInfo("127.0.0.1", "node2", 12, new Date());
//        doStart(node2);

        Thread.sleep(Integer.MAX_VALUE);
    }

    private void doStart(final NodeInfo nodeInfo) throws Exception {
        final ConfigClient client = new ConfigClientImpl(zk,"group0");
        client.start();

        client.startLeaderService(new LeaderListener() {
            @Override
            public void onEvent() {
                // do leader;
                System.out.println("i am leader");
                try {
                    client.registerMasterNodeService(nodeInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, nodeInfo);

        client.registerNodeService(nodeInfo);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RouteInfo routeInfo = client.getRouteInfo();
                    System.out.println(routeInfo);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private NodeInfo getNodeInfo() {
        String ip = "127.0.0.1";
        String nodeName = "node1";
        int port = 12;
        Date startTime = new Date();
        return new NodeInfo(ip, nodeName, port, startTime);
    }

    @Test
    public void testStop() throws Exception {

    }

    @Test
    public void testStartLeaderService() throws Exception {

    }
}