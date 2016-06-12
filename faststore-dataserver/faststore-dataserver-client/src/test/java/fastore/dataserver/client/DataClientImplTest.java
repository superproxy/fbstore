package fastore.dataserver.client;

import faststore.dataserver.api.vo.GetCmd;
import faststore.dataserver.api.vo.SetCmd;
import faststore.framework.processor.biz.common.RequestCode;
import faststore.framework.processor.biz.common.CommonHeader;
import faststore.framework.processor.biz.request.CommonRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class DataClientImplTest {

    private final static Logger logger = LoggerFactory.getLogger(DataClientImplTest.class);
    private static DataClient client;

    private String zk = "127.0.0.1:2181";
    private String group = "group0";

    @Test
    public void test() throws Exception {
        client = new DataClientImpl(zk, group);
        doPut();
        doGet();
    }

    private static void doPut() throws Exception {
        CommonRequest<SetCmd> commonRequest = new CommonRequest<SetCmd>();
        commonRequest.setCommonHeader(new CommonHeader(RequestCode.SET.value()));
        int n = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            client.put("key-" + i, "value-" + i);
        }

        System.out.println("write total-ms:" + (System.currentTimeMillis() - start));
        System.out.println("write:tps" + n / ((System.currentTimeMillis() - start) / 1000));
    }

    private static void doGet() throws Exception {
        CommonRequest<GetCmd> commonRequest = new CommonRequest<GetCmd>();
        commonRequest.setCommonHeader(new CommonHeader(RequestCode.GET.value()));
        int n = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Object objet = client.get("key-" + i);
            if (objet != null) {
//                System.out.println(objet.toString());
            }
        }

        System.out.println("read total-ms:" + (System.currentTimeMillis() - start));
        System.out.println("read:tps" + n / ((System.currentTimeMillis() - start) / 1000));
    }


}
