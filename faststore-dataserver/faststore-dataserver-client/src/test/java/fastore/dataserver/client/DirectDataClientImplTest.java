package fastore.dataserver.client;

import faststore.dataserver.api.vo.GetCmd;
import faststore.dataserver.api.vo.SetCmd;
import faststore.framework.processor.biz.common.RequestCode;
import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.request.CommonRequest;
import faststore.framework.protocol.ProtocolType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class DirectDataClientImplTest {
    private DataClient client;
    int n = 10000;
    //    String ip ="127.0.0.1";
    String ip = "10.27.113.40";
    int port =5999;
    String protocol = ProtocolType.FASTSTORE.name();

    @BeforeMethod
    public void init() {
        client = new DirectDataClientImpl(ip, port, protocol);
    }

    @Test
    public void test() throws Exception {
        doPut();
        doGet();
    }

    @Test
    public void testPut() throws Exception {
        doPut();
    }

    @Test
    public void testGet() throws Exception {
        doGet();
    }

    private void doPut() throws Exception {
        CommonRequest<SetCmd> commonRequest = new CommonRequest<SetCmd>();
        commonRequest.setCommonHeader(new CommonHeader(RequestCode.SET.value()));

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            client.put("key-" + i, "value-" + i);
            System.out.println("put key-" + i);
        }

        System.out.println("write total-ms:" + getGap(start));
        System.out.println("write:tps" + n * 1000 / (getGap(start)));
    }

    private void doGet() throws Exception {
        CommonRequest<GetCmd> commonRequest = new CommonRequest<GetCmd>();
        commonRequest.setCommonHeader(new CommonHeader(RequestCode.GET.value()));
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Object objet = client.get("key-" + i);
            if (objet != null) {
//                System.out.println(objet.toString());
            }
        }

        System.out.println("read total-ms:" + getGap(start));
        System.out.println("read:tps" + n * 1000 / getGap(start));
    }

    private long getGap(long start) {
        return System.currentTimeMillis() - start;
    }
}