package faststore.net.netty.common.protocol;

import io.netty.channel.ChannelHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class ProtocolHandlerManager {

    public void registerHandler(ChannelHandler channelHandler) {
        channelHandlerList.add(channelHandler);
    }

    private List<ChannelHandler> channelHandlerList = new ArrayList<>();

    public List<ChannelHandler> getChannleHandlers() {
        return channelHandlerList;
    }
}
