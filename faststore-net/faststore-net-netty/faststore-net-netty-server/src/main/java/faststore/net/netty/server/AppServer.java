package faststore.net.netty.server;

import faststore.framework.protocol.ProtocolType;
import faststore.framework.server.Server;
import faststore.framework.server.ServerContext;
import faststore.framework.server.ServerInfo;
import faststore.net.netty.common.faststoreprotocol.FastStoreProtocolHandlerManager;
import faststore.net.netty.common.protocol.ProtocolHandlerManager;
import faststore.net.netty.common.redisprotocol.RedisProtocolHandlerManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于netty实现的server服务
 */
public class AppServer implements Server {
    private final static Logger logger = LoggerFactory.getLogger(AppServer.class);

    private ServerContext serverContext;

    private ProtocolHandlerManager protocolHandlerManager;

    public AppServer() {
    }

    public void init(ServerContext serverContext) throws Exception {
        this.serverContext = serverContext;

        String protocol = serverContext.getServerConfig().getProtocol();
        if (ProtocolType.REDIS.name().equals(protocol)) {
            protocolHandlerManager = new RedisProtocolHandlerManager();
        }

        if (ProtocolType.FASTSTORE.name().equals(protocol)) {
            protocolHandlerManager = new FastStoreProtocolHandlerManager();
        }
        if (protocolHandlerManager == null) {
            throw new UnsupportedOperationException("netty server doesn't support this protocol, handlerManager " + protocol);
        }
    }

    public void start() throws Exception {
        logger.info("netty server is starting....");
        run(); // loop
    }


    public void run() {
        // 构造NioServerSocketChannelFactory，初始化bootstrap
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);   //这个是用于serversocketchannel的eventloop
        EventLoopGroup workerGroup = new NioEventLoopGroup();    //这个是用于处理accept到的channel
        try {
            ServerBootstrap b = new ServerBootstrap();    //构建serverbootstrap对象
            b.childOption(ChannelOption.TCP_NODELAY, true);
            b.group(bossGroup, workerGroup);   //设置时间循环对象，前者用来处理accept事件，后者用于处理已经建立的连接的io
            b.channel(NioServerSocketChannel.class);   //用它来建立新accept的连接，用于构造serversocketchannel的工厂类
            b.childHandler(new ChannelInitializer<SocketChannel>() {      //为accept channel的pipeline预添加的inboundhandler
                @Override     //当新连接accept的时候，这个方法会调用
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(protocolHandlerManager.getChannleHandlers().toArray(new ChannelHandler[0]));   //为当前的channel的pipeline添加自定义的处理函数
                }
            });
            //bind方法会创建一个serverchannel，并且会将当前的channel注册到eventloop上面，
            //会为其绑定本地端口，并对其进行初始化，为其的pipeline加一些默认的handler
            ChannelFuture f = b.bind(serverContext.getServerConfig().getPort()).sync();
            f.channel().closeFuture().sync();  //相当于在这里阻塞，直到serverchannel关闭
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void stop() {
    }

    public ServerInfo getServerInfo() throws Exception {
        return null;
    }
}
