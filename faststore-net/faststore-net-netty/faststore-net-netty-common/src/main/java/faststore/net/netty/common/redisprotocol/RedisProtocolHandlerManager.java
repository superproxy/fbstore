package faststore.net.netty.common.redisprotocol;

import faststore.net.netty.common.protocol.ProtocolHandlerManager;
import redis.server.netty.RedisCommandDecoder;
import redis.server.netty.RedisCommandHandler;
import redis.server.netty.RedisReplyEncoder;

public class RedisProtocolHandlerManager extends ProtocolHandlerManager {

    public RedisProtocolHandlerManager() {
        registerHandler(new RedisCommandDecoder());
        registerHandler(new RedisReplyEncoder());
        registerHandler(new RedisCommandHandler());
    }
}
