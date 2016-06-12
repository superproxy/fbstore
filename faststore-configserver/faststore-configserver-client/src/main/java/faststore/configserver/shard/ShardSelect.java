package faststore.configserver.shard;

/**
 * Created by 14120295 on 2016/6/1.
 */
public interface ShardSelect {


    /**
     * 根据 key选择 桶
     *
     * @param key
     * @return
     */
    String select(String key);
}
