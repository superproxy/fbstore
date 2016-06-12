package faststore.net.netty.client;

import faststore.common.serializer.FastStoreSerializable;
import faststore.framework.processor.biz.common.RequestCode;
import faststore.net.netty.common.faststoreprotocol.Protocol;
import faststore.net.netty.common.faststoreprotocol.common.FSCommonBody;
import faststore.net.netty.common.faststoreprotocol.common.FSCommonHeader;
import faststore.net.netty.common.faststoreprotocol.response.FSResponse;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Client {
    //为了简单起见，所有的异常都直接往外抛
    String host = "127.0.0.1";  //要连接的服务端IP地址
    int port = 5999;   //要连接的服务端对应的监听端口
    Socket client;
    DataOutputStream stream;

    public void init(String host, int port) throws Exception {
        //与服务端建立连接
        client = new Socket(host, port);
        stream = new DataOutputStream(client.getOutputStream());
        FSResponse FSResponse = getResponse();
        try {
            writeContent(stream, FSResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() throws IOException {
        stream.close();
        client.close();
    }

    private FSResponse getResponse() {
        FSResponse FSResponse = new FSResponse();
        FSCommonHeader header = new FSCommonHeader(RequestCode.HELLO.value());
        FSResponse.setFsRequestHeaer(header);
        FSCommonBody responseBody = new FSCommonBody();
        responseBody.setBody("hello");
        FSResponse.setFsRequestBody(responseBody);
        return FSResponse;
    }

    private void writeContent(DataOutputStream stream, FSResponse FSResponse) throws IOException {
        byte[] headerData = FastStoreSerializable.encode(FSResponse.getFsRequestHeaer());

        byte[] body = FSResponse.getFsRequestBody().getBody();
        int bodyLength = body == null ? 0 : body.length;

        int totalLength = Protocol.TOTAL_LENGTH + Protocol.HEAD_LENGTH + headerData.length + bodyLength;


        ByteBuffer buffer = ByteBuffer.allocate(totalLength);

        buffer.putInt(totalLength);

        buffer.putInt(headerData.length);
        buffer.put(headerData);
        buffer.put(body);
        buffer.flip();
        stream.write(buffer.array());
        stream.flush();//写完后要记得flush
    }

}