package faststore.net.netty.client;

import faststore.net.netty.common.faststoreprotocol.netty.FastStoreNettyDecoder;
import faststore.net.netty.common.faststoreprotocol.netty.FastStoreNettyEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    Bootstrap b;
    ChannelFuture f;

    public void writeAndFlush(Object object) {
        f.channel().writeAndFlush(object);
    }

    public void init(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new FastStoreNettyEncoder(),
                            new FastStoreNettyDecoder(),
                            new ClientFastStoreNettyChannelHandler());
                }
            });

            // Start the client.
            f = b.connect(host, port).sync();
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public void stop() {
    }
}
