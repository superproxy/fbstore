package faststore.net.netty.common.redisprotocol.codec;

import faststore.common.serializer.FastStoreSerializable;
import faststore.net.netty.common.redisprotocol.Protocol;
import faststore.net.netty.common.redisprotocol.common.CommonBody;
import faststore.net.netty.common.redisprotocol.request.Request;

import java.nio.ByteBuffer;

public class RequestDecoder {
    //  4  长度  body
    public static Request decode(final ByteBuffer byteBuffer) {
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

        Request request = FastStoreSerializable.decode(headerData, Request.class);
        request.setCommonBody(new CommonBody(bodyData));
        return request;
    }
}
