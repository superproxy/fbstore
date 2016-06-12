package faststore.net.netty.client;

import faststore.framework.client.ClientContext;
import faststore.framework.protocol.ProtocolType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppClientTest {

    AppClient appClient;

    @BeforeMethod
    public void init() throws Exception {

        appClient = new AppClient();
        ClientContext clientContext = new ClientContext();

        String clientType= ProtocolType.FASTSTORE.name();
        String remoteIp = "127.0.0.1";
        int remotePort = 5999;
        clientContext.setClientType(clientType);
        clientContext.setRemoteIp(remoteIp);
        clientContext.setRemotePort(remotePort);
        appClient.init(clientContext);
    }

    @Test
    public void testDoAction() throws Exception {
//        appClient.send();

    }
}