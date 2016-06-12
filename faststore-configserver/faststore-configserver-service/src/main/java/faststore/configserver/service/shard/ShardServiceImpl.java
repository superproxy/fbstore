package faststore.configserver.service.shard;

import faststore.configserver.api.shard.Shard;
import faststore.configserver.api.shard.ShardService;
import faststore.configserver.service.common.PathCallBack;
import faststore.configserver.service.zk.ClientFactory;
import faststore.configserver.service.zk.ZkService;
import faststore.configserver.api.common.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ShardServiceImpl implements ShardService {
    private ZkService zkService = ClientFactory.getZkService();

   private Logger logger = LoggerFactory.getLogger(ShardServiceImpl.class);

    @Override
    public List<Shard> find(Object... objects) throws Exception {
        return zkService.find(Path.buildShards((String) objects[0]), new PathCallBack() {
            @Override
            public String getPath(String path) {
                return Path.buildShardInfo(path);
            }
        }, Shard.class);
    }

    @Override
    public void add(Shard shard) throws Exception {
        zkService.add(Path.buildShardInfo(shard.getGroup().getName()), shard);
    }

    @Override
    public void delete(Shard shard) throws Exception {
        zkService.delete(Path.buildShard(shard.getGroup().getName(), shard.getName()));
    }
}
