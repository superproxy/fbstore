package faststore.net.netty.client;

import faststore.common.serializer.FastStoreSerializable;
import faststore.framework.processor.biz.common.RequestCode;
import faststore.net.netty.common.faststoreprotocol.Protocol;
import faststore.net.netty.common.faststoreprotocol.common.FSCommonBody;
import faststore.net.netty.common.faststoreprotocol.common.FSCommonHeader;
import faststore.net.netty.common.faststoreprotocol.response.FSResponse;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class RawClient2 {

    public static void main(String args[]) throws Exception {
        //为了简单起见，所有的异常都直接往外抛
        String host = "127.0.0.1";  //要连接的服务端IP地址
        int port = 5999;   //要连接的服务端对应的监听端口
        //与服务端建立连接
        Socket client = new Socket(host, port);
        //建立连接后就可以往服务端写数据了
        Writer writer = new OutputStreamWriter(client.getOutputStream());

        FSResponse FSResponse = new FSResponse();
        FSCommonHeader header = new FSCommonHeader(RequestCode.HELLO.value());
        FSResponse.setFsRequestHeaer(header);
        FSCommonBody responseBody = new FSCommonBody();
        responseBody.setBody("hello");
        FSResponse.setFsRequestBody(responseBody);
        try {
            byte[] headerData = FastStoreSerializable.encode(FSResponse.getFsRequestHeaer());

            byte[] body = FSResponse.getFsRequestBody().getBody();
            int bodyLength = body == null ? 0 : body.length;

            int totalLength = Protocol.TOTAL_LENGTH + Protocol.HEAD_LENGTH + headerData.length + bodyLength;


            byte[] bytes = new byte[1024];

            client.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}