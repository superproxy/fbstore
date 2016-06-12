package faststore.configserver.shard;

import faststore.configserver.api.bucket.BucketRangeMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 一致性hash。通过key的桶映射来确定分片,分片对应桶的范围。
 * 方便数据迁移
 */
public class ShardSelectImpl implements ShardSelect {

    private int bucketSize = 1024;

    private Map<String, BucketRangeMap> bucketRangeMapMap = new HashMap<String, BucketRangeMap>();

    @Override
    public String select(String key) {

        int bucketIndex = getBucketIndex(key);
        return getShardByBucketIndex(bucketIndex);
    }

    private int getBucketIndex(String key) {
        return hash(key) % bucketSize;
    }

    private String getShardByBucketIndex(int bucketIndex) {
        for (Map.Entry<String, BucketRangeMap> entry : bucketRangeMapMap.entrySet()) {
            if (entry.getValue().getStart() <= bucketIndex && bucketIndex <= entry.getValue().getEnd()) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * murmurhash
     */
    private int hash(String key) {
        return 0;
    }
}
