package faststore.configserver.api.common;

import java.text.MessageFormat;

public class Path {
    public final static String ROOT = "/faststore";
    public final static String DEFAULT_SHARD_NAME = "shard0";
    public final static String ROOT_NAME = "/faststore";
    public final static String GROUPS = "/faststore/groups";
    public final static String GROUP = "/faststore/groups/{0}";
    public final static String GROUP_INFO = "/faststore/groups/{0}/info";
    public final static String SHARDS = "/faststore/groups/{0}/shards";
    public final static String SHARD = "/faststore/groups/{0}/shards/{1}";
    public final static String SHARD_INFO = "/faststore/groups/{0}/shards/{1}/info";
    public final static String SELECTOR = "/faststore/groups/{0}/shards/{1}/selector";
    public final static String NODES = "/faststore/groups/{0}/shards/{1}/nodes";
    public final static String MASTERS = "/faststore/groups/{0}/shards/{1}/nodes/masters";
    public final static String SLAVES = "/faststore/groups/{0}/shards/{1}/nodes/slaves";
    public final static String MASTER_NODE = "/faststore/groups/{0}/shards/{1}/nodes/masters/{2}";
    public final static String SLAVE_NODE = "/faststore/groups/{0}/shards/{1}/nodes/slaves/{2}";

    public static String buildGroup(String group) {
        return MessageFormat.format(GROUP, group);
    }

    public static String buildShards(String group) {
        return MessageFormat.format(SHARDS, group);
    }

    public static String buildShard(String group, String shard) {
        return MessageFormat.format(SHARD, group, shard);
    }


    public static String buildMasterNode(String group, String shard, String node) {
        return MessageFormat.format(MASTER_NODE, group, shard, node);
    }

    public static String buildMasters(String group, String shard) {
        return MessageFormat.format(MASTERS, group, shard);
    }

    public static String buildSlaves(String group, String shard) {
        return MessageFormat.format(SLAVES, group, shard);
    }

    public static String buildSlaveNode(String group, String shard, String node) {
        return MessageFormat.format(SLAVE_NODE, group, shard, node);
    }

    public static String buildGroupInfo(String group) {
        return MessageFormat.format(GROUP_INFO, group);
    }

    public static String buildSelector(String group, String shard) {
        return MessageFormat.format(SELECTOR, group, shard);
    }


    public static void main(String[] args) {
        String value = buildMasterNode("group1", "shard1", "node1");
        System.out.println(value);
    }

    public static String buildShardInfoWithShard(String group, String shard) {
        return MessageFormat.format(SHARD_INFO, group, shard);
    }

    public static String buildShardInfo(String group) {
        return buildShardInfoWithShard(group, "shard0");
    }

    public static String buildNodes(String group, String shard) {
        return MessageFormat.format(NODES, group, shard);
    }
}
