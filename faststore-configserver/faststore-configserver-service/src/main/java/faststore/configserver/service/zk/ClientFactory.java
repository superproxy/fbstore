package faststore.configserver.service.zk;

public class ClientFactory {

    private static ZkClient zkClient = new ZkClient();

    public static ZkClient getZkClient() {
        return zkClient;
    }

    private static ZkService zkService = new ZkService();

    public static ZkService getZkService() {
        return zkService;
    }
}
