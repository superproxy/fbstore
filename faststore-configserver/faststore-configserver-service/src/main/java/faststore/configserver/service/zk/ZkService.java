package faststore.configserver.service.zk;

import faststore.common.serializer.FastStoreSerializable;
import faststore.configserver.service.common.PathCallBack;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZkService {
    CuratorFramework client = new ZkClient().getClient();
    public <T> List<T> find(String path1, PathCallBack pathCallBack, Class<T> t) throws Exception {
        List<T> list = new ArrayList<T>();
        try {
            Stat b = client.checkExists().forPath(path1);
            if (b == null) {
                return Collections.emptyList();
            }
            List<String> nameList = client.getChildren().forPath(path1);
            for (String name : nameList) {
                b = client.checkExists().forPath(pathCallBack.getPath(name));
                if (b != null) {
                    byte[] result = client.getData().forPath(pathCallBack.getPath(name));
                    T group = FastStoreSerializable.decode(result, t);
                    list.add(group);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public <T> void add(String path, T object) throws Exception {
        add(path, object, true);
    }

    public <T> void add(String path, T object, boolean persistent) throws Exception {
        try {
            Stat b = client.checkExists().forPath(path);
            if (b == null) {
                if (persistent) {
                    client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                            .forPath(path, FastStoreSerializable.encode(object));
                } else {
                    client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                            .forPath(path, FastStoreSerializable.encode(object));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void delete(String path) {
        // group info
        try {
            Stat b = client.checkExists().forPath(path);
            if (b != null) {
                // old master会自动删除
                client.delete().guaranteed().forPath(path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
