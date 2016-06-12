package faststore.net.netty.common.faststoreprotocol;

import faststore.net.netty.common.faststoreprotocol.netty.FastStoreNettyChannelHandler;
import faststore.net.netty.common.faststoreprotocol.netty.FastStoreNettyDecoder;
import faststore.net.netty.common.faststoreprotocol.netty.FastStoreNettyEncoder;
import faststore.net.netty.common.protocol.ProtocolHandlerManager;

public class FastStoreProtocolHandlerManager extends ProtocolHandlerManager {
    public FastStoreProtocolHandlerManager() {
        registerHandler(new FastStoreNettyDecoder());
        registerHandler(new FastStoreNettyEncoder());
        registerHandler(new FastStoreNettyChannelHandler());
    }
}
