package faststore.configserver.api.node;

public interface NodeService {
    /**
     * 注册节点
     *
     * @throws Exception
     */
    void registerNode() throws Exception;

    void registerMaster() throws Exception;

    /**
     * 注销
     *
     * @throws Exception
     */
    void unRegister() throws Exception;
}
