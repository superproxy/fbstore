package faststore.net.netty.common.redisprotocol.codec;

import faststore.common.serializer.FastStoreSerializable;
import faststore.net.netty.common.redisprotocol.Protocol;
import faststore.net.netty.common.redisprotocol.response.Response;

import java.nio.ByteBuffer;

/**
 * Created by 14120295 on 2016/5/24.
 */
public class ResponseEncoder {

    public static ByteBuffer encode(Response response) {
        // 2> header data length
        byte[] headerData = FastStoreSerializable.encode(response.getRequestHeaer());

        byte[] body = response.getRequestBody().getBody();
        int bodyLength = body == null ? 0 : body.length;

        ByteBuffer
                result = ByteBuffer.allocate(Protocol.TOTAL_LENGTH + Protocol.HEAD_LENGTH + headerData.length);

        // length
        result.putInt(Protocol.TOTAL_LENGTH + Protocol.HEAD_LENGTH + headerData.length + bodyLength);

        // header length
        result.putInt(headerData.length);

        // header data
        result.put(headerData);

        result.flip();

        return result;
    }

}
