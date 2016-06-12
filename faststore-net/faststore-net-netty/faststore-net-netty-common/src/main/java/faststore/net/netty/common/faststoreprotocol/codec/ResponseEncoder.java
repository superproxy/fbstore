package faststore.net.netty.common.faststoreprotocol.codec;

import faststore.common.serializer.FastStoreSerializable;
import faststore.net.netty.common.faststoreprotocol.Protocol;
import faststore.net.netty.common.faststoreprotocol.response.FSResponse;

import java.nio.ByteBuffer;

public class ResponseEncoder {

    public static ByteBuffer encode(FSResponse FSResponse) {
        // 2> header data length
        byte[] headerData = FastStoreSerializable.encode(FSResponse.getFsRequestHeaer());

        byte[] body = FSResponse.getFsRequestBody().getBody();
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
