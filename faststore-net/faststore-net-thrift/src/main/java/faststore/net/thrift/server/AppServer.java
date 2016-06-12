package faststore.net.thrift.server;

import faststore.framework.server.Server;
import faststore.framework.server.ServerContext;
import faststore.framework.server.ServerInfo;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import faststore.net.thrift.api.request.DataServerService;

public class AppServer implements Server {
    private ServerContext serverContext;

    @Override
    public void init(ServerContext serverContext) throws Exception {
        this.serverContext = serverContext;
    }

    @Override
    public void start() throws Exception {
        ServerServiceHandler serverServiceHandler = new ServerServiceHandler();
        serverServiceHandler.setRequestHandlerChains(serverContext.getRequestHandlerChains());

        final DataServerService.Processor<ServerServiceHandler> routeInfoHandlerProcessor
                = new DataServerService.Processor<ServerServiceHandler>(serverServiceHandler);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TNonblockingServerSocket   serverTransport = new TNonblockingServerSocket(serverContext.getServerConfig().getPort());
                    TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
                    tArgs.processor(routeInfoHandlerProcessor);
                    tArgs.transportFactory(new TFramedTransport.Factory());
                    tArgs.protocolFactory(new TCompactProtocol.Factory());
                    // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
                    TServer server = new TNonblockingServer(tArgs);
                    System.out.println("Starting the simple faststore.dataserver.server ....");
                    server.serve();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void stop() throws Exception {

    }

    @Override
    public ServerInfo getServerInfo() throws Exception {
        return new ServerInfo();
    }
}
