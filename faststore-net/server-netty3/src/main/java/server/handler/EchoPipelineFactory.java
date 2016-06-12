package faststore.net.netty.common.faststoreprotocol.handler;


import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.channels.Channels;

public class EchoPipelineFactory implements
        ChannelPipelineFactory {

    public ChannelPipeline getPipeline() throws Exception {
        // Create a default pipeline implementation.  
        ChannelPipeline pipeline =   Channels.pipeline();

        // Add the text line codec combination first,  
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
                8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // and then business logic.
        pipeline.addLast("faststore.framework.processor.handler", new EchoServerHandler());

        return pipeline;
    }
}  