/**
 * Copyright (C) 2010-2013 Alibaba Group Holding Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package faststore.net.netty.common.faststoreprotocol.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import faststore.net.netty.common.faststoreprotocol.codec.ResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import faststore.net.netty.common.faststoreprotocol.response.FSResponse;

import java.nio.ByteBuffer;


public class FastStoreNettyEncoder extends MessageToByteEncoder<FSResponse> {
    private static final Logger log = LoggerFactory.getLogger(FastStoreNettyEncoder.class);

    @Override
    public void encode(ChannelHandlerContext ctx, FSResponse FSResponse, ByteBuf out)
            throws Exception {
        try {
            ByteBuffer header = ResponseEncoder.encode(FSResponse);
            out.writeBytes(header);
            byte[] body = FSResponse.getFsRequestBody().getBody();
            if (body != null) {
                out.writeBytes(body);
            }
        } catch (Exception e) {
//            log.error("encode exception, " + RemotingHelper.parseChannelRemoteAddr(ctx.channel()), e);
            if (FSResponse != null) {
                log.error(FSResponse.toString());
            }
//            RemotingUtil.closeChannel(ctx.channel());
        }
    }
}
