package faststore.net.netty.common.faststoreprotocol.codec;

import faststore.common.serializer.FastStoreSerializable;
import faststore.net.netty.common.faststoreprotocol.Protocol;
import faststore.net.netty.common.faststoreprotocol.request.FSRequest;
import faststore.net.netty.common.faststoreprotocol.common.FSCommonBody;

import java.nio.ByteBuffer;

public class RequestDecoder {
    //  4  长度  body
    public static FSRequest decode(final ByteBuffer byteBuffer) {
        int length = byteBuffer.limit();
        int headerDataLength = byteBuffer.getInt();

        byte[] headerData = new byte[headerDataLength];
        byteBuffer.get(headerData);

        int bodyLength = length - Protocol.HEAD_LENGTH - headerDataLength;
        byte[] bodyData = null;
        if (bodyLength > 0) {
            bodyData = new byte[bodyLength];
            byteBuffer.get(bodyData);
        }

        FSRequest FSRequest = FastStoreSerializable.decode(headerData, FSRequest.class);
        FSRequest.setFSCommonBody(new FSCommonBody(bodyData));
        return FSRequest;
    }
}
