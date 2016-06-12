package faststore.dataserver.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import faststore.net.netty.common.faststoreprotocol.handler.NettyDecoder;
import faststore.net.netty.common.faststoreprotocol.handler.NettyEncoder;

public class ServerImpl implements Server {

    private ServerConfig serverConfig;

    public ServerImpl(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    public void start() {
        run();
    }


    public void run() {
        // 构造NioServerSocketChannelFactory，初始化bootstrap
        EventLoopGroup bossGroup = new NioEventLoopGroup();   //这个是用于serversocketchannel的eventloop
        EventLoopGroup workerGroup = new NioEventLoopGroup();    //这个是用于处理accept到的channel
        try {
            ServerBootstrap b = new ServerBootstrap();    //构建serverbootstrap对象
            b.group(bossGroup, workerGroup);   //设置时间循环对象，前者用来处理accept事件，后者用于处理已经建立的连接的io
            b.channel(NioServerSocketChannel.class);   //用它来建立新accept的连接，用于构造serversocketchannel的工厂类
            b.childHandler(new ChannelInitializer<SocketChannel>() {      //为accept channel的pipeline预添加的inboundhandler
                @Override     //当新连接accept的时候，这个方法会调用
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new NettyDecoder(),
                            new MyChannelHandler(),
                            new NettyEncoder());   //为当前的channel的pipeline添加自定义的处理函数
                }

            });
            //bind方法会创建一个serverchannel，并且会将当前的channel注册到eventloop上面，
            //会为其绑定本地端口，并对其进行初始化，为其的pipeline加一些默认的handler
            ChannelFuture f = b.bind(serverConfig.getPort()).sync();
            f.channel().closeFuture().sync();  //相当于在这里阻塞，直到serverchannel关闭
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void stop() {
    }
}
