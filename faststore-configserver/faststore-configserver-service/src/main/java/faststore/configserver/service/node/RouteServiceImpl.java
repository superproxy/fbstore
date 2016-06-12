package faststore.configserver.service.node;

import faststore.configserver.api.common.Path;
import faststore.configserver.api.node.NodeInfo;
import faststore.configserver.api.node.RouteInfo;
import faststore.configserver.api.node.RouteService;
import faststore.configserver.service.common.PathCallBack;
import faststore.configserver.service.zk.ClientFactory;
import faststore.configserver.service.zk.ZkService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private ZkService zkService = ClientFactory.getZkService();
    private RouteInfo routeInfo;

    private String group;
    private String shard;

    public RouteServiceImpl(CuratorFramework client, String group, String shard) throws Exception {
        this.group = group;
        this.shard = shard;
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, Path.buildNodes(group, shard), false);

        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                // 重新初始化数据
                initRouteInfo();
            }
        });
    }

    public RouteServiceImpl(CuratorFramework client, String group) throws Exception {
        this(client, group, Path.DEFAULT_SHARD_NAME);
    }

    @Override
    public synchronized RouteInfo getRouteInfo() {
        // 获取master信息
        // 获取 slaves信息
        // 注册监听
        if (routeInfo != null) {
            return routeInfo;
        } else {
            try {
                routeInfo = initRouteInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routeInfo;
        }
    }

    private synchronized RouteInfo initRouteInfo() throws Exception {
        RouteInfo routeInfo = new RouteInfo();
        getMasterNode(routeInfo, Path.buildMasters(group, shard));
        getSlavesNode(routeInfo, Path.buildMasters(group, shard));
        return this.routeInfo = routeInfo;
    }

    private void getMasterNode(RouteInfo routeInfo, final String path) throws Exception {
        List<NodeInfo> nodeInfos = zkService.find(path, new PathCallBack() {
            @Override
            public String getPath(String master) {
                return path + "/" + master;
            }
        }, NodeInfo.class);

        routeInfo.clearMasters();
        routeInfo.addMasters(nodeInfos);

    }

    private void getSlavesNode(RouteInfo routeInfo, final String path) throws Exception {

        List<NodeInfo> nodeInfos = zkService.find(path, new PathCallBack() {
            @Override
            public String getPath(String slave) {
                return path + "/" + slave;
            }
        }, NodeInfo.class);

        routeInfo.clearSlaves();
        routeInfo.addSlaves(nodeInfos);
    }
}
