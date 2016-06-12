package faststore.net.netty.common.faststoreprotocol.handler;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * 协议格式部分
 */
public class ProtocolPipelineFactory implements
        ChannelPipelineFactory {

    public ChannelPipeline getPipeline() throws Exception {
        // Create a default pipeline implementation.  
        ChannelPipeline pipeline = Channels.pipeline();

        // Add the text line codec combination first,  
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
                8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // and then business logic.
        pipeline.addLast("faststore.framework.processor.handler", new ServerHandler());

        return pipeline;
    }
}  