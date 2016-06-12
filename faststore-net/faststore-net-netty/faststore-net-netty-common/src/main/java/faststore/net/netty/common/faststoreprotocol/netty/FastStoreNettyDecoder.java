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
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import faststore.net.netty.common.faststoreprotocol.Protocol;
import faststore.net.netty.common.faststoreprotocol.codec.RequestDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;


/**
 * @author shijia.wxr<vintage.wang@gmail.com>
 * @since 2013-7-13
 */
public class FastStoreNettyDecoder extends LengthFieldBasedFrameDecoder {
    private static final Logger log = LoggerFactory.getLogger(FastStoreNettyDecoder.class);


    public FastStoreNettyDecoder() {
        super(Protocol.FRAME_MAX_LENGTH, 0, Protocol.TOTAL_LENGTH, 0, Protocol.TOTAL_LENGTH);
    }


    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = null;
        try {
            frame = (ByteBuf) super.decode(ctx, in);
            if (null == frame) {
                return null;
            }
            ByteBuffer byteBuffer = frame.nioBuffer();
            return RequestDecoder.decode(byteBuffer);
        } catch (Exception e) {
//            log.error("decode exception, " + RemotingHelper.parseChannelRemoteAddr(ctx.channel()), e);
//            RemotingUtil.closeChannel(ctx.channel());
        } finally {
            if (null != frame) {
                frame.release();
            }
        }

        return null;
    }
}
