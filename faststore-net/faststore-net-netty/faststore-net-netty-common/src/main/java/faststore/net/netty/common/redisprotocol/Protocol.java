package faststore.net.netty.common.redisprotocol;

public class Protocol {

    /**
     * 整体报文大小的自建
     */
    public final static int TOTAL_LENGTH = 4;
    /**
     * 报文头大小的字节
     */
    public final static int HEAD_LENGTH = 4;

    public final static int FRAME_MAX_LENGTH = 1024;
}
