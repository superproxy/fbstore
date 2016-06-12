package faststore.net.thrift.client;

import faststore.framework.client.Client;
import faststore.framework.client.ClientContext;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.processor.biz.response.CommonResponse;
import faststore.net.thrift.api.request.DataServerService;
import faststore.net.thrift.api.request.Request;
import faststore.net.thrift.api.request.Response;
import faststore.net.thrift.protocol.Thrift2BizConvert;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


public class AppClient implements Client {
    private TTransport transport;
    private TSocket socket;
    private DataServerService.Client client;
    private static final int TIMEOUT = 30000;

    @Override
    public void init(ClientContext clientContext) throws Exception {
        socket = new TSocket(clientContext.getRemoteIp(), clientContext.getRemotePort(), TIMEOUT);
        socket.getSocket().setTcpNoDelay(true);
        socket.getSocket().setSoLinger(false, 0);
        transport = new TFramedTransport(socket);
        TProtocol protocol = new TCompactProtocol(transport);
        transport.open();
        client = new DataServerService.Client(protocol);
    }

    public void stop() {
        transport.close();
    }

    @Override
    public <T> CommonResponse send(CommonRequest<T> commonRequest) throws Exception {
        Request request = Thrift2BizConvert.convertRequest(commonRequest);
        Response response = doAction(request);
        return Thrift2BizConvert.convertResponse(response);
    }


    protected Response doAction(Request request) throws Exception {
        return client.doHandler(request);
    }


}
